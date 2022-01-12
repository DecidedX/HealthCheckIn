package decided.spider.healthcheckin.network;

import com.alibaba.fastjson.JSONObject;
import decided.spider.healthcheckin.utils.MsgManager;
import decided.spider.healthcheckin.utils.JsRunner;
import decided.spider.healthcheckin.utils.RandomBodyT;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CheckIn {

    private final Connection connection;

    public CheckIn(Connection connection){
        this.connection = connection;
    }

    public MsgManager execute(String longitude,String latitude) throws Exception {
        if (isChecked()){
            return MsgManager.IS_CHECKED;
        }else {
            JSONObject location = MapLocation.getAddress(connection,longitude,latitude);
            writeInformation(location);

            return saveInformation();
        }
    }

    private boolean isChecked() throws IOException {
        connection.url("http://gaut.shzu.edu.cn/gxqtxiaoyingyong/zyq/viewZyqJianKangDaKaForApp.do?client=gxqtapp");
        Connection.Response response = connection.method(Connection.Method.POST).execute();
        JSONObject json = JSONObject.parseObject(response.body());
        return !(json.getJSONObject("data").getJSONObject("jkdk").getString("tiwen") == null);
    }

    private MsgManager saveInformation() throws Exception {
        connection.url("http://gaut.shzu.edu.cn/gxqtxiaoyingyong/zyq/saveaddZyqJianKangDaKaForApp.do?client=gxqtapp");
        Connection.Response response = connection.method(Connection.Method.POST).execute();
        return (JSONObject.parseObject(response.body()).getIntValue("code") == 0) ? MsgManager.CHECK_IN_SUCCESS : MsgManager.CHECK_IN_FAILURE;
    }

    private void writeInformation(JSONObject location) throws Exception {
        Map<String,String> data = new HashMap<>();
        Thread.sleep(1000);
        System.out.println("打卡地点:" + location.getString("address"));
        data.put("dwaddress",location.getString("address"));
        data.put("jkzk","1");
        data.put("bszz","");
        data.put("tiwen", RandomBodyT.getRandomBodyTemperature());
        data.put("tiwen2",RandomBodyT.getRandomBodyTemperature());
        data.put("tiwen3",RandomBodyT.getRandomBodyTemperature());
        data.put("bz","");
        data.put("wxprovience",location.getString("province"));
        data.put("wxcity",location.getString("district"));
        data.put("longitude",location.getString("longitude"));
        data.put("latitude",location.getString("latitude"));
        data.put("dwtime",location.getString("dwtime"));
        data.put("deviceid","");
        data.put("accuracy", JsRunner.getAccuracy(location.getString("dwtime"),location.getString("longitude"),location.getString("latitude")));
        connection.data(data);
    }

}
