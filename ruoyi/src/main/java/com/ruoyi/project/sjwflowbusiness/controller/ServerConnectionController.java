package com.ruoyi.project.sjwflowbusiness.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.HttpClientUtil;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.sjwflowbusiness.domain.ServerConnection;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试服务器连接状态
 */
@RestController
@RequestMapping("/sjwflowbusiness/serverconn/")
public class ServerConnectionController {

    @RequestMapping("/GetConnectionStatus")
    public AjaxResult GetConnectionStatus(){
        ClassPathResource classPathResource = new ClassPathResource("/serverconfig.json");
        List<ServerConnection> scList = new ArrayList();

        try {
            InputStream inputStream =classPathResource.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String jsonStr = new String(bytes);

            JSONArray jsonArray = JSON.parseArray(jsonStr);
            System.out.println(jsonArray);

            for(int i=0;i<jsonArray.size();i++){
                ServerConnection sc = new ServerConnection();
                sc.setName(jsonArray.getJSONObject(i).get("name").toString());
                sc.setStatus(HttpClientUtil.testConnect(jsonArray.getJSONObject(i).get("url").toString()));
                scList.add(sc);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.success(scList);
    }
}
