package decided.spider.healthcheckin;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import decided.spider.healthcheckin.config.ConfigFile;
import decided.spider.healthcheckin.network.CheckIn;
import decided.spider.healthcheckin.network.LoginCheckIn;
import decided.spider.healthcheckin.utils.JsRunner;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws Exception {

//        try (WebClient webClient = new WebClient(BrowserVersion.CHROME)){
//            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//            webClient.getOptions().setJavaScriptEnabled(true);
//            webClient.getOptions().setThrowExceptionOnScriptError(false);
//            webClient.setCssErrorHandler(new SilentCssErrorHandler());
//            webClient.getOptions().setCssEnabled(false);
//            webClient.getOptions().setTimeout(3000);
//            webClient.getOptions().setRedirectEnabled(true);
//
//            HtmlPage page = webClient.getPage("https://www.yiban.cn/");
//
//            HtmlPage loginPage = ((DomElement)page.getByXPath("/html/body/header/div/nav/div/div[2]/aside/span/a[2]").get(0)).click();
//
//            while (loginPage.getByXPath("/html/body/main/div[1]/div[2]/ul/li[1]/div[6]/a").size() == 0){
//                Thread.sleep(500);
//            }
//
//            HtmlForm form = (HtmlForm) loginPage.getByXPath("/html/body/main/div[1]/div[2]/ul/li[1]/div[6]/a").get(0);
//
//            HtmlTextInput username = (HtmlTextInput) form.
//
//
//
//        }

//        启动程序时启动线程，后主线程接收指令写入配置文件
//        CheckThread thread = new CheckThread();
//        thread.start();

//        ConfigFile config = new ConfigFile();
//        config.addAccount("17356095404","2333");
//        config.changeToken("17356095404","a27256beae14480f7086bc79a2ea8b75");
    }

}
