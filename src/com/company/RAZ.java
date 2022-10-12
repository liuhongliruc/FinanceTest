package com.company;

/**
 * @author liuhongli <liuhongli@kuaishou.com>
 * Created on 2022-10-08
 */
public class RAZ {
    public static void main(String[] args){
        int currentLen=12;
        int finished=68;
        int beginDate=1;
        int n=30;
        int start=1+finished;
        int end=12+finished;
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
                int num=i-6-(beginDate-1);
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
