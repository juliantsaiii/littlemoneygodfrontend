package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  littlemoneygod启动成功   ლ(´ڡ`ლ)ﾞ  \n"+
                ".__  .__  __    __  .__                                                              .___\n" +
                "|  | |__|/  |__/  |_|  |   ____   _____   ____   ____   ____ ___.__. ____   ____   __| _/\n" +
                "|  | |  \\   __\\   __\\  | _/ __ \\ /     \\ /  _ \\ /    \\_/ __ <   |  |/ ___\\ /  _ \\ / __ | \n" +
                "|  |_|  ||  |  |  | |  |_\\  ___/|  Y Y  (  <_> )   |  \\  ___/\\___  / /_/  >  <_> ) /_/ | \n" +
                "|____/__||__|  |__| |____/\\___  >__|_|  /\\____/|___|  /\\___  > ____\\___  / \\____/\\____ | \n" +
                "                              \\/      \\/            \\/     \\/\\/   /_____/             \\/ ");
    }
}
