package decided.spider.healthcheckin.utils;

import decided.spider.healthcheckin.Main;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;

public class JsRunner {

    public static String getAccuracy(String locateTime, String longitude, String latitude) throws Exception{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        File file = getJsFile();
        FileReader reader = new FileReader(file);
        engine.eval(reader);

        String accuracy = "";
        if (engine instanceof Invocable){
            Invocable invoke = (Invocable) engine;
            accuracy = (String) invoke.invokeFunction("getAccuracy",locateTime + longitude + latitude);
        }
        file.delete();
        return accuracy;
    }

    private static File getJsFile() throws IOException {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream("AccuracyGet.js");
        File file = new File(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getFile()).getParentFile().getPath() + "/js.js");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buffer = new byte[stream.available()];

            while ((len = stream.read(buffer)) != -1){
                outputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            stream.close();
            outputStream.close();
        }
        return file;
    }

}
