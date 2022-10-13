package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import org.xmind.core.Core;
import org.xmind.core.CoreException;
import org.xmind.core.INotes;
import org.xmind.core.IPlainNotesContent;
import org.xmind.core.ISheet;
import org.xmind.core.ITopic;
import org.xmind.core.IWorkbook;
import org.xmind.core.IWorkbookBuilder;
import org.xmind.core.internal.dom.TopicImpl;

import com.sun.tools.hat.internal.model.Root;

/**
 * @author liuhongli <liuhongli@kuaishou.com>
 * Created on 2022-10-12
 */
public class Xmind {
    public static void main(String[] args) throws Exception {

        String root="FM:";
        String xmindEmptyPath="/Users/hongliliu/Documents/测试用例xmind/auto/test.xmind";
        int n=4;
        createEmptyTestCases(root,xmindEmptyPath,n);

        root="FM:new";
        String inputPath="/Users/hongliliu/testH/xmind/input.txt";
        String xmindPath="/Users/hongliliu/Documents/测试用例xmind/auto/demo.xmind";
        createXmind(root,inputPath,xmindPath);
    }
    public static void createEmptyTestCases(String root,String path,int n) throws Exception{

        Queue<Node> q=new ArrayDeque<>();
        IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
        IWorkbook workbook = workbookBuilder.createWorkbook();
        // 获得默认sheet
        ISheet primarySheet = workbook.getPrimarySheet();
        // 获得根主题
        ITopic rootTopic = primarySheet.getRootTopic();
        rootTopic.setTitleText(root);
        //右逻辑图 org.xmind.ui.logic.right
        rootTopic.setStructureClass("org.xmind.ui.logic.right");
        Node rootNode=new Node();
        rootNode.topic=rootTopic;
        q.add(rootNode);
        Node parent;
        Node child;
        int currentDepth=0;
        rootNode.depth=0;
        while(currentDepth<n){
            parent=q.poll();
                for(int i=0;i<n;i++){
                    ITopic t=workbook.createTopic();
                    child=new Node();
                    child.topic=t;
                    child.depth=parent.depth+1;
                    currentDepth=child.depth;
                    if(currentDepth<n-1){
                        t.setTitleText("FM:");
                    }
                    else{
                        if(parent.topic.getTitleText().equals("FM:"))
                        t.setTitleText("TC:");
                    }
                    parent.topic.add(child.topic, ITopic.ATTACHED);
                    q.add(child);
                }
        }
        workbook.save(path);
    }

    public static void createXmind(String root,String inputPath,String path) throws Exception{
        // 创建思维导图的工作空间
        IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
        IWorkbook workbook = workbookBuilder.createWorkbook();
        // 获得默认sheet
        ISheet primarySheet = workbook.getPrimarySheet();
        // 获得根主题
        ITopic rootTopic = primarySheet.getRootTopic();
        // 设置根主题的标题
        rootTopic.setTitleText(root);
        //正确的逻辑图 org.xmind.ui.logic.right
        rootTopic.setStructureClass("org.xmind.ui.logic.right");
        FileReader f=new FileReader(inputPath);
        BufferedReader b=new BufferedReader(f);
        String s;
        while((s=b.readLine())!=null){
            ITopic topic = workbook.createTopic();
            topic.setTitleText("TC:"+s.trim());
            rootTopic.add(topic, ITopic.ATTACHED);
        }
        workbook.save(path);
    }
}
class Node{
    ITopic topic;
    int depth;
}
