 package com.hao.util;
 
 import com.intellij.openapi.ui.Messages;
 
 public class MessageUtils
 {
   private static final String DEFAULT_TITLE = "Do Patch";
   
   public static void alert(String title, String message)
   {
     Messages.showDialog(message, title, new String[] { "OK" }, -1, null);
   }
   
   public static void alert(String message)
   {
     alert("Do Patch", message);
   }
 }


