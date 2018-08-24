 package com.hao.tools;
 
 import com.hao.util.VirtualFileUtils;
 import com.intellij.openapi.diagnostic.Logger;
 import com.intellij.openapi.fileEditor.FileEditorManager;
 import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
 import com.intellij.openapi.fileEditor.FileEditorManagerListener;
 import com.intellij.openapi.project.Project;
 import com.intellij.openapi.util.text.StringUtil;
 import com.intellij.openapi.vfs.VirtualFile;
 import com.intellij.openapi.wm.ToolWindow;
 import com.intellij.openapi.wm.ToolWindowFactory;
 import com.intellij.ui.JBColor;
 import com.intellij.ui.content.Content;
 import com.intellij.ui.content.ContentFactory;
 import com.intellij.ui.content.ContentFactory.SERVICE;
 import com.intellij.ui.content.ContentManager;
 import com.intellij.util.messages.MessageBus;
 import com.intellij.util.messages.MessageBusConnection;
 import java.util.List;
 import javax.swing.BorderFactory;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.JTree;
 import javax.swing.JViewport;
 import javax.swing.tree.DefaultMutableTreeNode;
 import javax.swing.tree.DefaultTreeModel;
 import javax.swing.tree.MutableTreeNode;
 import org.jetbrains.annotations.NotNull;
 
 public class ToolFactoryCompute
   implements ToolWindowFactory
 {
   private static final Logger logger;
   public static final String TOOL_WINDOW_ID = "Jstructure";
   private JPanel myPanel;
   private JScrollPane myScrollPane;
   private JTree javaTree;
   
   public ToolFactoryCompute()
   {
     $$$setupUI$$$();
   }
   
   static
   {
     ToolFactoryCompute.logger = Logger.getInstance(ToolFactoryCompute.class);
   }
   
   public void createToolWindowContent(@NotNull Project project, @NotNull final ToolWindow toolWindow)
   {
     if (project == null) $$$reportNull$$$0(0);

     if (toolWindow == null) $$$reportNull$$$0(1);

     Content content = ContentFactory.SERVICE.getInstance().createContent(this.myPanel, "", false);
     content.setCloseable(false);
     toolWindow.getContentManager().addContent(content);
     
 
     MessageBus messageBus = project.getMessageBus();
     messageBus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener()
     {
       public void selectionChanged(@NotNull FileEditorManagerEvent event)
       {
         if (event == null) $$$reportNull$$$0(0);

         if ((null == event.getNewFile()) || (!toolWindow.isVisible())) return;

         ToolFactoryCompute.this.javaView(event.getManager(), event.getNewFile());
       }
     });
   }
   
   public void init(ToolWindow toolWindow)
   {
     this.javaTree.setOpaque(false);
     this.myPanel.setOpaque(false);
     this.myScrollPane.setOpaque(false);
     this.myScrollPane.getViewport().setOpaque(false);
     
 
     this.javaTree.setBorder(BorderFactory.createLineBorder(JBColor.BLACK, 0));
     this.myScrollPane.setBorder(BorderFactory.createLineBorder(JBColor.BLACK, 0));
     this.myPanel.setBorder(BorderFactory.createLineBorder(JBColor.BLACK, 0));
   }
   
   private void javaView(@NotNull FileEditorManager manager, @NotNull VirtualFile file)
   {
     if (manager == null) $$$reportNull$$$0(2);

     if (file == null) $$$reportNull$$$0(3);

     if (!StringUtil.endsWith(file.getName(), ".java")) return;

     String fileName = file.getName();
     fileName = StringUtil.substringBefore(fileName, ".");
     
     DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileName);
     List<String> lines = VirtualFileUtils.readLines(file);
     for (String line : lines) {
       if ((line.contains("(")) && (line.contains(")"))) {
         MutableTreeNode lv1 = new DefaultMutableTreeNode(line);
         root.add(lv1);
      }
    }
    DefaultTreeModel tree = new DefaultTreeModel(root);
    this.javaTree.setModel(tree);
    this.myPanel.setVisible(true);
  }
 }



