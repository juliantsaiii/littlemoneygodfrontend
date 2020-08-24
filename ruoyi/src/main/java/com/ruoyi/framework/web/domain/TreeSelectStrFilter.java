package com.ruoyi.framework.web.domain;

import java.util.List;

public class TreeSelectStrFilter {

    @Override
    public boolean equals(Object obj) {
        if(obj == null  ){
            return false;
        }
        List<TreeSelectStr> list =  (List<TreeSelectStr>)obj;
        if(list.size()==0){
            return true;
        }else{
            return false;
        }
    }

}
