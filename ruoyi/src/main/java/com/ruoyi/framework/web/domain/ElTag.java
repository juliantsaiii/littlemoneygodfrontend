package com.ruoyi.framework.web.domain;

import com.ruoyi.project.sjwflowsys.domain.User;

public class ElTag {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private String id;

    public ElTag(User user){
        name = user.getFullname();
        id = user.getId();
    }


}
