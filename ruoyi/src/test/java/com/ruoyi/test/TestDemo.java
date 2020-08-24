package com.ruoyi.test;

import com.ruoyi.common.utils.file.SambFileUtil;
import com.ruoyi.project.tool.dynamic.service.IDynamictokenService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {
    @Autowired
    private IDynamictokenService dynamictokenService;

    @Autowired
    private SambFileUtil sambFileUtil;

    @Test
    public void getPassword()
    {
        try {
            sambFileUtil.testSmb();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
