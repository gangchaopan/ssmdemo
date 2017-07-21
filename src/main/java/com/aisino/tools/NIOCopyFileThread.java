package com.aisino.tools;


import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * NIO方式操作文件
 * Created by gangchaopan on 2017/7/19.
 */
public class NIOCopyFileThread extends  Thread {

    private String srcPath ;

    private String destPath;

    private long start,end;

    public NIOCopyFileThread(String srcPath,String destPath, long start, long end){
        this.srcPath = srcPath;

        this.destPath = destPath;

        this.start = start;

        this.end = end;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #
     */
    @Override
    public void run() {
       try{

           RandomAccessFile in = new RandomAccessFile(srcPath,"r");
           RandomAccessFile out = new RandomAccessFile(destPath,"rw");

           in.seek(start);

           out.seek(start);

           FileChannel fisChannel=  in.getChannel();
           FileChannel  fosChannel = out.getChannel();

           FileLock lock = fosChannel.lock(start,(end-start),false);

           fisChannel.transferTo(start,(end-start),fosChannel);

           lock.release();

           out.close();
           in.close();
           System.out.println(Thread.currentThread().getId()+"完成");



       }catch (Exception e){

       }
    }
}
