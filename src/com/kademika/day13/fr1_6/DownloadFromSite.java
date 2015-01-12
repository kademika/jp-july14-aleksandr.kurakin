package com.kademika.day13.fr1_6;

import java.io.*;
import java.net.*;

/**
 * Created by kurakinaleksandr on 24.12.14.
 */
public class DownloadFromSite {

    public static void main(String args[]) {
        int size = 1024;
        File site = new File("site.html");

        OutputStream outStream = null;
        InputStream is;

        try {
            URL url;
            byte[] buf;
            int ByteRead;
            url = new URL("http://zakupki.gov.ru/epz/order/notice/ea44/view/common-info.html?regNumber=0148300015814000370");
            System.out.println(url.getFile());
            outStream = new BufferedOutputStream(new FileOutputStream(site));
            URLConnection uCon = url.openConnection();
            is = uCon.getInputStream();
            buf = new byte[size];
            while ((ByteRead = is.read(buf)) != -1) {
                outStream.write(buf, 0, ByteRead);
            }
            System.out.println("Downloaded Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
