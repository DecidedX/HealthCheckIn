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
    }

}
