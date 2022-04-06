package decided.spider.healthcheckin.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import decided.spider.healthcheckin.utils.MsgManager;

import java.io.IOException;
import java.sql.*;

public class JDBCTool {

    private static String url;
    private static String username;
    private static String passwd;
    private static Connection connection;

    static {
        try {
            DatabaseConfig config = new DatabaseConfig();
            url = config.getUrl();
            username = config.getUsername();
            passwd = config.getPassword();
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public JDBCTool() throws SQLException {
        connection = DriverManager.getConnection(url, username, passwd);
    }

    public MsgManager addAccount(String username, String password, String longitude, String latitude, String email) throws SQLException {
        if (getAccount(username).isEmpty()){
            JSONObject json = new JSONObject();
            json.put("username",username);
            json.put("password",password);
            json.put("longitude",longitude);
            json.put("latitude",latitude);
            json.put("email",email);

            String sql = "INSERT INTO users(phone_num, json) VALUES(?, ?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1,username);
            pre.setString(2, JSON.toJSONString(json));
            pre.execute();
            return MsgManager.WRITE_SUCCESS;
        }else {
            return MsgManager.ACCOUNT_EXISTED;
        }
    }

    public MsgManager removeAccount(String username) throws SQLException {
        if (!getAccount(username).isEmpty()){
            String sql = "DELETE FROM users WHERE phone_num=?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1,username);
            pre.execute();
            return MsgManager.ACCOUNT_REMOVE_SUCCESS;
        }else {
            return MsgManager.ACCOUNT_NOT_EXIST;
        }
    }

    public MsgManager changePassword(String username,String password) throws SQLException {
        if (!getAccount(username).isEmpty()){
            JSONObject json = getAccount(username);
            json.put("password",password);
            update(username,json);
            return MsgManager.WRITE_SUCCESS;
        }else {
            return MsgManager.ACCOUNT_NOT_EXIST;
        }
    }

    public MsgManager changeLocation(String username,String longitude,String latitude) throws SQLException {
        if (!getAccount(username).isEmpty()){
            JSONObject json = getAccount(username);
            json.put("longitude",longitude);
            json.put("latitude",latitude);
            update(username,json);
            return MsgManager.WRITE_SUCCESS;
        }else {
            return MsgManager.ACCOUNT_NOT_EXIST;
        }
    }

    public MsgManager setEmail(String username,String email) throws SQLException {
        if (!getAccount(username).isEmpty()){
            JSONObject json = getAccount(username);
            json.put("email",email);
            update(username,json);
            return MsgManager.WRITE_SUCCESS;
        }else {
            return MsgManager.ACCOUNT_NOT_EXIST;
        }
    }

    private void update(String username,JSONObject json) throws SQLException {
        String sql = "UPDATE users SET json=? WHERE phone_num=?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1,JSON.toJSONString(json));
        pre.setString(2,username);
        pre.execute();
    }

    public JSONObject getAccount(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE phone_num=?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1,username);
        ResultSet result = pre.executeQuery();
        JSONObject json = new JSONObject();
        while (result.next()){
            json = JSON.parseObject(result.getString("json"));
        }
        return json;
    }

    public JSONObject readAccounts() throws SQLException {
        JSONObject json = new JSONObject();
        String sql = "SELECT * FROM users";
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet result = pre.executeQuery();
        while (result.next()){
            json.put(result.getString("phone_num"),JSON.parseObject(result.getString("json")));
        }
        return json;
    }

    public void close() throws SQLException {
        connection.close();
    }

}
