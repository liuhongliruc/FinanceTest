package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
       String s="cba";
       char[] array=s.toCharArray();
        TreeMap<Character,Integer> map=new TreeMap<>();
        HashMap<Character,Integer> map2=new HashMap<>();
       for(int i=0;i<array.length;i++){
           map.put(array[i],map.getOrDefault(array[i],0)+1);
           if(!map2.containsKey(array[i])){
               map2.put(array[i],i);
           }
       }
       char c='$';
       for(Map.Entry entry:map.entrySet()){
           if((int)entry.getValue() == 1) {
               System.out.println(entry.getKey());
               c=(char)entry.getKey();
               break;
           }
       }
       if(c=='$'){
           System.out.println(-1);
       }
       else{
           int pos=map2.get(c);
           System.out.println(pos);
       }
    }
}
