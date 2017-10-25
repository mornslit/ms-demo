package com.peilian.demo.v1.mq;

import com.peilian.demo.v1.entity.Country;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class testDemo {
    @Autowired
    private CountrySender sender;

    @Autowired
    private TmpSender tmpSender;
    @Test
    public void hello() throws Exception {
        //发送 queue
        sender.send();
        //成功后 看log,会有 ## Receiver :
    }
    @Test
    public void sendObjectJson() throws Exception {
        //发送 queue
        Country country=new Country();
        country.setCountryName("somewhere");
        country.setCountryCode("A");
        for (int i=0;i<10;i++) {
            sender.send(country);
        }
        //成功后 看log,会有 ## Receiver :
    }
    @Test
    public void sendObjectJson2() throws Exception {
        //发送 queue
        Tmp tmp=new Tmp();
        tmp.setClassId(1234567890L);
        tmp.setUserId(987654321L);
        for (int i=0;i<10;i++) {
            tmpSender.send(tmp);
        }
        //成功后 看log,会有 ## Receiver :
    }
}
