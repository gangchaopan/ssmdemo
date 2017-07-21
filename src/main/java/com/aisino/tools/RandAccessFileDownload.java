package com.aisino.tools;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gangchaopan on 2017/7/20.
 */
public class RandAccessFileDownload  implements  Runnable{

    private CountDownLatch latch;

    private File destFile;

    private long start;

    private long end ;

    private  String  loadurl;

    private int threadId;

    private int blockNum;




    public RandAccessFileDownload(int blockNum ,int threadId,String  loadurl,File destFile, long start, long end,CountDownLatch latch) throws IOException{
        this.threadId = threadId;
        this.destFile = destFile;
        this.start = start;
        this.end = end;

        this.latch = latch;
        this.loadurl = loadurl;

        this.blockNum = blockNum;

    }



    public void run(){
        RandomAccessFile out= null;
        BufferedInputStream bis =null;
        InputStream inputStream = null;
        HttpURLConnection connection =null;

        System.out.println(Thread.currentThread().getName()+"线程开始下载文件"+"start:"+start+"end:"+end);
        long startTime = System.currentTimeMillis();

        try{
            URL url = new URL(loadurl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);

            long seekStartTime  = System.currentTimeMillis();
            File savefile = new File("/Users/gangchaopan/Downloads/test/"+threadId+".txt");
            if(savefile.exists()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(savefile)));
                String currentPos = reader.readLine();
                start = Long.valueOf(currentPos);
                System.out.println(Thread.currentThread().getName()+"从上次文件继续读取"+start+"-"+end);

                connection.setRequestProperty("Range","bytes=" + start + "-" + end );
                reader.close();
            }else{
                connection.setRequestProperty("Range","bytes=" + start+ "-" + end );
            }

            //171881+379900

            out  = new RandomAccessFile(destFile,"rw");
            out.seek(start);

            //206 表示区域部分下载请求成功的状态，200表示全部下载成功
            if(connection.getResponseCode() ==206){
                inputStream = connection.getInputStream();

                int  len = 0;

                long total = 0;//记录当前线程下载文件的位置

                byte[] bytes = new byte[8192];
                while ( (len = inputStream.read( bytes)) !=-1 ){
                    out.write(bytes,0,len);
                    total += len;
                    long  currentLocation = start+total;
                    RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/gangchaopan/Downloads/test/"+threadId+".txt","rw");
                    randomAccessFile.write(String.valueOf(currentLocation).getBytes());
                    randomAccessFile.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {

                inputStream.close();
                out.close();
                connection.disconnect();
                System.out.println(Thread.currentThread().getName()+"进程任务下载结束");

                latch.countDown();
                synchronized (RandAccessFileDownload.class){
                    if(latch.getCount()==0){
                        //所有线程全部运行完毕
                        for (int i=0;i<blockNum;i++){
                            File file3 = new File("/Users/gangchaopan/Downloads/test/"+i+".txt") ;
                            System.out.println("删除文件记录"+file3.getAbsolutePath());
                            file3.delete();
                        }
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+"：消耗时间:"+(endTime-startTime)+":处理长度"+connection.getContentLength());

    }


    public   static void startDownload(int threadNum,String loadurl,String destfilePath) throws  Exception{

        CountDownLatch latch = new CountDownLatch(threadNum);

        int blockCount=threadNum;//线程数
        File destFile = new File(destfilePath);

        URL url = new URL(loadurl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //服务器设置了禁止抓取,通过设置User-Agent来欺骗服务器
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        if(connection.getResponseCode()==200) {
            int contentLength = connection.getContentLength();
            connection.disconnect();
            long oneNum = contentLength / blockCount;


            RandomAccessFile out = new RandomAccessFile(destFile, "rw");
            out.setLength(contentLength);
            out.close();

            //创建线程池
            ExecutorService executorService =  Executors.newCachedThreadPool();

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < blockCount; i++) {

                long startPos = oneNum * i;
                long endPos = oneNum*(i+1)-1;
                if(i==contentLength){
                    endPos=contentLength;
                }
                executorService.execute(new RandAccessFileDownload(blockCount,i,loadurl, destFile, startPos,endPos, latch));
            }

            latch.await();
            long endTime = System.currentTimeMillis();
            System.out.println("文件下载成功，耗时时间："+(endTime-startTime)+"ms");

        }else {
            System.out.println("下载失败");
        }



    }

    public static void main(String[] args) throws  Exception{


        //消耗时间508ms
//        startDownload(latch,4,"http://124.202.164.8/files/306700000A1E2FBE/download.oracle.com/otn/java/jdk/7u80-b15/jdk-7u80-linux-x64.rpm",
//                "/Users/gangchaopan/Downloads/test/jdk-7u80-linux-x64.rpm");
        startDownload(4,"http://sw.bos.baidu.com/sw-search-sp/software/9a2808964b476/QQ_8.9.3.21169_setup.exe",
                "/Users/gangchaopan/Downloads/test/qq.exe");

    }

}
