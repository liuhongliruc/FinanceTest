package com.company;

/**
 * @author xiongtian <xiongtian@kuaishou.com>
 * Created on 2021-10-19
 */
class Solution {
    public static int myAtoi(String s) {
        if(s==null||s.equals(""))return 0;
        String t=s.replace(" ","");
        if(t.equals(""))return 0;
        s=s.trim();
        char[] array=s.toCharArray();
        if(!Character.isDigit(array[0])&&array[0]!='-'&&array[0]!='+'){
            return 0;
        }
        long result=0L;
        if(array[0]=='-'){
            for(int i=1;i<array.length;i++){
                if(!Character.isDigit(array[i]))break;
                result=result*10+array[i]-'0';
                if(result>Integer.MAX_VALUE)
                    return Integer.MIN_VALUE;
            }
            result=-result;
        }
        else if(array[0]=='+'){
            for(int i=1;i<array.length;i++){
                if(!Character.isDigit(array[i]))break;
                result=result*10+array[i]-'0';
                if(result>Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
            }
        }
        else{
            for(int i=0;i<array.length;i++){
                if(!Character.isDigit(array[i]))break;
                result=result*10+array[i]-'0';
                if(result>Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
            }
        }
        return (int)result;
    }
    public static void main(String[] args){
        String s="+";
        int a=myAtoi(s);
        System.out.println(myAtoi(s));
    }
}