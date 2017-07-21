package com.aisino.tools;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 文件下载
 * Created by gangchaopan on 2017/7/19.
 */
public class FileDownLoad {



    /**
     * 消耗时间366ms
     * 最简单的方式的文件下载，按照字节数组读取
     * @param loadUrl   网路文件下载地址
     * @param destfilepath  目标文件存储路径
     * @throws IOException
     */
    private static void BufferoutputStreambytesDownload(String loadUrl,String destfilepath) throws IOException{

        URL url = new URL(loadUrl);
        URLConnection connection = url.openConnection();

        //服务器设置了禁止抓取,通过设置User-Agent来欺骗服务器
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream =  connection.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);


        FileOutputStream fos = new FileOutputStream(destfilepath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);



        byte[] bytes = new byte[1024];
        int  len =0;

        while( (len = bis.read(bytes)) != -1 ){
            bos.write(bytes,0 ,len);
        }

        bis.close();
        bos.close();

    }


    /**
     *
     * 消耗时间508ms
     * 最简单的方式的文件下载，在缓存区中读取
     * @param loadUrl   下载地址URLL
     * @param destfilepath  目标文件存储路径
     * @throws IOException
     */
    public  static void  BufferoutputStreamDownload(String loadUrl,String destfilepath) throws IOException{

        URL url = new URL(loadUrl);
        URLConnection connection = url.openConnection();

        //服务器设置了禁止抓取,通过设置User-Agent来欺骗服务器
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");


        InputStream inputStream = connection.getInputStream();

        BufferedInputStream bis = new BufferedInputStream(inputStream);

        FileOutputStream fos = new FileOutputStream(destfilepath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int  content =0;

        while( (content = bis.read()) != -1 ){
            bos.write(content);
        }
        System.out.println("下载成功");

        bis.close();
        bos.close();

    }




    public static void main(String[] args) {
        long current = System.currentTimeMillis();

        try {
            //消耗时间508ms
            BufferoutputStreambytesDownload("http://pic1.win4000.com/wallpaper/1/59327f1ae0e5f.jpg",
                    "/Users/gangchaopan/Downloads/test/59327f1ae0e5f2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("消耗时间"+(end -current)+"ms");
    }



}
