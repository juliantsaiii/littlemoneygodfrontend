package com.ruoyi.project.sjwflowsys.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.framework.web.domain.TreeSelectStr;
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
    public TreeSelectStr getDeptTree(String ID,List<TreeSelectStr> list){
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
                    ts.setChildren(new ArrayList<>());
                }
                trees.add(ts);
            }
            tsMain = getDeptTree(dept.getPid(),trees);
        }
        return tsMain;
    }
}
