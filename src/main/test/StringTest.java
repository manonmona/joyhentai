import mona.joyhentai.Util.JoyConfig;
import mona.joyhentai.service.JoyConfigService;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @Author manonmona
 * @@Date 2021/10/25 19:53
 */
public class StringTest {
    public static void main(String[] args) throws IOException{
        /*String str  = "https://zh.joyhentai.fun/detail/2044036o377594.html";
        System.out.println(str.substring(0,str.indexOf("/")));*/

        JoyConfigService down = new JoyConfigService();
        JoyConfig joyConfig = down.getJoyConfig();
        System.out.println(joyConfig);
    }
}
