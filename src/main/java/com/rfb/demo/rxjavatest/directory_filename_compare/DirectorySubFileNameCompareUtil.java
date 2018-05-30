package com.rfb.demo.rxjavatest.directory_filename_compare;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/5/30 0030.
 */
public class DirectorySubFileNameCompareUtil {

    public static CmpResult compare(String path1, String path2){

        File file1 = new File(path1);
        File file2 = new File(path2);

        if(!file1.exists() || !file1.isDirectory()){
            System.out.println("file1 is not exit or directory");
            return null;
        }

        if(!file2.exists() || !file2.isDirectory()){
            System.out.println("file2 is not exit or directory");
            return null;
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

        return new CmpResult(path1, path2, filesIn1In2, filesIn1NotIn2, filesIn2NotIn1);

    }

    public static void test(){

        String path1 = "H:\\WORK\\project\\AndroidStudio\\im-component2\\module_im\\src\\main\\res\\drawable-xhdpi";
        String path2 = "H:\\WORK\\project\\AndroidStudio\\im-component2\\module_im\\src\\main\\res\\drawable-xxhdpi";
        String result = "E://dirCmpResult2.txt";

        CmpResult cmpResult = compare(path1, path2);
        if(cmpResult == null){
            System.out.println("result is null");
            return;
        }

        CmpResult.print2File(result, cmpResult);
        System.out.println("cmp over");

        for(String name:cmpResult.getFilesIn1In2()){

            File file = new File(cmpResult.getPath1()+"\\"+name);
            file.deleteOnExit();
        }

    }


}
