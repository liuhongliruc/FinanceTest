package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opencsv.CSVReader;

/**
 * @author liuhongli <liuhongli@kuaishou.com>
 * Created on 2022-10-09
 */
public class Industry4 {
    public static String pathDic="/Users/hongliliu/testH/industry/dic";
    public static String pathOnline="/Users/hongliliu/testH/industry/online.csv";
    public static String pathSql="/Users/hongliliu/testH/industry/sql.txt";
    public static void main(String[] args) throws Exception {
        //检查
        checkIds();
    }

    public static void checkIds() throws Exception{
        //id映射，一级映射到一级，二级映射到二级
        //可能有一对多，所以用HashMap<String, HashSet>
        //先读一遍dic  /Users/hongliliu/testH/industry/dic
        //构造好字典
        FileReader f=new FileReader(pathDic);
        BufferedReader b=new BufferedReader(f);
        String s;
        HashMap<String, HashSet<String>> dic=new HashMap<>();
        HashMap<String,String>  dicName=new HashMap<>();
        while((s=b.readLine())!=null){
            // System.out.println(s);
            String[] array=s.split("\\s+");
            if(dic.containsKey(array[0])){
                HashSet<String> set1=dic.get(array[0]);
                set1.add(array[4]);
                dic.put(array[0],set1);
            }
            else{
                HashSet<String> set1=new HashSet<>();
                set1.add(array[4]);
                dic.put(array[0],set1);
            }

            if(dic.containsKey(array[2])){
                HashSet<String> set1=dic.get(array[2]);
                set1.add(array[6]);
                dic.put(array[2],set1);
            }
            else{
                HashSet<String> set1=new HashSet<>();
                set1.add(array[6]);
                dic.put(array[2],set1);
            }
            //把行业4.0的id和行业名称写入另一个map
            dicName.put(array[4],array[5]);
            dicName.put(array[6],array[7]);
        }
        b.close();
        //输出一下字典
        for(Map.Entry<String,HashSet<String>> entry: dic.entrySet()){
            System.out.print("3.0 id "+entry.getKey()+" to 4.0 ids: ");
            for(String ss:entry.getValue()){
                System.out.print(ss+" ");
            }
            System.out.println();
        }

        //然后根据原来线上数据库，解析出id， 根据字典，输出映射后的行业id，输出一个 map1 <行号id, hashSet1>集合
        List<String[]> list=readAll(pathOnline);
        HashMap<String,HashSet<String>> map1=new HashMap<>();
        for(int i=0;i<list.size();i++){
            String[] currentLine=list.get(i);
            String id=currentLine[0];
            JSONArray array= JSON.parseArray(currentLine[1]);
            HashSet<String> setAll=new HashSet<>();
            for(int j=0;j<array.size();j++){
                JSONObject obj=array.getJSONObject(j);
                String industryId3=obj.getString("recommendedIndustryId");
                HashSet<String> set2=dic.get(industryId3);//每个原行业id对应的4.0行业id集合
                for(String ss:set2){
                    setAll.add(ss);
                }
            }
            map1.put(id,setAll);
        }
        //读取yaoyuan生成的sql，读取其中的<行号id， hashSet2> 生成map2
       // String pathSql=
        f=new FileReader(pathSql);
        b=new BufferedReader(f);
        HashMap<String,HashSet<String>> map2=new HashMap<>();
        while((s=b.readLine())!=null){
            String[] array=s.split("=");
            String recommends=array[1];
            recommends=recommends.replace(" ,exclude_industry_params ","");
            recommends=recommends.replace("'","");
            recommends=recommends.replaceAll("\\\\","");
            //  System.out.print(recommends+" ");
            String id=array[3];
            HashSet<String> set3=new HashSet<>();
            id=id.replaceAll("\\s+","");
            id=id.replace(";","");
            // System.out.println(id);
            JSONArray jArray=JSON.parseArray(recommends);
            for(int i=0;i<jArray.size();i++){
                JSONObject obj=jArray.getJSONObject(i);
                String indusId=obj.getString("recommendedIndustryId");
                set3.add(indusId);
                String indusName=obj.getString("recommendedIndustryName");
                String name4=dicName.get(indusId);
                if(!indusName.equals(name4)){//校验SQL里面的行业名称是否正确
                    System.out.println("id "+id+" indusId "+indusId+" has wrong name: "+indusName);
                }
            }
            map2.put(id,set3);
        }
        //检查是否相等 map1里面的hashSet1与map2里面的hashSet2
        for(Map.Entry<String,HashSet<String>> entry:map1.entrySet()){
            String id=entry.getKey();
            HashSet<String> set1=entry.getValue();
            if(map2.containsKey(id)){
                HashSet<String> set2=map2.get(id);
                boolean result=setEquals(set1,set2);
                System.out.println("id "+id+" RESULT: "+result);
            }
            else{
                System.out.println("id "+id+" online is not contained in SQL!");
            }
        }
    }

    public static List<String[]> readAll(String filePath) throws Exception {
        File file = new File(filePath);
        FileReader fReader = new FileReader(file);
        CSVReader csvReader = new CSVReader(fReader);
        //读取全部用readAll
        List<String[]> list = csvReader.readAll();
        csvReader.close();
        return list;
    }
    public static boolean setEquals(HashSet<String> set1, HashSet<String> set2){
        boolean result=true;
        for(String s:set1){
            if(!set2.contains(s)){
                System.out.println("Set2 doesn't contains : "+s);
                result=false;
            }
        }
        for(String s:set2){
            if(!set1.contains(s)){
                System.out.println("Set1 doesn't contains : "+s);
                result=false;
            }
        }
        return result;
    }
}
