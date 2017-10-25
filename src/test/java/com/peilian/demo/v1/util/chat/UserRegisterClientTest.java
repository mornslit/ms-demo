package com.peilian.demo.v1.util.chat;

import com.peilian.comm.chat.user.RegisterClient;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class UserRegisterClientTest {

    private RestTemplate restTemplate;
//    private RegisterClient registerClient;

    @Value("${chat.url.user_register}")
    private String chatSrvUrl;
    @Value("${chat.token}")
    private String chatToken;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testUserRegister() throws IOException {
        /**
         * 获取聊天服务的UUID,具体可以参考 <VIPSwoole 对接文档 V1.0.0>
         * @param chatSrvUrl 一般来之配置文件 chat.url.user_register   测试服务器为"http://chat.dev.pnlyy.com/v1/user/register"
         * @param chatToken  一般来之配置文件 chat.token 测试服务器为"abc"
         * @param appType        默认微信:0 ,app：1 ,官网：2
         * @param restTemplate   RestTemplate spring的http客户端
         * @return String chatId
         */
        String chatId = RegisterClient.getChatId(chatSrvUrl, chatToken, 0, restTemplate);
        System.out.println(chatId);
        assertNotNull(chatId);
    }
}
