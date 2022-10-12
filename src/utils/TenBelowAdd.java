package utils;

/**
 * @author liuhongli <liuhongli@kuaishou.com>
 * Created on 2021-12-03
 */
public class TenBelowAdd {
    public static void main(String[] args){
      // TenBelowAdd();
         TenBelowMinus();
    }
    public static void TwentyBelowAdd(){
        int k=0;
        for(int i=0;i<=10;i++){
            for(int j=i;j<=10;j++){
                System.out.print(k+++" ");
                System.out.println(i+"+"+j+"= ");
                if(i!=j){
                    System.out.println(j + "+" + i + "= ");
                }
                else{
                    System.out.println();
                }
            }
        }
    }
    public static void TenBelowAdd(){
        int k=0;
        for(int i=0;i<=10;i++){
            for(int j=i;j<=10;j++){
                if(i+j<=10) {
                    System.out.print(k++ + " ");
                   System.out.println(i + "+" + j + "= ");
//                    if(i!=j){
//                        System.out.print(k + " ");
//                        k++;
//                        System.out.println(j + "+" + i + "= ");
//                    }
//                    else{
//                        System.out.println();
//                    }
                }
            }
        }
    }
    public static void TenBelowMinus(){
        int k=0;
        for(int i=10;i>=1;i--){
            for(int j=i;j>=0;j--){
                System.out.print(k+++" ");
                System.out.println(i+" - "+j+" = ");
            }
        }
    }

}
