package mona.joyhentai.service.impl;

import mona.joyhentai.component.CommonUtils;
import mona.joyhentai.model.Books;
import mona.joyhentai.model.BooksPages;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @Author manonmona
 * @@Date 2021/10/26 13:15
 */
@Service
public class AbstractJoyhentaiDownloadService {


    public void threadPoolDownload(Books books , List<BooksPages> booksPagesList){

        ExecutorService pool = CommonUtils.getPool();

        // 去除特殊字符得到的名称
        String dirName = replacePathChar(books.getName());

        String pathName = CommonUtils.getJoyConfig().getDir()+"\\["+books.getPages()+"P]"+dirName;
        File imagePathFile = new File(pathName);

        if (!imagePathFile.exists()) {
            imagePathFile.mkdir();
        }

        for (int i = 0; i < booksPagesList.size(); i++) {

            BooksPages booksPages = booksPagesList.get(i);

            File imageFile = new File(imagePathFile+"\\"+ booksPages.getName()+ booksPages.getSuffix());
            // 如果当前文件已存在则不执行操作
            if(imageFile.canWrite()){
                continue;
            }

            pool.execute(() -> {
                boolean result = false;
                ever:do {
                    // 重试次数
                    for (int j = 0; j < CommonUtils.getJoyConfig().getRetry(); j++) {
                        try {
                            // 下载图片
                            result = download(booksPages, pathName);
                            if(!result){
                                String suffix = booksPages.getSuffix();
                                String url = booksPages.getUrl();
                                if(suffix.equals(".png")){
                                    booksPages.setSuffix(".jpg");
                                    booksPages.setUrl(url.replace(".png",".jpg"));
                                }else if(suffix.equals(".jpg")){
                                    booksPages.setSuffix(".png");
                                    booksPages.setUrl(url.replace(".jpg",".png"));
                                }
                                result = download(booksPages, pathName);
                            }
                        } catch (Exception e) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String dateTime = dateFormat.format(new Date());
                            System.out.println(e.getMessage()+"：["+books.getId()+"]"+dirName+"\t"+ booksPages.getName()+"页下载失败   ----->"+dateTime);

                        }
                        // 如果执行完，则退出
                        if(result){
                            break ever;
                        }
                    }
                }while (CommonUtils.getJoyConfig().isRetryEver());

            });

        }
        // 关闭
//        pool.shutdown();
    }

    /**
     * 下载图片
     * @param booksPages
     * @throws Exception
     */
    protected boolean download(BooksPages booksPages , String imagePathFile) throws Exception{
        // 1.创建URL
        URL url = new URL(booksPages.getUrl());
        // 2.使用URL打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 3.设置超时时间，访问方式postget，伪装浏览器
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        // 4.获取链接状态，200为成功，403表示访问受限，不成功则退出
        if(conn.getResponseCode()!=200 && conn.getResponseCode()!=301){
//            System.out.println(conn.getResponseCode()+"连接退出");
            return false;
        }
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = this.readInputStream(inStream);
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File(imagePathFile+"\\"+booksPages.getName()+booksPages.getSuffix());
        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();
        return true;
    }

    /**
     * 读取字节数就
     * @param inStream
     * @return
     * @throws Exception
     */
    protected byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * windows路径不能包含：/\:*?"<>| 这些字符，需要去除
     * @param pathName
     * @return
     */
    public static String replacePathChar(String pathName){
        return pathName.replace("/" , "")
                .replace("\\" , "")
                .replace(":" , "")
                .replace("*" , "")
                .replace("?" , "")
                .replace("\"" , "")
                .replace("<" , "")
                .replace(">" , "")
                .replace("|" , "");
    }
}
