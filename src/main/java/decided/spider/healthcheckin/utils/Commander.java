package decided.spider.healthcheckin.utils;

import com.alibaba.fastjson.JSONObject;
import decided.spider.healthcheckin.config.ConfigFile;

import java.io.IOException;
import java.util.Map;

public class Commander {

    public Commander(String[] args) throws IOException {
        ConfigFile file = new ConfigFile();
        switch (args[0]){
            case "add":
                if (args.length == 5){
                    System.out.println(file.addAccount(args[1],args[2],args[3],args[4]));
                }else {
                    outError();
                }
                break;
            case "del":
                if (args.length == 2){
                    System.out.println(file.removeAccount(args[1]));
                }else {
                    outError();
                }
                break;
            case "cpw":
                if (args.length == 3){
                    System.out.println(file.changePassword(args[1],args[2]));
                }else {
                    outError();
                }
                break;
            case "cl":
                if (args.length == 4){
                    System.out.println(file.changeLocation(args[1],args[2],args[3]));
                }else {
                    outError();
                }
                break;
            case "all":
                if (args.length == 1){
                    JSONObject accounts = file.readAccounts();
                    for (Map.Entry<String, Object> e:accounts.entrySet()){
                        System.out.println(e.getKey());
                    }
                }else {
                    outError();
                }
                break;
            default:
                outError();
                break;
        }
    }

    private void outError(){
        System.out.println("ERROR");
    }

}
