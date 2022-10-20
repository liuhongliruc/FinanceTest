package com.company;

/**
 * @author liuhongli <liuhongli@kuaishou.com>
 * Created on 2022-10-08
 */
public class RAZ {
    public static void main(String[] args){
        int len=12;
        int finished=38;
        int beginDate=20;
        int n=30;
        calculate(len,n,finished,beginDate);
    }
    public static void calculate(int currentLen,int n, int finished,int beginDate){
        int start=1+finished;
        int end=currentLen+finished;
        for(int i=beginDate;i<=n;i++){
            System.out.print(i+"号\t");
            if(currentLen<12){
                //                for(int j=1;j<=2*i;j++){
                //                    System.out.print(j+"\t");
                //                }
                System.out.print("1 -- ");
                System.out.print(currentLen);
                System.out.println();
                currentLen+=2;
            }
            else{
                int num=i-currentLen/2-(beginDate-1);
                //                for(int j=0;j<num*2;j++){
                //                    System.out.print("\t");
                //                }
                //                for(int j=1;j<=12;j+*){
                //                    System.out.print(j+num*2);
                //                    System.out.print("\t");
                //                }
                System.out.print(start+num*2);
                System.out.print(" -- ");
                System.out.print(end+num*2);
                System.out.println();
            }

        }
    }
}
