package decided.spider.healthcheckin.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;

public class DatabaseConfig {

    private final File file;
    private final JSONObject json;

    public DatabaseConfig() throws IOException {
        File path = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).getParentFile();
        this.file = new File(path.getPath() + "/database.conf");
        create();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        json = JSON.parseObject(reader.readLine());
    }

    private void create() throws IOException {
        if (!file.exists()){
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(JSON.toJSONString(new JSONObject(), SerializerFeature.WriteNullStringAsEmpty));
            writer.close();
        }
    }

    public void setDBConfig(String address, String database_name, String username, String password) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        json.put("url","jdbc:mysql://" + address + "/" + database_name);
        json.put("username",username);
        json.put("password",password);
        writer.write(json.toJSONString());
        writer.close();
    }

    public String getUrl(){
        return json.getString("url");
    }

    public String getUsername(){
        return json.getString("username");
    }

    public String getPassword(){
        return json.getString("password");
    }

}
