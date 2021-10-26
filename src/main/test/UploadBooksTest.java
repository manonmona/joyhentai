import mona.joyhentai.model.Books;
import mona.joyhentai.model.BooksPages;
import mona.joyhentai.service.BooksPagesService;
import mona.joyhentai.service.BooksService;
import mona.joyhentai.service.impl.BooksPagesServiceImpl;
import mona.joyhentai.service.impl.BooksServiceImpl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author manonmona
 * @@Date 2021/10/11 20:25
 */
public class UploadBooksTest {

    public static void main(String[] args) {

//        BooksService booksService = new BooksServiceImpl();
//        BooksPagesService booksPagesService = new BooksPagesServiceImpl();
//
//        List<Books> booksList = booksService.getNotDownSuccessBooks();
//        List<BooksPages> pagesList = booksPagesService.getNotDownSuccessBooksPages(booksList.get(0).getId());
//        if(pagesList.size()>0){
//            for (int i = 0; i <pagesList.size() ; i++) {
//                if(i>9){
//                    pagesList.get(i).setStatus(2);
//                    booksList.get(0).setStatus(2);
//                    break;
//                }
//                pagesList.get(i).setStatus(1);
//            }
//        }
//        booksService.updateBooksStatus(booksList.get(0));
//        booksPagesService.updateBooksPageStatus(pagesList);

//        upload();
        threadPoolUpload();
    }

    public static void threadPoolUpload(){
        BooksService booksService = new BooksServiceImpl();
        BooksPagesService booksPagesService = new BooksPagesServiceImpl();

//        List<Books> booksList = booksService.getNotDownSuccessBooks(1,15);
        List<Books> booksList = new ArrayList<>();
        List<BooksPages> booksPagesList = null;

        //获取CPU数量
        int processors = Runtime.getRuntime().availableProcessors();

        // 创建cpu个数+1个线程
        ExecutorService pool = Executors.newFixedThreadPool(processors+2);

        String rootPath = "D:\\tmp";
        for (int i = 0; i < booksList.size(); i++) {
            Books books = booksList.get(i);
            booksPagesList = booksPagesService.getNotDownSuccessBooksPages(books.getId());
            // 如果当前本子张数为0，立刻跳转到下一次循环
            if(booksPagesList.size()==0) {
                continue ;
            }
            // * 创建文件夹，文件夹格式，id.[page].name
            String idStr = books.getId().toString();
            int idStrLen = idStr.length();
            idStr = idStrLen==1?"000"+idStr:idStrLen==2?"00"+idStr:idStrLen==3?"0"+idStr:idStr;
            // 去除特殊字符得到的名称
            String dirName = replacePathChar(books.getName());

            String pathName = rootPath+"\\["+idStr+"]["+books.getPages()+"P]"+dirName;
            File imagePathFile = new File(pathName);

            if (!imagePathFile.exists()) {
                imagePathFile.mkdir();
            }

            final List<BooksPages> fn_pagesList = booksPagesList;
            final int index = i;

            pool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < fn_pagesList.size(); j++) {
                        try {
                            // 下载图片
                            boolean result = download(fn_pagesList, j, pathName);
                            if(!result){
                                String suffix = fn_pagesList.get(j).getSuffix();
                                String url = fn_pagesList.get(j).getUrl();
                                if(suffix.equals(".png")){
                                    fn_pagesList.get(j).setSuffix(".jpg");
                                    fn_pagesList.get(j).setUrl(url.replace(".png",".jpg"));
                                }else if(suffix.equals(".jpg")){
                                    fn_pagesList.get(j).setSuffix(".png");
                                    fn_pagesList.get(j).setUrl(url.replace(".jpg",".png"));
                                }
                                download(fn_pagesList, j, pathName);
                            }
                        } catch (Exception e) {
//                            e.printStackTrace();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String dateTime = dateFormat.format(new Date());
                            System.out.println(e.getMessage()+"：["+books.getId()+"]"+dirName+"\t"+fn_pagesList.get(j).getName()+"页下载失败   ----->"+dateTime);
//                            booksList.get(index).setStatus(2);
//                            booksService.updateBooksStatus(booksList.get(index));

                            fn_pagesList.get(j).setStatus(2);
//                            booksPagesService.updateBooksPageStatus(fn_pagesList);

//                            return;
                        }

                        if(j==fn_pagesList.size()-1){
                            booksPagesService.updateBooksPageStatus(fn_pagesList);
                            int notDownSuccessCount = booksPagesService.getNotDownSuccessBooksPages(books.getId()).size();
                            if(notDownSuccessCount==0){
                                booksList.get(index).setStatus(1);
                            }else {
                                booksList.get(index).setStatus(2);
                            }
                            booksService.updateBooksStatus(booksList.get(index));
                        }
                    }
                }
            });

        }

        pool.shutdown();
        boolean suo = true;
        while (suo){
            if(pool.isTerminated()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = dateFormat.format(new Date());
                System.out.println("dateTime--->所有线程执行完毕，main退出操作");
                suo =false;
            }
        }
