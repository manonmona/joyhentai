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

    public JoyConfigService(){
        init();
    }

    public void init(){
        this.refJoyConfig();
    }

    /**
     * 保存下载配置信息
     * @return
     */
    public boolean saveJoyConfig(JoyConfig joyConfig){
        try {
            String path = getClass().getResource("/").toURI().getPath();
            ///保存属性到properties文件
            FileOutputStream oFile = new FileOutputStream(path+"config-personal.properties");
            Properties prop = new Properties();
            prop.setProperty("download.dir", joyConfig.getDir());
            prop.setProperty("download.waitTime", joyConfig.getWaitTime()+"");
            prop.setProperty("download.readTime", joyConfig.getReadTime()+"");
            prop.setProperty("download.retry", joyConfig.getRetry()+"");
            prop.setProperty("download.retryEver", joyConfig.isRetryEver()+"");
            prop.store(oFile, "The New properties file");
            oFile.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        CommonUtils.setJoyConfig(joyConfig);
        return true;
    }

    /**
     * 获取下载配置信息
     * @return
     */
    public JoyConfig getJoyConfig(){
        JoyConfig joyConfig = CommonUtils.getJoyConfig();

        if (joyConfig == null) {
            refJoyConfig();
            joyConfig = CommonUtils.getJoyConfig();
        }
        return joyConfig;
    }

    /**
     * 重置配置信息
     */
    public boolean resetJoyConfig(){
        JoyConfig config = new JoyConfig();
        Properties prop = new Properties();
        try {
            InputStream in = getClass().getResourceAsStream("/download-config.properties");
            prop.load(in);
            // 创建个人修改
            String path = getClass().getResource("/").toURI().getPath();
            ///保存属性到properties文件
            FileOutputStream oFile = new FileOutputStream(path+"config-personal.properties");
            prop.store(oFile, "The New properties file");
            oFile.close();

            config.setDir(prop.getProperty("download.dir"));
            config.setWaitTime(Long.parseLong(prop.getProperty("download.waitTime")));
            config.setReadTime(Long.parseLong(prop.getProperty("download.readTime")));
            config.setRetry(Integer.parseInt(prop.getProperty("download.retry")));
            config.setRetryEver(Boolean.parseBoolean(prop.getProperty("download.retryEver")));
            CommonUtils.setJoyConfig(config);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取配置信息
     */
    protected void refJoyConfig(){
        JoyConfig config = new JoyConfig();
        Properties prop = new Properties();
        try {
            String filePath = getClass().getResource("/").toURI().getPath()+"config-personal.properties";
            File file = new File(filePath);

            InputStream in = null;
            if(file.canRead()){
                in = new FileInputStream(file);
                prop.load(in);
            }else {
                in = getClass().getResourceAsStream("/download-config.properties");
                prop.load(in);
                ///保存属性到properties文件
                FileOutputStream oFile = new FileOutputStream(filePath);
                prop.store(oFile, "The New properties file");
                oFile.close();
            }
            config.setDir(prop.getProperty("download.dir"));
            config.setWaitTime(Long.parseLong(prop.getProperty("download.waitTime")));
            config.setReadTime(Long.parseLong(prop.getProperty("download.readTime")));
            config.setRetry(Integer.parseInt(prop.getProperty("download.retry")));
            config.setRetryEver(Boolean.parseBoolean(prop.getProperty("download.retryEver")));
            CommonUtils.setJoyConfig(config);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtils.setJoyConfig(null);
        }
    }

}
