package decided.spider.healthcheckin;

import com.alibaba.fastjson.JSONObject;
import decided.spider.healthcheckin.config.JDBCTool;
import decided.spider.healthcheckin.email.EmailSender;
import decided.spider.healthcheckin.network.CheckIn;
import decided.spider.healthcheckin.network.LoginCheckIn;
import decided.spider.healthcheckin.network.LoginYiban;
import decided.spider.healthcheckin.utils.MsgManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckThread implements Runnable{

    private static final long DAY = 24*60*60*1000;
    private static final String NAME = "CheckIn";
    private static JSONObject accounts = null;

    public void run() {
        System.out.println("启动打卡线程");
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY,0);
        today.set(Calendar.MINUTE,5);
        today.set(Calendar.SECOND,0);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    startCheck();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("打卡异常，十小时后重试");
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                startCheck();
                            } catch (Exception ex) {
                                for (Map.Entry<String, Object> e : accounts.entrySet()){
                                    String email = ((JSONObject) e.getValue()).getString("email");
                                    if (email != null){
                                        try {
                                            EmailSender.send(email);
                                        } catch (IOException exc) {
                                            exc.printStackTrace();
                                        }
                                    }
                                }
                                ex.printStackTrace();
                            }
                        }
                    },10*60*60*1000);
                }
            }
        },today.getTime(),DAY);

    }

    public static void startCheck() throws Exception {
        System.out.println("====================开始自动打卡====================");
        JDBCTool jdbcTool = new JDBCTool();
        accounts = jdbcTool.readAccounts();
        for (Map.Entry<String, Object> e : accounts.entrySet()) {
            String username = e.getKey();
            JSONObject data = (JSONObject) e.getValue();
            String password = data.getString("password");
            String longitude = data.getString("longitude");
            String latitude = data.getString("latitude");
//            String token = data.getString("token");
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(ft.format(new Date()) + "—正在尝试为:[" + username + "] 打卡...");
            LoginYiban yiban = new LoginYiban(username, password);
            LoginCheckIn loginCheckIn = yiban.login();
            if (yiban.isLogin()) {
                CheckIn checkIn = loginCheckIn.login();
                if (loginCheckIn.isLogin()) {
                    System.out.println("打卡结果：" + checkIn.execute(longitude, latitude));
                } else {
                    System.out.println("token过期");
                }
            } else {
                System.out.println("打卡结果：" + MsgManager.YIBAN_LOGIN_FAILURE);
            }

            System.out.println("__________________________________________________");
            Thread.sleep(5000);
        }
        System.out.println("====================自动打卡完成====================");
    }

    public void start(){
        Thread thread = new Thread(this,NAME);
        thread.start();
    }

}
