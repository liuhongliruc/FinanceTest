package utils;

import java.util.Arrays;

/**
 * @author liuhongli <liuhongli@kuaishou.com>
 * Created on 2021-12-08
 */
public class StringToArray {
    public static void main(String[] args){
        String []s1={"abc","123","dbc"};
        String []s2={"m1","m2","m3"};
        String [][]total=new String[2][3];
        total[0]=s1;
        total[1]=s2;
        System.out.println(s1.toString());
    }
}
