package decided.spider.healthcheckin;

import decided.spider.healthcheckin.utils.Commander;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

//        启动程序时启动线程，后主线程接收指令写入配置文件
        CheckThread thread = new CheckThread();
        thread.start();

        while (true){
            Scanner scanner = new Scanner(System.in);
            String[] command = {};
            if (scanner.hasNextLine()){
                command = scanner.nextLine().split("\\s");
            }
            new Commander(command);
        }


//        ConfigFile config = new ConfigFile();
//        config.addAccount("17356095404","20011118wt.","117.114927","30.516808");
//        config.changeToken("17356095404","a27256beae14480f7086bc79a2ea8b75");
    }

}
