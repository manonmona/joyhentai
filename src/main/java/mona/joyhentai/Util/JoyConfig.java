package mona.joyhentai.Util;

/**
 * @Author manonmona
 * @@Date 2021/10/26 13:40
 */
public class JoyConfig {

    @Override
    public String toString() {
        return "JoyConfig{" +
                "dir='" + dir + '\'' +
                ", waitTime=" + waitTime +
                ", readTime=" + readTime +
                ", retry=" + retry +
                ", retryEver=" + retryEver +
                '}';
    }

    // 下载路径
    private String dir;
    // 请求等待时间（毫秒）
    private long waitTime;
    // 阅读等待时间（毫秒）
    private long readTime;
    // 失败后重试的次数
    private int retry;
    // 失败后是否一直重试直至成功，默认false
    private boolean retryEver;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public long getReadTime() {
        return readTime;
    }

    public void setReadTime(long readTime) {
        this.readTime = readTime;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public boolean isRetryEver() {
        return retryEver;
    }

    public void setRetryEver(boolean retryEver) {
        this.retryEver = retryEver;
    }
}
