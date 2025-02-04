package decided.spider.healthcheckin.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import decided.spider.healthcheckin.utils.MsgManager;

import java.io.*;

public class ConfigWriter {

    private final File file;
    private final JSONObject json;

    protected ConfigWriter(File file) throws IOException {
        this.json = new ConfigReader(file).read();
        this.file = file;
    }

    protected MsgManager changeToken(String username,String token) throws IOException {
        if (isExist(username)){
            JSONObject account = json.getJSONObject(username);
            account.put("token",token);
            json.put(username,account);
            return write(json);
        }else {
            return MsgManager.ACCOUNT_NOT_EXIST;
        }
    }

    protected MsgManager changePassword(String username,String password) throws IOException {
        if (isExist(username)){
            JSONObject account = json.getJSONObject(username);
            account.put("password",password);
            json.put(username,account);
            return write(json);
        }else {
            return MsgManager.ACCOUNT_NOT_EXIST;
        }
    }

    protected MsgManager changeLocation(String username,String longitude,String latitude) throws IOException {
        if (isExist(username)){
            JSONObject account = json.getJSONObject(username);
            account.put("longitude",longitude);
            account.put("latitude", latitude);
            json.put(username,account);
            return write(json);
        }else {
            return MsgManager.ACCOUNT_NOT_EXIST;
        }
    }

    protected MsgManager addAccount(String username, String password,String longitude,String latitude) throws IOException {
        JSONObject account = new JSONObject();
        account.put("password",password);
        account.put("token", "");
        account.put("longitude",longitude);
        account.put("latitude",latitude);
        json.put(username,account);
        return write(json);
    }

    protected MsgManager removeAccount(String username) throws IOException{
        json.remove(username);
        write(json);
        return MsgManager.ACCOUNT_REMOVE_SUCCESS;
    }

    protected MsgManager setEmail(String username,String email) throws IOException {
        if (isExist(username)){
            JSONObject account = json.getJSONObject(username);
            account.put("email",email);
            json.put(username,account);
            return write(json);
        }else {
            return MsgManager.ACCOUNT_NOT_EXIST;
        }
    }

    private MsgManager write(JSONObject json) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(JSON.toJSONString(json, SerializerFeature.WriteNullStringAsEmpty));
        writer.close();
        return MsgManager.WRITE_SUCCESS;
    }

    private boolean isExist(String username){
        return json.containsKey(username);
    }

}
