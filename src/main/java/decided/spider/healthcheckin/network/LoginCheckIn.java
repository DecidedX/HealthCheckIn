package decided.spider.healthcheckin.network;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginCheckIn {

    private Connection connection;
    private final String loginToken;
    private boolean login;

    public LoginCheckIn(String loginToken){
        this.loginToken = loginToken;
    }

    public LoginCheckIn(Connection connection){
        this.connection = connection;
        this.loginToken = "";
    }

    public CheckIn login() throws IOException {
        Connection.Response response;
        if (loginToken.equals("")){
            connection.url("https://f.yiban.cn/iapp377994");
        }else {
            connection = putCookies(putHeaders(Jsoup.connect("https://f.yiban.cn/iapp377994"))).ignoreContentType(true);
        }
        response = connection.execute();
        login = response.cookies().containsKey("gautappin");

        return new CheckIn(connection);
    }

    public boolean isLogin(){
        return login;
    }

    private Connection putHeaders(Connection connection){
        Map<String,String> headers = new HashMap<>();
        headers.put("Host","f.yiban.cn");
        headers.put("Connection","keep-alive");
        headers.put("Upgrade-Insecure-Requests","1");
        headers.put("User-Agent","Mozilla/5.0 (Linux; Android 11; M2007J3SC Build/RKQ1.200826.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/90.0.4430.210 Mobile Safari/537.36 yiban_android/5.0");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.put("authorization","Bearer " + loginToken);
        headers.put("appversion","5.0");
        headers.put("logintoken",loginToken);
        headers.put("signature","cE3/yE7qAMPxySqyR1ZATAGIdcmolEl8Cx8QEZQKS1RRwLbAQ9mIdZiqGntV+7Ow9j9IUIMJPlqdacgM7lDU2w");
        headers.put("X-Requested-With","com.yiban.app");
        headers.put("Sec-Fetch-Site","none");
        headers.put("Sec-Fetch-Mode","navigate");
        headers.put("Sec-Fetch-User","?1");
        headers.put("Sec-Fetch-Dest","document");
        headers.put("Accept-Encoding","gzip, deflate");
        headers.put("Accept-Language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        connection.headers(headers);
        return connection;
    }

    private Connection putCookies(Connection connection){
        Map<String,String> cookies = new HashMap<>();
        cookies.put("logintoken",loginToken);
        cookies.put("client","android");
        connection.cookies(cookies);
        return connection;
    }

}
