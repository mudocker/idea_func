 package com.hao.util;
 
 import com.google.common.io.Files;
 import com.hao.constant.Constant;
 import com.intellij.openapi.diagnostic.Logger;
 import com.intellij.openapi.util.text.StringUtil;
 import com.intellij.openapi.vfs.VirtualFile;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.IOException;
 import java.util.List;
 
 public class VirtualFileUtils
 {
   private static final Logger logger = Logger.getInstance(VirtualFileUtils.class);
   
   public static void appendLine(VirtualFile vfile, List<String> lines)
   {

       File file = new File(vfile.getPath());
       List<String> list = Files.readLines(file, Constant.CHARSET);
       BufferedWriter bufferedWriter = Files.newWriter(file, Constant.CHARSET);
       if (!list.isEmpty())
       {
         writerLine(list, bufferedWriter);
         bufferedWriter.newLine();
       }
       if (!lines.isEmpty()) writerLine(lines, bufferedWriter);

       bufferedWriter.close();

     refresh(vfile);
   }
   
   private static void writerLine(List<String> list, BufferedWriter bufferedWriter)
     throws IOException
   {
     for (String path : list) {
       if (StringUtil.isEmpty(path)) bufferedWriter.newLine();
       else
       {
         bufferedWriter.write(path);
         bufferedWriter.newLine();
       }
     }
   }
   
   public static void refresh(VirtualFile vfile)
   {
     vfile.refresh(true, false);
   }
   
   public static List<String> readLines(VirtualFile vfile)
   {
     if ((null == vfile) || (!vfile.exists()) || (vfile.isDirectory())) return null;
       File file = new File(vfile.getPath());
       return Files.readLines(file, Constant.CHARSET);

     return null;
   }
 }



