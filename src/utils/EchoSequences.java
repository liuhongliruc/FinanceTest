package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author liuhongli <liuhongli@kuaishou.com>
 * Created on 2021-11-15
 */
public class EchoSequences {
    public static void main(String[] args){
        String filePath="/Users/hongliliu/testH/input.txt";
        File file = new File(filePath);
        BufferedReader reader = null;
        HashMap<String,Integer> map1=new HashMap<String,Integer>();
        TreeMap<Integer,Integer> map2=new TreeMap<>();
        Set<String> set=new HashSet<>();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString1 = null;
            String[] str1=null;
            String[] str2=null;
            StringBuffer s1=new StringBuffer();
            StringBuffer s2=new StringBuffer();
            if((tempString1=reader.readLine())!=null)
            {
                tempString1=tempString1.trim();
                tempString1=tempString1.replace(" ","");
                System.out.println(tempString1);
                str1 = tempString1.split(",");
                for(int i=0;i<str1.length;i++){
                    String s=str1[i];
                    s=s.replace("_","");
                    s=s.toLowerCase(Locale.ROOT);
                    str1[i]=s;
                    map1.put(str1[i],i+1);
                }
            }
            if((tempString1=reader.readLine())!=null)
            {
                tempString1=tempString1.trim();
                tempString1=tempString1.replace(" ","");
                System.out.println(tempString1);
                str2 = tempString1.split(",");
                for(int i=0;i<str2.length;i++){
                    String s=str2[i];
                    s=s.replace("_","");
                    s=s.toLowerCase(Locale.ROOT);
                    str2[i]=s;
                    set.add(s);
                }
            }
            for(int i=0;i<str1.length;i++){
                String s=str1[i];
                if(!set.contains(s)){
                    map1.remove(s);
                }
            }
            for(int i=0;i<str2.length;i++){
                if(map1.containsKey(str2[i])){
                    map2.put(map1.get(str2[i]),i+1);
                }
            }
            //hashMap是无序的，需要根据value排一下序。
            List<Entry<String,Integer>> list = new ArrayList<>(map1.entrySet());
            Collections.sort(list,new Comparator<Entry<String,Integer>>() {
                //升序排序
                @Override
                public int compare(Entry<String, Integer> o1,
                        Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }

            });
            //注意这里遍历的是list，也就是我们将map.Entry放进了list，排序后的集合
            for(Entry<String,Integer> entry: list){
                s1.append("$"+entry.getValue()+"FS");
            }
            String result1=s1.toString().substring(0,s1.length()-2);
            System.out.println("cat a.txt|awk -F ',' '{print "+result1+"}'>b.txt");

            for(Entry<Integer,Integer> entry: map2.entrySet()){
                s2.append("$"+entry.getValue()+"FS");
            }
            String result2=s2.toString().substring(0,s2.length()-2);
            System.out.println("cat a.txt|awk -F ',' '{print "+result2+"}'>b.txt");
            reader.close();
             String userId="2111975851";
             Long a=Long.parseLong(userId)%5;
            System.out.println("TABle: "+a);
            Long tableNo =Long.parseLong(userId)%10;
            System.out.println("DB: "+tableNo/5);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}
