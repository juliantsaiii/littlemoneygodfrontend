package com.ruoyi.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void getPassword()
    {
        String password = bCryptPasswordEncoder.encode("admin123");
        System.out.println("加密字符串"+password);
        System.out.println("匹配结果"+bCryptPasswordEncoder.matches("admin123",password));
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
