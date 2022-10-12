import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liuhongli <liuhongli @kuaishou.com>
 * Created on 2022-06-17
 */
public class CalanderTest {
    public static void main(String[] args) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2021, Calendar.DECEMBER, 30);
        Date str1 = calendar1.getTime();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format2 = new SimpleDateFormat("YYYY-MM-dd");
       // System.out.println("2021-12-30 to yyyy-MM-dd: " + format1.format(str1));
       // System.out.println("2021-12-30 to YYYY/MM/dd: " + format2.format(str1));

//        String s="";
//        int a=Integer.parseInt(s);
//        System.out.println(a);
        System.out.println(0.1+0.2);

    }
}
