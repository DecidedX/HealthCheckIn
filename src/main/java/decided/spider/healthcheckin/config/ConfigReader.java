package decided.spider.healthcheckin.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class ConfigReader {

    private final File file;

    protected ConfigReader(File file){
        this.file = file;
    }

    protected JSONObject read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return JSON.parseObject(reader.readLine());
    }

}
