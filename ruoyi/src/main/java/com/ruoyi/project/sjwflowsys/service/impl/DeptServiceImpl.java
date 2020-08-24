package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.web.domain.TreeSelectStr;
import com.ruoyi.project.sjwflowsys.domain.User;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sjwflowsys.mapper.DeptMapper;
import com.ruoyi.project.sjwflowsys.domain.Dept;
import com.ruoyi.project.sjwflowsys.service.IDeptService;

/**
 * 部门Service业务层处理
 * 
 * @author littlemoneygod
 * @date 2020-07-06
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class DeptServiceImpl implements IDeptService
{
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询部门
     * 
     * @param id 部门ID
     * @return 部门
     */
    @Override
    public Dept selectDeptById(String id)
    {
        return deptMapper.selectDeptById(id);
    }

    /**
     * 查询部门列表
     * 
     * @param dept 部门
     * @return 部门
     */
    @Override
    public List<Dept> selectDeptList(Dept dept)
    {
        List<Dept> depts = deptMapper.selectDeptList(dept);
        for(Dept d:depts)
        {
            d.setHasChildren(d.getHasChild());
        }
        return depts;
    }

    /**
     * 新增部门
     * 
     * @param dept 部门
     * @return 结果
     */
    @Override
    public int insertDept(Dept dept)
    {
        return deptMapper.insertDept(dept);
    }

    /**
     * 修改部门
     * 
     * @param dept 部门
     * @return 结果
     */
    @Override
    public int updateDept(Dept dept)
    {
        return deptMapper.updateDept(dept);
    }

    /**
     * 批量删除部门
     * 
     * @param ids 需要删除的部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptByIds(String[] ids)
    {
        return deptMapper.deleteDeptByIds(ids);
    }

    /**
     * 删除部门信息
     * 
     * @param id 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(String id)
    {
        return deptMapper.deleteDeptById(id);
    }

    private boolean isover = true;

    /**
     * 根据当前节点递归获取部门树
     * @param ID
     * @param list
     * @return
     */
    public TreeSelectStr getDeptTree(String ID,List<TreeSelectStr> list,boolean isCompany){
        Dept dept = deptMapper.selectDeptById(ID);
        TreeSelectStr tsMain = new TreeSelectStr();
        if(dept.getPid().equals("-1"))
        {
            tsMain = new TreeSelectStr(dept,list);
        }
        else
        {
            Dept query = new Dept();
            query.setPid(dept.getPid());
            List<TreeSelectStr> trees = new ArrayList<>();
            List<Dept> depts = deptMapper.selectDeptList(query);
            for (Dept d:depts)
            {
                TreeSelectStr ts = new TreeSelectStr();
                ts.setId(d.getId());
                ts.setLabel(d.getName());
                if(d.getId().equals(ID)){
                    ts.setChildren(list);
                }else{
                    if((!isCompany || !JudgeDeptType(d.getDepttype()))&&d.getHasChild()){
                        ts.setChildren(null);
                    }else{
                        ts.setChildren(new ArrayList<>());
                    }
                }
                trees.add(ts);
            }
            tsMain = getDeptTree(dept.getPid(),trees,isCompany);
        }
        return tsMain;
    }

    /**
     * 判断节点是否是省纪委、市纪委、县区纪委
     * @param deptType
     * @return
     */
    public boolean JudgeDeptType(String deptType){
        String [] array = {"省纪委","市纪委","县区纪委"};
        return ArrayUtils.contains(array,deptType);
    }

    /**
     * 根据条件设置部门树的haschildren
     * @param selectType
     * @param Type
     * @param list
     * @return
     */
    public List<Dept> setHasChildren(String selectType,String Type,List<Dept> list){
        switch (Type){
            case "dept": //查询的是部门，并且展示方式是tree，haschildren值与selecttree相反，
                if(selectType.equals("tree"))
                {
                    for(Dept d : list)
                    {
                        d.setHasChildren(!d.getHasChildren());
                    }
                }
                break;
            case "company":
                boolean istree = selectType.equals("tree");
                for(Dept d : list)
                {
                    if(d.getDepttype()==null||JudgeDeptType(d.getDepttype())){
                        d.setHasChildren(istree);
                    }else{
                        d.setHasChildren(!istree);
                    }
                }
                break;
        }
        return list;
    }

    /**
     * 更新用户表部门id
     * @param deptid
     * @param deptname
     * @return
     */
    public int updateDeptname(String deptid, String deptname){
        return deptMapper.updateDeptname(deptid,deptname);
    }

    /**
     * 根据companyid生成部门树
     * @param depts
     * @param users
     * @param result
     * @return
     */
    public List<Dept> UserTreeByCompany(List<Dept> depts,List<User> users,List<Dept> result){
        for(Dept d : result)
        {
            List<Dept> curDepts = depts.stream().filter(de->de.getPid().equals(d.getId())).collect(Collectors.toList());
            List<Dept> nextDepts = curDepts;
            curDepts.addAll(GetUserByDept(d.getId(),users));
            d.setChildren(curDepts);
            d.setHasChildren(true);
            UserTreeByCompany(depts,users,nextDepts);
        }
        return result;
    }

    public List<Dept> GetUserByDept(String deptid,List<User> user)
    {
        List<User> curUsers = user.stream().filter(u->u.getDeptid().equals(deptid)).collect(Collectors.toList());
        List<Dept> curdepts = curUsers.stream().map(Dept::new).collect(Collectors.toList());
        return curdepts;
    }

    /**
     * 根据companyid 查部门
     * @param id
     * @return
     */
    public List<Dept> selectDeptByCompanyID(String id){
        return deptMapper.selectDeptByCompanyID(id);
    }
}
