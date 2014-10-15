package com.kademika.day11.rwFiles;


import java.io.*;


public class FileCopier {
    public static void main(String[] args) {

        File source = new File("/Users/kurakinaleksandr/ansitest.txt");
        changeEncoding(source,"cp1251", "UTF-8");
//            copyFile(source);

    }


    public static void copyFile(File src) throws FileNotFoundException {
        File result = new File(src.getParent() + "/Copy of " + src.getName());
        int size;
        try (FileInputStream inputStream = new FileInputStream(src); FileOutputStream outputStream = new FileOutputStream(result)) {
            size = inputStream.available();
            for (int i = 0; i < size; i++) {
                outputStream.write(inputStream.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeEncoding (File file, String currentEncoding, String neededEncoding){
        File result = new File(file.getParent() + "/New encoded in " + neededEncoding + " " + file.getName());

        int c;
        try (FileInputStream fis = new FileInputStream(file); InputStreamReader reader = new InputStreamReader(fis, currentEncoding);
            FileOutputStream fos = new FileOutputStream(result); OutputStreamWriter writer = new OutputStreamWriter(fos, neededEncoding))
        {
            while ((c=reader.read()) !=-1) {
                writer.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
