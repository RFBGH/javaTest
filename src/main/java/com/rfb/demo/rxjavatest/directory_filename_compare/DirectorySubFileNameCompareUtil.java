package com.rfb.demo.rxjavatest.directory_filename_compare;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/5/30 0030.
 */
public class DirectorySubFileNameCompareUtil {

    public static void compare(String path1, String path2, String result){

        File file1 = new File(path1);
        File file2 = new File(path2);

        if(!file1.exists() || !file1.isDirectory()){
            System.out.println("file1 is not exit or directory");
            return;
        }

        if(!file2.exists() || !file2.isDirectory()){
            System.out.println("file2 is not exit or directory");
            return;
        }

        String[] file1SubFiles = file1.list();
        String[] file2SubFiles = file2.list();

        Set<String> son1Files = new HashSet<String>(Arrays.asList(file1SubFiles));
        Set<String> son2Files = new HashSet<String>(Arrays.asList(file2SubFiles));

        Set<String> filesIn1In2 = new HashSet<String>();
        Set<String> filesIn1NotIn2 = new HashSet<String>();
        Set<String> filesIn2NotIn1 = new HashSet<String>();

        for(String son:son1Files){

            if(son2Files.contains(son)){
                filesIn1In2.add(son);
            }else{
                filesIn1NotIn2.add(son);
            }
        }

        for(String son:son2Files){

            if(filesIn1In2.contains(son)){
                continue;
            }
            filesIn2NotIn1.add(son);
        }

        FileOutputStream out = null;
        try{

            out = new FileOutputStream(new File(result));

            out.write("commonFile:\r\n".getBytes());
            out.write(("file1:"+path1+"\r\n").getBytes());
            out.write(("file2:"+path2+"\r\n").getBytes());
            for(String string:filesIn1In2){
                out.write((string+"\r\n").getBytes());
            }
            out.write(("\r\n").getBytes());
            out.write(("\r\n").getBytes());

            out.write("filesIn1NotIn2:\r\n".getBytes());
            out.write(("file1:"+path1+"\r\n").getBytes());
            out.write(("file2:"+path2+"\r\n").getBytes());
            for(String string:filesIn1NotIn2){
                out.write((string+"\r\n").getBytes());
            }
            out.write(("\r\n").getBytes());
            out.write(("\r\n").getBytes());

            out.write("filesIn2NotIn1:\r\n".getBytes());
            out.write(("file1:"+path1+"\r\n").getBytes());
            out.write(("file2:"+path2+"\r\n").getBytes());
            for(String string:filesIn2NotIn1){
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

    public static void test(){

        String path1 = "H:\\WORK\\project\\AndroidStudio\\im-component2\\module_im\\src\\main\\res\\drawable-xhdpi";
        String path2 = "H:\\WORK\\project\\AndroidStudio\\im-component2\\module_im\\src\\main\\res\\drawable-xxhdpi";
        String result = "E://dirCmpResult.txt";

        compare(path1, path2, result);

        System.out.println("cmp over");
    }


}
