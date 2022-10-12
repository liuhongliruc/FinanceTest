import java.util.HashMap;
import java.util.Map;

/**
 * @author liuhongli <liuhongli@kuaishou.com>
 * Created on 2022-08-05
 */
public class Test {
    public static void main(String[] args){
        String s="a,a,!";
//        s=s.replaceAll("[^a-zA-Z0-9]","");
//        char[] array=s.toCharArray();
//        boolean result=true;
//        for(int i=0,j=s.length()-1;i<j;i++,j--){
//            if(array[i]!=array[j]){
//                result=false;
//            }
//        }
//        System.out.println(result);

        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for(Map.Entry entry:map.entrySet()){
            System.out.print(entry.getKey()+" ");
            System.out.println(entry.getValue());
        }
    }
}