//        threadPoolUpload();
    }

    /**
     * 下载
     */
    public static void upload() {
        BooksService booksService = new BooksServiceImpl();
        BooksPagesService booksPagesService = new BooksPagesServiceImpl();

//        List<Books> booksList = booksService.getNotDownSuccessBooks(1,15);
        List<Books> booksList = new ArrayList<>();
        List<BooksPages> booksPagesList = null;

        String rootPath = "D:\\tmp";
        for (int i = 0; i < booksList.size(); i++) {
            Books books = booksList.get(i);
            booksPagesList = booksPagesService.getNotDownSuccessBooksPages(books.getId());
            // 如果当前本子张数为0，立刻跳转到下一次循环
            if(booksPagesList.size()==0) {
                continue ;
            }
            // * 创建文件夹，文件夹格式，id.[page].name
            String idStr = books.getId().toString();
            int idStrLen = idStr.length();
            idStr = idStrLen==1?"000"+idStr:idStrLen==2?"00"+idStr:idStrLen==3?"0"+idStr:idStr;
            // 去除特殊字符得到的名称
            String dirName = replacePathChar(books.getName());

            String pathName = rootPath+"\\["+idStr+"]["+books.getPages()+"P]"+dirName;
            File imagePathFile = new File(pathName);

            if (!imagePathFile.exists()) {
                imagePathFile.mkdir();
            }

            for (int j = 0; j < booksPagesList.size(); j++) {
                try {
                    // 下载图片
                    download(booksPagesList,j,pathName);
                } catch (Exception e) {
                    e.printStackTrace();

//                    if(j==0){
//
//                        booksList.get(i).setStatus(0);
//                    }else{
//
//                        booksList.get(i).setStatus(2);
//                    }
//                    booksService.updateBooksStatus(booksList.get(i));
                    booksPagesList.get(j).setStatus(2);
//                    booksPagesService.updateBooksPageStatus(booksPagesList);
//
//                    return;
                }

                if(j==booksPagesList.size()-1){
                    booksPagesService.updateBooksPageStatus(booksPagesList);
                    booksPagesList = booksPagesService.getNotDownSuccessBooksPages(books.getId());
                    if(booksPagesList.size()==0){
                        booksList.get(i).setStatus(1);
                    }else {
                        booksList.get(i).setStatus(2);
                    }
                    booksService.updateBooksStatus(booksList.get(i));
                }
            }


        }
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

    /**
     * 下载图片
     * @param booksPagesList
     * @param index
     * @param imagePathFile
     * @throws Exception
     */
    public static boolean download(List<BooksPages> booksPagesList,Integer index , String imagePathFile)throws Exception {
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
        byte[] data = UploadBooksTest.readInputStream(inStream);
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
    public static byte[] readInputStream(InputStream inStream) throws Exception{
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
