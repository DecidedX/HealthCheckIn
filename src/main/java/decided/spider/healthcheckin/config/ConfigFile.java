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

    public MsgManager addAccount(String username,String password) throws IOException {
        ConfigWriter writer = new ConfigWriter(file);
        return writer.addAccount(username,password);
    }

    public MsgManager changeToken(String username,String token) throws IOException {
        ConfigWriter writer = new ConfigWriter(file);
        return writer.changeToken(username,token);
    }

    public MsgManager changePassword(String username,String password) throws IOException {
        ConfigWriter writer = new ConfigWriter(file);
        return writer.changePassword(username,password);
    }

//    public MsgManager removeAccount(){
//
//    }
    public JSONObject readAccounts() throws IOException {
        return new ConfigReader(file).read();
    }


}