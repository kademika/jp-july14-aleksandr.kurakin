package com.kademika.day11.zipArchiver;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by kurakinaleksandr on 25.08.14.
 */

public class ZipArchivUtils {

    public void zip(File source)  {
        File zipResult = new File(source.getPath() + ".zip");
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipResult));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        doZip(source, out);

    }

    public void doZip(File rootDir, ZipOutputStream out) {
        byte[] buffer = new byte[1024];
        try {
            for (File file : rootDir.listFiles()) {

                if (!file.isDirectory()) {
                    FileInputStream in = new FileInputStream(file);
//                    out.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(rootDir.getAbsolutePath().length()))); // need to make path from
                    // source
                    out.putNextEntry(new ZipEntry(file.getPath())); // need to make path from
                    // source

                    while ((in.read(buffer)) > 0) {
                        out.write(buffer);
                    }
                    out.closeEntry();
                    in.close();

                } else doZip(file, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unZip(File source) throws IOException {

        byte[] buffer = new byte[1024];
        try {

            File unZipResult = new File(source.getParent());
            if (!unZipResult.exists()) {
                unZipResult.mkdir();
            }

            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(source));

            ZipEntry ze = zis.getNextEntry();
            System.out.println(ze.getName());

            while (ze != null) {

                String fileName = ze.getName();

                File newFile = new File(fileName);// here delete all before fileName "unZipResult + File.separator + "

                System.out.println("file unzip : " + newFile.getAbsolutePath());
//                if (newFile.isDirectory()) {
//                    System.out.println(newFile.getAbsoluteFile() + " is directory");
//                    newFile.mkdir();
//                }
                new File(newFile.getParent()).mkdirs();
//                    System.out.println("Directory " + newFile.getParent() + " created");


                    FileOutputStream fos = new FileOutputStream(newFile);

                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }

                    fos.close();
                    ze = zis.getNextEntry();

            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {

    }
}
