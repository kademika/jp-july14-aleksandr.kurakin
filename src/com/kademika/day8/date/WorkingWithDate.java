package com.kademika.day8.date;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by kurakinaleksandr on 03.07.14.
 */
public class WorkingWithDate {
    public static void main(String[] args) throws Exception{
        Date today = new Date();
        System.out.println(today);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        System.out.println(sdf.format(today));

        Date date =sdf.parse("25 мар 1977");
        System.out.println(date);
        System.out.println(sdf.format(date));
    }
}
