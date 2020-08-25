package com.anshare.servermonitor.controller;

import com.anshare.servermonitor.Service.Server;
import com.anshare.servermonitor.entity.AjaxResult;
import org.springframework.web.bind.annotation.*;

/**
 * 服务器监控
 * @author littlemoneygod
 * @since 2020/08/24
 */
@CrossOrigin
@RestController
@RequestMapping("/monitor")
public class ServerController {

    /**
     * 获取服务器监控信息
     * @param
     * @return com.company.api.base.ResultT<com.company.api.server.Server>
     * @author zengxueqi
     * @since 2020/7/14
     */
    @GetMapping("/server")
    public AjaxResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }

}