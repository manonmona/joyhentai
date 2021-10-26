package mona.joyhentai.component;

import mona.joyhentai.Util.JoyConfig;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 公用线程池
 * @Author manonmona
 * @@Date 2021/10/26 12:19
 */
@Component
public class CommonUtils {

    // 下载设置
    private static JoyConfig joyConfig;

    // 公用任务线程池
    private static ExecutorService pool;

    public CommonUtils(){
        //获取CPU数量
        int processors = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(processors);
    }

    public static ExecutorService getPool() {
        return CommonUtils.pool;
    }

    public static JoyConfig getJoyConfig() {
        return joyConfig;
    }

    public static void setJoyConfig(JoyConfig joyConfig) {
        CommonUtils.joyConfig = joyConfig;
    }
}
