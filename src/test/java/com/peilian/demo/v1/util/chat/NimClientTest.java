package com.peilian.demo.v1.util.chat;

import com.peilian.comm.util.IdGen;
import com.peilian.comm.client.nim.NimClient;
import com.peilian.comm.client.nim.NimResponse;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class NimClientTest {

    @Before
    public void init() {
    }
//    @Test
//    public void testSha1(){
////        curl -X POST
//// -H "AppKey: go9dnk49bkd9jd9vmel1kglw0803mgq3"
//// -H "Nonce: 4tgggergigwow323t23t"
//// -H "CurTime: 1443592222"
//// -H "CheckSum: 9e9db3b6c9abb2e1962cf3e6f7316fcc55583f86"
//// -H "Content-Type: application/x-www-form-urlencoded" -d 'accid=zhangsan&name=zhangsan' 'https://api.netease.im/nimserver/user/create.action'
//        String appKey  ="go9dnk49bkd9jd9vmel1kglw0803mgq3";
//        String nonce="4tgggergigwow323t23t";
//        Long curTime=1443592222L;
//        String str=appKey+nonce+curTime;
//        System.out.println("str="+str);
//        String checkSum="9e9db3b6c9abb2e1962cf3e6f7316fcc55583f86";
//
//        String sha1Str= DigestUtils.sha1Hex(str);
//        System.out.println("sha1Str="+sha1Str);
//        assertNotNull(sha1Str);
////        assertEquals(checkSum,sha1Str);
//        sha1Str=getCheckSum(appKey,nonce,curTime+"");
//        System.out.println("sha1Str="+sha1Str);
//        assertEquals(checkSum,sha1Str);
//
//    }
//
//    public static String getCheckSum(String appSecret, String nonce, String curTime) {
//        return encode("sha1", appSecret + nonce + curTime);
//    }
//    private static String encode(String algorithm, String value) {
//        if (value == null) {
//            return null;
//        }
//        try {
//            MessageDigest messageDigest
//                    = MessageDigest.getInstance(algorithm);
//            messageDigest.update(value.getBytes());
//            return getFormattedText(messageDigest.digest());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private static String getFormattedText(byte[] bytes) {
//        int len = bytes.length;
//        StringBuilder buf = new StringBuilder(len * 2);
//        for (int j = 0; j < len; j++) {
//            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
//            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
//        }
//        return buf.toString();
//    }
//    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
//            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
//
//    @Test
//    public void test() throws IOException {
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        String url = "https://api.netease.im/nimserver/user/create.action";
//        HttpPost httpPost = new HttpPost(url);
//
//        String appKey = "ff0f9a72db5b719dad88ce9dd23c16b7";
//        String appSecret = "afcc2f923f42";
////        String nonce =  "12345";
//        String nonce =  ""+ new IdGen().next();
//        String curTime = String.valueOf((new Date()).getTime() / 1000L);
//        String checkSum = getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码
//
//        // 设置请求的header
//        httpPost.addHeader("AppKey", appKey);
//        httpPost.addHeader("Nonce", nonce);
//        httpPost.addHeader("CurTime", curTime);
//        httpPost.addHeader("CheckSum", checkSum);
//        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // 设置请求的参数
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("accid", "helloworld"));
//        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
//
//        // 执行请求
//        HttpResponse response = httpClient.execute(httpPost);
//
//        System.out.println(response.getStatusLine());
//        // 打印执行结果
//        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
//    }
    @Value("${nim.appKey}")
    private String appKey;
    @Value("${nim.appSecret}")
    private String appSecret;
    @Value("${nim.url.create}")
    private String url;

    @Test
    public void testClient() throws IOException {
        RestTemplate restTemplate= new RestTemplate();
        //手机号
        String mobile="12345678901232";
        //自己产生的token随机性
        String token= new IdGen().next()+"";
        //要判断 response.code==200
        NimResponse response= NimClient.getToken(restTemplate,url,appKey,appSecret,mobile,token);
        if (response.getCode()==200){
            //正常生成了token。
        }
        System.out.println(response);

    }
}
