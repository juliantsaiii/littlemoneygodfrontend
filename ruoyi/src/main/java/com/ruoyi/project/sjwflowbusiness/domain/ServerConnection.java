package com.ruoyi.project.sjwflowbusiness.domain;

public class ServerConnection {
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    private String Name;
    private Boolean Status;
}
