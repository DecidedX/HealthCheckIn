package decided.spider.healthcheckin.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import decided.spider.healthcheckin.utils.MsgManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigFile {

    protected final File file;

    public ConfigFile() throws IOException {
        File file = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).getParentFile();
        this.file = new File(file.getPath() + "/data.conf");
        create();
    }

    private void create() throws IOException {
        if (!file.exists()){
            try {
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(JSON.toJSONString(new JSONObject(), SerializerFeature.WriteNullStringAsEmpty));
                writer.close();
            }catch (Exception e){
                throw e;
            }
        }

    }

    public MsgManager addAccount(String username,String password,String longitude,String latitude) throws IOException {
        ConfigWriter writer = new ConfigWriter(file);
        return writer.addAccount(username,password,String.valueOf(Float.parseFloat(longitude)-0.0067f),String.valueOf(Float.parseFloat(latitude)-0.0064f));
    }

    public MsgManager changeToken(String username,String token) throws IOException {
        ConfigWriter writer = new ConfigWriter(file);
        return writer.changeToken(username,token);
    }

    public MsgManager changePassword(String username,String password) throws IOException {
        ConfigWriter writer = new ConfigWriter(file);
        return writer.changePassword(username,password);
    }

    public MsgManager changeLocation(String username,String longitude,String latitude) throws IOException {
        ConfigWriter writer = new ConfigWriter(file);
        return writer.changeLocation(username,String.valueOf(Float.parseFloat(longitude)-0.0067f),String.valueOf(Float.parseFloat(latitude)-0.0064f));
    }

    public MsgManager removeAccount(String username) throws IOException {
        ConfigWriter writer = new ConfigWriter(file);
        return writer.removeAccount(username);
    }

    public JSONObject readAccounts() throws IOException {
        return new ConfigReader(file).read();
    }

    public MsgManager setEmail(String username,String email) throws IOException {
        ConfigWriter writer = new ConfigWriter(file);
        return writer.setEmail(username, email);
    }


}
