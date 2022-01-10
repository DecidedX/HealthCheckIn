package decided.spider.healthcheckin.network;

import com.alibaba.fastjson.JSON;
import decided.spider.healthcheckin.utils.RSAEncryption;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginYiban {

    private final String username;
    private final String password;
    private boolean login;

    public LoginYiban(String username,String password){
        this.username = username;
        this.password = password;
    }

    public LoginCheckIn login() throws IOException {
        Connection connection = Jsoup.connect("https://www.yiban.cn/");
        getOriginCookies(connection);
        connection.url("https://www.yiban.cn/login?go=https%3A%2F%2Fwww.yiban.cn%2F");
        Connection.Response response = connection.method(Connection.Method.GET).execute();
        String[] keys = getPublicKeys(response.parse().getElementById("login-pr"));
        Map<String,String> cookies = doLogin(response.cookies(),keys);
        connection.url("https://www.yiban.cn/ajax/my/getLogin");
        connection.cookie("yiban_user_token",cookies.get("yiban_user_token"));
        return new LoginCheckIn(connection);
    }

    public boolean isLogin() {
        return login;
    }

    private Map<String,String> doLogin(Map<String,String> cookies, String[] keys) throws IOException {
        Connection conn = Jsoup.connect("https://www.yiban.cn/login/doLoginAjax");
        conn.cookies(cookies);
        Map<String,String> data = new HashMap<>();
        data.put("password",RSAEncryption.encrypt(keys[0],password));
        data.put("account",username);
        data.put("keysTime",keys[1]);
        data.put("token","");
        conn.data(data);
        Connection.Response response = conn.ignoreContentType(true).method(Connection.Method.POST).execute();
        login = JSON.parseObject(response.body()).getIntValue("code") == 200;
        return response.cookies();
    }

    private String[] getPublicKeys(Element login_pr) throws IOException {
        String keys = login_pr.attr("data-keys").substring(26).replace("\n-----END PUBLIC KEY-----","");
        String keys_time = login_pr.attr("data-keys-time");
        return new String[]{keys, keys_time};
    }

    private void getOriginCookies(Connection connection) throws IOException {
        setHeaders(connection);
        connection.ignoreContentType(true).method(Connection.Method.GET).execute();
    }

    private void setHeaders(Connection connection) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        headers.put("Cache-Control", "max-age=0");
        headers.put("Connection", "keep-alive");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.put("Host", "www.yiban.cn");
        headers.put("Sec-Fetch-Dest", "document");
        headers.put("Sec-Fetch-Mode", "navigate");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("Sec-Fetch-User", "?1");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36 Edg/97.0.1072.55");
        headers.put("sec-ch-ua", "\" Not;A Brand\";v=\"99\", \"Microsoft Edge\";v=\"97\", \"Chromium\";v=\"97\"");
        headers.put("sec-ch-ua-mobile", "?0");
        headers.put("sec-ch-ua-platform", "\"Windows\"");
        connection.headers(headers);
    }

}
