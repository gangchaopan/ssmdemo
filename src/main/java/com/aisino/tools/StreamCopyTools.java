package com.aisino.tools;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 字节流工具类
 * Created by gangchaopan on 2017/7/19.
 */
public class StreamCopyTools {


    /**
     * 加入内存缓冲区，一次读写字节数组
     * @param filename 传入的文件名称
     * @param destFilename 需要生成的文件名称
     * @throws IOException 异常信息
     */
    public  static void BufferStreamBytesOperation(String filename,String destFilename) throws  IOException{

            FileInputStream fis = new FileInputStream( filename);
            BufferedInputStream bis = new BufferedInputStream( fis );

            FileOutputStream fos = new FileOutputStream(destFilename);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[]  bytes = new byte[1024];
            int len = 0;

            while((len = bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }

            //关闭字节流，被JVM垃圾回收机制清理，并通知系统 关闭相应的资源
            bos.close();
            bis.close();
    }


    /**
     * 加入缓存区，一次读写一个字节
     * @param filename 传入的文件名称
     * @param destFilename 需要生成的文件名称
     * @throws IOException 异常信息
     */
    public static void BufferStreamByteOperation(String filename,String destFilename) throws IOException{
        FileInputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);

        FileOutputStream fos = new FileOutputStream(destFilename);
        BufferedOutputStream bos  = new BufferedOutputStream(fos);

        int content = 0 ;
        while( ( content = bis.read()) != -1 ){
            bos.write(content);
        }

        bos.close();
        bis.close();

    }


    /**
     * 普通文件 一次读写一个字节数组
     * @param filename 传入的文件名称
     * @param destFilename 生成文件名称
     * @throws IOException 异常信息
     */
    public static void StreamBytesOperation(String filename,String destFilename) throws IOException{

        FileInputStream fis = new FileInputStream(filename);

        FileOutputStream fos  = new FileOutputStream(destFilename);

        int len = 0 ;
        byte[] bytes = new byte[1024];
        while ( (len = fis.read( bytes)) !=-1 ){
            fos.write(bytes,0,len);
        }

        fis.close();
        fos.close();
    }


    /**
     * 普通文件流读写  一次读写一个字节
     * @param filename 传入的文件文件名称
     * @param destFilename 生成的文件名称
     * @throws IOException 异常信息
     */
    public static void StreamByteOperation(String  filename, String destFilename) throws IOException{

        FileInputStream fis = new FileInputStream(filename);

        FileOutputStream fos = new FileOutputStream(destFilename);

        int content = 0 ;

        while( (content = fis.read()) != -1 ){
            fos.write(content);
        }

        fis.close();
        fos.close();

    }

    /**
     * NIO复制文件
     * @param filename
     * @param destFilename
     * @throws IOException
     */
    public static void NIOStream(String filename,String destFilename) throws  IOException{
        FileInputStream fis = new FileInputStream(filename);
        FileOutputStream fos = new FileOutputStream(destFilename);

        FileChannel fisChannel=  fis.getChannel();
        FileChannel  fosChannel = fos.getChannel();

        fisChannel.transferTo(0,fisChannel.size(),fosChannel);
        System.out.println("复制成功");

    }


    public static void largeMappedFileOperation(String srcfilename,String destfilename) throws  IOException{

        RandomAccessFile rafi = new RandomAccessFile(srcfilename, "r");
        RandomAccessFile rafo = new RandomAccessFile(destfilename, "rw");
        FileChannel fci = rafi.getChannel();
        FileChannel fco = rafo.getChannel();
        long size = fci.size();
        MappedByteBuffer mbbi = fci.map(FileChannel.MapMode.READ_ONLY, 0, size);
        MappedByteBuffer mbbo = fco.map(FileChannel.MapMode.READ_WRITE, 0, size);
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            byte b = mbbi.get(i);
            mbbo.put(i, b);
        }
        fci.close();
        fco.close();
        rafi.close();
        rafo.close();
    }






    /**
     * 对13M的文件进行测试
     * @param args 参数
     */
    public static void main(String[] args) {
        String filename = "/Users/gangchaopan/Downloads/test/2222.pdf";
        String destFilename = "/Users/gangchaopan/Downloads/test/9999.pdf";
        long current = System.currentTimeMillis();

//        try {
//            //消耗时间45377ms
//            StreamByteOperation(filename,destFilename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            //消耗时间254ms
//            StreamBytesOperation(filename,destFilename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            //消耗时间866ms
//            BufferStreamByteOperation(filename,destFilename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            //消耗时间95ms
//            BufferStreamBytesOperation(filename,destFilename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            //消耗时间294ms
//            NIOStream(filename,destFilename);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //5ms
//        int blockCount=4;//线程数
//        File source = new File(filename);
//        long len = source.length();
//        long oneNum = len/blockCount;
//        for (int i=0 ; i< blockCount ; i++){
//            new NIOCopyFileThread(filename,destFilename,oneNum*i,oneNum*(i+1)).start();
//        }

        try {
            //消耗时间532ms
            largeMappedFileOperation(filename,destFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }



        long end = System.currentTimeMillis();

        System.out.println("消耗时间"+(end -current)+"ms");


    }




}
