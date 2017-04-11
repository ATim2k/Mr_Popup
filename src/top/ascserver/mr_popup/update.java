package top.ascserver.mr_popup;

import cn.nukkit.utils.TextFormat;
import sun.misc.BASE64Decoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by WE·JianGe on 2017/3/3.
 */
public class update {
    public boolean updateCheck(){
        try {
            String a;
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer("aHR0cDovL21yc2t5Lnh5ei9wbHVnaW5zL21ycG9wdXB1cGRhdGUuaHRtbA==");
            String url = new String(b,"utf-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream(), "GB2312"));//GB2312可以根据需要替换成要读取网页的编码
            while ((a = in.readLine()) != null) {
                if (a.equals("Mr_Popup v1.3.4")){
                    Timers.getPlugin().getLogger().info(TextFormat.GREEN + "插件已是最新版本,无需更新!");
                    return true;
                }else{
                    Timers.getPlugin().getLogger().info(TextFormat.GOLD + "插件有新版本发布了，快去zxda下载吧！");
                    updateMessage();
                    return false;
                }
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return true;
    }
    public void updateMessage(){
        try {
            String a;
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer("aHR0cDovL21yc2t5Lnh5ei9wbHVnaW5zL21ycG9wdXB1cGRhdGVtZXNzYWdlLmh0bWw=");
            String url = new String(b,"utf-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream(), "GB2312"));//GB2312可以根据需要替换成要读取网页的编码
            while ((a = in.readLine()) != null) {
                Timers.getPlugin().getLogger().info(TextFormat.YELLOW + a);
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }
}
