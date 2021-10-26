package mona.joyhentai.service.impl;

import mona.joyhentai.model.BooksPages;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/26 13:15
 */
public class AbstractJoyhentaiDownloadService {


    /**
     * 下载图片
     * @param booksPagesList
     * @param index
     * @param imagePathFile
     * @throws Exception
     */
    protected boolean download(List<BooksPages> booksPagesList, Integer index , String imagePathFile)throws Exception {
        BooksPages booksPages = booksPagesList.get(index);
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
            booksPagesList.get(index).setStatus(2);
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
        //设置下载状态为完成
        booksPagesList.get(index).setStatus(1);
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
}
