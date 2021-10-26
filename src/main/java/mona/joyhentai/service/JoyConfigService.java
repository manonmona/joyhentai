package mona.joyhentai.service;

import mona.joyhentai.Util.JoyConfig;
import mona.joyhentai.component.CommonUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @Author manonmona
 * @@Date 2021/10/26 13:29
 */
@Service
public class JoyConfigService {

    /**
     * 保存下载配置信息
     * @return
     */
    public boolean saveDownloadConfig(){
        return false;
    }

    /**
     * 获取下载配置信息
     * @return
     */
    public JoyConfig getJoyConfig() throws IOException {
        JoyConfig joyConfig = CommonUtils.getJoyConfig();

        if (joyConfig == null) {
            refJoyConfig();
            joyConfig = CommonUtils.getJoyConfig();
        }
        return joyConfig;
    }

    /**
     * 获取配置信息
     */
    protected void refJoyConfig(){
        JoyConfig config = new JoyConfig();
        Properties prop = new Properties();
        try {
            InputStream in = getClass().getResourceAsStream("/download-config.properties");
            prop.load(in);
            config.setDir(prop.getProperty("download.dir"));
            config.setWaitTime(Long.parseLong(prop.getProperty("download.waitTime")));
            config.setReadTime(Long.parseLong(prop.getProperty("download.readTime")));
            config.setRetry(Integer.parseInt(prop.getProperty("download.retry")));
            config.setRetryEver(Boolean.parseBoolean(prop.getProperty("download.retryEver")));
            CommonUtils.setJoyConfig(config);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            CommonUtils.setJoyConfig(null);
        }
    }

}
