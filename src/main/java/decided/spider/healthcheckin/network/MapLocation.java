package decided.spider.healthcheckin.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapLocation {

    public static JSONObject getAddress(Connection connection,String longitude, String latitude) throws IOException {

        connection.url("http://gaut.shzu.edu.cn/gxqtxiaoyingyong/zyq/getGdAddressByLatLog.do?client=gxqtapp");
        Map<String,String> data = new HashMap<>();
        data.put("latitude",latitude);
        data.put("longitude",longitude);
        data.put("needTrans","0");
        connection.data(data);
        Connection.Response response = connection.ignoreContentType(true).method(Connection.Method.POST).execute();
        return JSON.parseObject(response.body()).getJSONObject("data");

    }

}
