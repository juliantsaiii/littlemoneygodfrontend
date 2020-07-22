package com.ruoyi.test;

import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.project.tool.dynamic.domain.Dynamictoken;
import com.ruoyi.project.tool.dynamic.service.IDynamictokenService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {
    @Autowired
    private IDynamictokenService dynamictokenService;

    @Test
    public void getPassword()
    {
        long r = 0;
        r = (long) ((Math.random() + 1) * 1000000000);
        long a = System.currentTimeMillis();
        long b = Long.parseLong(String.valueOf(r).substring(1));
        System.out.println(a);

    }

    @Before
    public  void testBefore()
    {
        System.out.println("before");
    }

    @After
    public  void testAfter()
    {
        System.out.println("after");
    }
}
