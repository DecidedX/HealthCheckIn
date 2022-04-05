package decided.spider.healthcheckin.email;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;

public class EmailConfig {

    private final File file;
    private final JSONObject json;

    public EmailConfig() throws IOException {
        File path = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).getParentFile();
        this.file = new File(path.getPath() + "/mail.conf");
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

    public void setEmailConfig(String server, String port, String ssl, String email, String password) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        json.put("server",server);
        json.put("port",port);
        json.put("ssl",ssl);
        json.put("email",email);
        json.put("password",password);
        writer.write(json.toJSONString());
        writer.close();
    }

    public String getServer(){
        return json.getString("server");
    }

    public String getPort(){
        return json.getString("port");
    }

    public String getSsl(){
        return json.getString("ssl");
    }

    public String getEmail(){
        return json.getString("email");
    }

    public String getPassword(){
        return json.getString("password");
    }

}
