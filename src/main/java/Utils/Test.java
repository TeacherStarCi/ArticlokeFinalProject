
package Utils;

import java.sql.Timestamp;

public class Test {
    public static void main(String[] args) {
        System.out.println(new Timestamp(0));
        System.out.println(Utils.getRemainingTimeForPublisher(new Timestamp(2022-1900,10,3,0,0,0,0), true));
       System.out.println(Utils.getRemainingTime(new Timestamp(2022-1900,10,3,0,0,0,0), true));   
        System.out.println(Utils.getDateFromMillis(1012344441l));
        
    }
}
