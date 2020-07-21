package com.ruoyi.project.sjwflowbusiness.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 附件管理对象 fileupload
 * 
 * @author littlemoneygod
 * @date 2020-07-21
 */
public class Fileupload extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String filename;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String basepath;

    /** $column.columnComment */
    private String savepath;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fileextend;

    /** $column.columnComment */
    private String editor;

    /** $column.columnComment */
    private String addtime;

    /** $column.columnComment */
    private Boolean history;

    /** $column.columnComment */
    private String deptname;

    /** $column.columnComment */
    private Boolean isdeleted;

    /** $column.columnComment */
    private String dictid;

    /** $column.columnComment */
    private String clueid;

    /** $column.columnComment */
    private String userid;

    /** $column.columnComment */
    private String category;

    /** $column.columnComment */
    private String pdfid;

    /** $column.columnComment */
    private String stepid;

    /** $column.columnComment */
    private Long filesize;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String stepname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String username;

    /** 状态 */
    private String state;

    /** 申请签章 */
    @Excel(name = "申请签章")
    private String applystamp;

    /** 签章 */
    private String addstamp;

    /** $column.columnComment */
    private String ismeasure;

    /** $column.columnComment */
    @Excel(name = "申请签章")
    private String instanceid;

    /** $column.columnComment */
    private String process;

    /** $column.columnComment */
    private String flowid;

    /** $column.columnComment */
    private String groupid;

    /** 拟文签章关联 */
    @Excel(name = "拟文签章关联")
    private String niwenid;

    /** 文书编号 */
    @Excel(name = "文书编号")
    private String code;

    /** 历史记录 */
    private String hashistory;

    /** 该字段跟业务平台没关系,是同步巡视或信访信息用的 0 系统上传 1巡视或信访 */
    private Integer sourcetype;

    /** 是否同步 跟业务平台没关系是后台同步代码使用 0默认 1 同步成功 2文件缺失 */
    private Integer issync;

    /** 关联的文书ID */
    private String wenshuid;

    /** 步骤阶段 */
    private Long stepstate;

    /** $column.columnComment */
    private String filepathid;

    /** $column.columnComment */
    private String docabbreviation;

    /** $column.columnComment */
    @Excel(name = "文书编号")
    private Long printnum;

    /** $column.columnComment */
    @Excel(name = "文书编号")
    private Integer isofd;

    /** $column.columnComment */
    private Boolean isoldspb;

    /** 是否事项报告附件（专用） */
    private Boolean isthing;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setBasepath(String basepath) 
    {
        this.basepath = basepath;
    }

    public String getBasepath() 
    {
        return basepath;
    }
    public void setSavepath(String savepath) 
    {
        this.savepath = savepath;
    }

    public String getSavepath() 
    {
        return savepath;
    }
    public void setFileextend(String fileextend) 
    {
        this.fileextend = fileextend;
    }

    public String getFileextend() 
    {
        return fileextend;
    }
    public void setEditor(String editor) 
    {
        this.editor = editor;
    }

    public String getEditor() 
    {
        return editor;
    }
    public void setAddtime(String addtime) 
    {
        this.addtime = addtime;
    }

    public String getAddtime() 
    {
        return addtime;
    }
    public void setHistory(Boolean history) 
    {
        this.history = history;
    }

    public Boolean getHistory() 
    {
        return history;
    }
    public void setDeptname(String deptname) 
    {
        this.deptname = deptname;
    }

    public String getDeptname() 
    {
        return deptname;
    }
    public void setIsdeleted(Boolean isdeleted) 
    {
        this.isdeleted = isdeleted;
    }

    public Boolean getIsdeleted() 
    {
        return isdeleted;
    }
    public void setDictid(String dictid) 
    {
        this.dictid = dictid;
    }

    public String getDictid() 
    {
        return dictid;
    }
    public void setClueid(String clueid) 
    {
        this.clueid = clueid;
    }

    public String getClueid() 
    {
        return clueid;
    }
    public void setUserid(String userid) 
    {
        this.userid = userid;
    }

    public String getUserid() 
    {
        return userid;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setPdfid(String pdfid) 
    {
        this.pdfid = pdfid;
    }

    public String getPdfid() 
    {
        return pdfid;
    }
    public void setStepid(String stepid) 
    {
        this.stepid = stepid;
    }

    public String getStepid() 
    {
        return stepid;
    }
    public void setFilesize(Long filesize) 
    {
        this.filesize = filesize;
    }

    public Long getFilesize() 
    {
        return filesize;
    }
    public void setStepname(String stepname) 
    {
        this.stepname = stepname;
    }

    public String getStepname() 
    {
        return stepname;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setApplystamp(String applystamp) 
    {
        this.applystamp = applystamp;
    }

    public String getApplystamp() 
    {
        return applystamp;
    }
    public void setAddstamp(String addstamp) 
    {
        this.addstamp = addstamp;
    }

    public String getAddstamp() 
    {
        return addstamp;
    }
    public void setIsmeasure(String ismeasure) 
    {
        this.ismeasure = ismeasure;
    }

    public String getIsmeasure() 
    {
        return ismeasure;
    }
    public void setInstanceid(String instanceid) 
    {
        this.instanceid = instanceid;
    }

    public String getInstanceid() 
    {
        return instanceid;
    }
    public void setProcess(String process) 
    {
        this.process = process;
    }

    public String getProcess() 
    {
        return process;
    }
    public void setFlowid(String flowid) 
    {
        this.flowid = flowid;
    }

    public String getFlowid() 
    {
        return flowid;
    }
    public void setGroupid(String groupid) 
    {
        this.groupid = groupid;
    }

    public String getGroupid() 
    {
        return groupid;
    }
    public void setNiwenid(String niwenid) 
    {
        this.niwenid = niwenid;
    }

    public String getNiwenid() 
    {
        return niwenid;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setHashistory(String hashistory) 
    {
        this.hashistory = hashistory;
    }

    public String getHashistory() 
    {
        return hashistory;
    }
    public void setSourcetype(Integer sourcetype) 
    {
        this.sourcetype = sourcetype;
    }

    public Integer getSourcetype() 
    {
        return sourcetype;
    }
    public void setIssync(Integer issync) 
    {
        this.issync = issync;
    }

    public Integer getIssync() 
    {
        return issync;
    }
    public void setWenshuid(String wenshuid) 
    {
        this.wenshuid = wenshuid;
    }

    public String getWenshuid() 
    {
        return wenshuid;
    }
    public void setStepstate(Long stepstate) 
    {
        this.stepstate = stepstate;
    }

    public Long getStepstate() 
    {
        return stepstate;
    }
    public void setFilepathid(String filepathid) 
    {
        this.filepathid = filepathid;
    }

    public String getFilepathid() 
    {
        return filepathid;
    }
    public void setDocabbreviation(String docabbreviation) 
    {
        this.docabbreviation = docabbreviation;
    }

    public String getDocabbreviation() 
    {
        return docabbreviation;
    }
    public void setPrintnum(Long printnum) 
    {
        this.printnum = printnum;
    }

    public Long getPrintnum() 
    {
        return printnum;
    }
    public void setIsofd(Integer isofd) 
    {
        this.isofd = isofd;
    }

    public Integer getIsofd() 
    {
        return isofd;
    }
    public void setIsoldspb(Boolean isoldspb) 
    {
        this.isoldspb = isoldspb;
    }

    public Boolean getIsoldspb() 
    {
        return isoldspb;
    }
    public void setIsthing(Boolean isthing) 
    {
        this.isthing = isthing;
    }

    public Boolean getIsthing() 
    {
        return isthing;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("filename", getFilename())
            .append("basepath", getBasepath())
            .append("savepath", getSavepath())
            .append("fileextend", getFileextend())
            .append("editor", getEditor())
            .append("addtime", getAddtime())
            .append("history", getHistory())
            .append("deptname", getDeptname())
            .append("isdeleted", getIsdeleted())
            .append("dictid", getDictid())
            .append("clueid", getClueid())
            .append("userid", getUserid())
            .append("category", getCategory())
            .append("pdfid", getPdfid())
            .append("stepid", getStepid())
            .append("filesize", getFilesize())
            .append("stepname", getStepname())
            .append("username", getUsername())
            .append("state", getState())
            .append("applystamp", getApplystamp())
            .append("addstamp", getAddstamp())
            .append("ismeasure", getIsmeasure())
            .append("instanceid", getInstanceid())
            .append("process", getProcess())
            .append("flowid", getFlowid())
            .append("groupid", getGroupid())
            .append("niwenid", getNiwenid())
            .append("code", getCode())
            .append("hashistory", getHashistory())
            .append("sourcetype", getSourcetype())
            .append("issync", getIssync())
            .append("wenshuid", getWenshuid())
            .append("stepstate", getStepstate())
            .append("filepathid", getFilepathid())
            .append("docabbreviation", getDocabbreviation())
            .append("printnum", getPrintnum())
            .append("isofd", getIsofd())
            .append("isoldspb", getIsoldspb())
            .append("isthing", getIsthing())
            .toString();
    }
}
