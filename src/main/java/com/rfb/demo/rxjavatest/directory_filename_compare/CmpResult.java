package com.rfb.demo.rxjavatest.directory_filename_compare;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Set;

/**
 * Created by Administrator on 2018/5/30 0030.
 */
public class CmpResult {

    private String mPath1;
    private String mPaht2;

    private Set<String> filesIn1In2;
    private Set<String> filesIn1NotIn2;
    private Set<String> filesIn2NotIn1;

    public CmpResult(String path1, String path2, Set<String> filesIn1In2, Set<String> filesIn1NotIn2, Set<String> filesIn2NotIn1) {
        this.filesIn1In2 = filesIn1In2;
        this.filesIn1NotIn2 = filesIn1NotIn2;
        this.filesIn2NotIn1 = filesIn2NotIn1;
        mPaht2 = path2;
        mPath1 = path1;
    }

    public Set<String> getFilesIn1In2() {
        return filesIn1In2;
    }

    public Set<String> getFilesIn1NotIn2() {
        return filesIn1NotIn2;
    }

    public Set<String> getFilesIn2NotIn1() {
        return filesIn2NotIn1;
    }

    public String getPath1() {
        return mPath1;
    }

    public String getPath2() {
        return mPaht2;
    }

    public static void print2File(String filename, CmpResult cmpResult){

        FileOutputStream out = null;
        try{

            out = new FileOutputStream(new File(filename));

            out.write("commonFile:\r\n".getBytes());
            out.write(("file1:"+cmpResult.getPath1()+"\r\n").getBytes());
            out.write(("file2:"+cmpResult.getPath2()+"\r\n").getBytes());
            for(String string:cmpResult.getFilesIn1In2()){
                out.write((string+"\r\n").getBytes());
            }
            out.write(("\r\n").getBytes());
            out.write(("\r\n").getBytes());

            out.write("filesIn1NotIn2:\r\n".getBytes());
            out.write(("file1:"+cmpResult.getPath1()+"\r\n").getBytes());
            out.write(("file2:"+cmpResult.getPath2()+"\r\n").getBytes());
            for(String string:cmpResult.getFilesIn1NotIn2()){
                out.write((string+"\r\n").getBytes());
            }
            out.write(("\r\n").getBytes());
            out.write(("\r\n").getBytes());

            out.write("filesIn2NotIn1:\r\n".getBytes());
            out.write(("file1:"+cmpResult.getPath1()+"\r\n").getBytes());
            out.write(("file2:"+cmpResult.getPath2()+"\r\n").getBytes());
            for(String string:cmpResult.getFilesIn2NotIn1()){
                out.write((string+"\r\n").getBytes());
            }
            out.write(("\r\n").getBytes());
            out.write(("\r\n").getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out != null){
                try{
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
