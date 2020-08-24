package com.anshare.servermonitor.controller;

/**
 * 服务器监控
 * @author littlemoneygod
 * @since 2020/08/24
 */
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
    @PostMapping("/server")
    public ResultT<Server> getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return ResultT.ok(server);
    }

}