package decided.spider.healthcheckin;

import com.alibaba.fastjson.JSONObject;
import decided.spider.healthcheckin.config.ConfigFile;
import decided.spider.healthcheckin.network.CheckIn;
import decided.spider.healthcheckin.network.LoginCheckIn;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckThread implements Runnable{

    private static final long DAY = 24*60*60*1000;
    private static final String NAME = "CheckIn";

    public void run() {
        System.out.println("启动打卡线程");
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY,0);
        today.set(Calendar.MINUTE,16);
        today.set(Calendar.SECOND,0);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    System.out.println("====================开始自动打卡====================");
                    ConfigFile config = new ConfigFile();
                    JSONObject accounts = config.readAccounts();
                    for (Map.Entry<String, Object> e:accounts.entrySet()){
                        String username = e.getKey();
                        JSONObject data = (JSONObject) e.getValue();
                        String password = data.getString("password");
                        String token = data.getString("token");
                        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                        System.out.println(ft.format(new Date()) + "—正在尝试为:[" + username + "] 打卡...");
                        LoginCheckIn loginCheckIn = new LoginCheckIn(token);
                        CheckIn checkIn = loginCheckIn.login();
                        if (loginCheckIn.isLogin()){
                            System.out.println("打卡结果：" + checkIn.execute());
                        }else {
                            System.out.println("token过期");
                        }
                        System.out.println("__________________________________________________");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("====================自动打卡完成====================");
            }
        },today.getTime(),DAY);

    }

    public void start(){
        Thread thread = new Thread(this,NAME);
        thread.start();
    }

}
