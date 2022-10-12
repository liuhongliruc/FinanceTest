package com.company;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author xiongtian <xiongtian@kuaishou.com>
 * Created on 2022-06-22
 */
public class Test {
    public static void main(String[] args){
      int m= 48;
       int n= 37;
       int[][] indices= {{40,5}};
       int result=oddCells(m,n,indices);
        System.out.println(result);
    }
    public static int oddCells(int m, int n, int[][] indices) {
        int[][] matrix=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=0;
            }
        }
        for(int i=0;i<indices.length;i++){
            int[] point=new int[2];
            point[0]=indices[i][0];
            point[1]=indices[i][1];
            addRows(matrix,point[0],point[1]);
        }
        int result=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(isOdd(matrix[i][j])){
                    result++;
                }
            }
        }
        return result;
    }
    public static void addRows(int[][] matrix, int m,int n){
        for(int i=0;i<matrix[m].length;i++){
            matrix[m][i]++;
        }
        for(int j=0;j<matrix.length;j++){
            matrix[j][n]++;
        }


    }
    public static boolean isOdd(int n){
        return n%2==1;
    }
    public static boolean isAnagram(String s, String t) {
        if(s==null&&t==null)return true;
        if(s==null||t==null)return false;
        if(s.equals("")&&t.equals(""))return true;
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for(int i=0;i<t.length();i++){
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)-1);
        }
        for(Entry<Character,Integer> entry: map.entrySet())
        {
            if(entry.getValue()!=0)return false;
        }
        return true;
    }
}
