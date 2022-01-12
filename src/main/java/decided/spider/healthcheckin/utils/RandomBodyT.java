package decided.spider.healthcheckin.utils;

import java.util.Random;

public class RandomBodyT {

    public static String getRandomBodyTemperature(){
        Random random = new Random();
        float r = random.nextFloat() + 35.7f;
        return Float.toString(r).substring(0,4);
    }

}
