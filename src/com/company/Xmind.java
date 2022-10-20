package com.company;

import static com.company.IndustryFour.readAll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import org.xmind.core.Core;
import org.xmind.core.ISheet;
import org.xmind.core.ITopic;
import org.xmind.core.IWorkbook;
import org.xmind.core.IWorkbookBuilder;


/**
 * @author liuhongli <liuhongli@kuaishou.com>
 * Created on 2022-10-12
 */
public class Xmind {
    public static void main(String[] args) throws Exception {
        String root="FM:";
        String xmindEmptyPath="/Users/hongliliu/Documents/测试用例xmind/auto/test.xmind";
        int n=4;
       // createEmptyTestCases(root,xmindEmptyPath,n);
        String title="功能cases";
        root="FM:"+title;
        String inputPath="/Users/hongliliu/testH/xmind/input.txt";
        String xmindPath="/Users/hongliliu/Documents/测试用例xmind/auto/demo.xmind";
        //createOneLayerXmind(root,inputPath,xmindPath);
        String inputCsvPath="/Users/hongliliu/Documents/测试用例xmind/testcases.csv";
        String xmindPath2="/Users/hongliliu/Documents/测试用例xmind/auto/demoT.xmind";
        String xmindPathS="/Users/hongliliu/Documents/测试用例xmind/auto/demoS.xmind";
       // createTwoLayersXmind(root,inputCsvPath,xmindPath2);
        createMultipleLayersXmind(root,inputCsvPath,xmindPathS);
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

    public static void createOneLayerXmind(String root,String inputPath,String path) throws Exception{
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

    public static void createTwoLayersXmind(String root,String inputPath,String path) throws Exception{
        List<String[]> list=readAll(inputPath);
        LinkedHashMap<String,ITopic> map=new LinkedHashMap<>();
        String currentLayer="";
        // 创建思维导图的工作空间
        IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
        IWorkbook workbook = workbookBuilder.createWorkbook();
        // 获得默认sheet
        ISheet primarySheet = workbook.getPrimarySheet();
        // 获得根主题
        ITopic rootTopic = primarySheet.getRootTopic();
        // 设置根主题的标题
        rootTopic.setTitleText(root);
        //右逻辑图 org.xmind.ui.logic.right
        rootTopic.setStructureClass("org.xmind.ui.logic.right");
        for(int i=0;i<list.size();i++){
           String[] array=list.get(i);
           if(!array[0].equals("")){
               currentLayer=array[0];
               if(!map.containsKey(currentLayer)){
                   ITopic topicParent = workbook.createTopic();
                   topicParent.setTitleText("FM:"+currentLayer);
                   map.put(currentLayer,topicParent);

                   ITopic topicSon = workbook.createTopic();
                   topicSon.setTitleText("TC:"+array[1]);
                   topicParent.add(topicSon, ITopic.ATTACHED);
               }
               else{
                   if(!array[1].equals("")){
                       ITopic topicParent=map.get(currentLayer);
                       ITopic topicSon = workbook.createTopic();
                       topicSon.setTitleText("TC:"+array[1]);
                       topicParent.add(topicSon, ITopic.ATTACHED);
                   }
               }
           }
           else{
               ITopic topicParent=map.get(currentLayer);
               ITopic topicSon = workbook.createTopic();
               topicSon.setTitleText("TC:"+array[1]);
               topicParent.add(topicSon, ITopic.ATTACHED);
           }
        }
        for(Map.Entry<String,ITopic> entry:map.entrySet()){
            ITopic topic=entry.getValue();
            rootTopic.add(topic, ITopic.ATTACHED);
        }
        workbook.save(path);
    }

    public static void createMultipleLayersXmind(String root,String inputPath,String path) throws Exception{
        List<String[]> listInput=readAll(inputPath);
        List<LinkedHashMap<String,ITopic>> listTopics =new ArrayList<>();
        // 创建思维导图的工作空间
        IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
        IWorkbook workbook = workbookBuilder.createWorkbook();
        // 获得默认sheet
        ISheet primarySheet = workbook.getPrimarySheet();
        // 获得根主题
        ITopic rootTopic = primarySheet.getRootTopic();
        // 设置根主题的标题
        rootTopic.setTitleText(root);
        //右逻辑图 org.xmind.ui.logic.right
        rootTopic.setStructureClass("org.xmind.ui.logic.right");
        LinkedHashMap<String,ITopic> map0=new LinkedHashMap<>();
        for(int i=0;i<listInput.size();i++){
            String[] array0=listInput.get(i);
            String[] array=removeLastBlanks(array0);
            for(int j=0;j<array.length;j++){
                String s=array[j];
                s=s.replaceAll("\\s+","");
                if(s.equals("")){
                    continue;
                }
                if(j==0){
                    ITopic topicParent = workbook.createTopic();
                    topicParent.setTitleText("FM:"+array[j]);
                    map0.put(s,topicParent);
                    listTopics.add(map0);
                }
                else{
                    ITopic topicSon = workbook.createTopic();
                    if(j==array.length-1){
                        topicSon.setTitleText("TC:"+s);
                    }
                    else{
                        topicSon.setTitleText("FM:"+s);
                    }
                    String parentS=array[j-1];
                    parentS=parentS.replaceAll("\\s+","");
                    if(!parentS.equals("")){
                        if(listTopics.get(j-1).containsKey(parentS)){
                            ITopic topicParent=listTopics.get(j-1).get(parentS);
                            topicParent.add(topicSon, ITopic.ATTACHED);
                        }
                    }
                    else{
                        LinkedHashMap<String,ITopic> map=listTopics.get(j-1);
                        //通过反射获取LinkedHashMap的最后一个元素
                        //Get last element through reflection
                        Field tail = map.getClass().getDeclaredField("tail");
                        tail.setAccessible(true);
                        Map.Entry<String,ITopic> entry= (Entry<String, ITopic>)tail.get(map);
                        ITopic topicParent=entry.getValue();
                        topicParent.add(topicSon, ITopic.ATTACHED);
                    }
                    int n=listTopics.size();
                    if(j==n){
                        LinkedHashMap<String,ITopic> map1=new LinkedHashMap<>();
                        map1.put(s,topicSon);
                        listTopics.add(map1);
                    }
                    else{
                        LinkedHashMap<String,ITopic> map1=listTopics.get(j);
                        map1.put(s,topicSon);
                        listTopics.add(map1);
                    }
                }
            }
        }
        for(Map.Entry<String,ITopic> entry:map0.entrySet()){
            ITopic topic=entry.getValue();
            rootTopic.add(topic, ITopic.ATTACHED);
        }
        workbook.save(path);

   }
   public static String[] removeLastBlanks(String[] array){
        int maxIndex=0;
        for(int i=0;i<array.length;i++){
            String s=array[i];
            s=s.replaceAll("\\s+","");
            if(!s.equals("")){
                maxIndex=i;
            }
        }
        String[] result=Arrays.copyOfRange(array,0,maxIndex+1);
        return result;
   }
}
class Node{
    ITopic topic;
    int depth;
}
