package com.kademika.day11.theory;

import java.io.File;
import java.io.IOException;


public class PathToFile {
    public static void main(String[] args) throws IOException {
        File file = new File("src/com/kademika/day11/theory/test.txt");
        File file1 = new File("/Users/kurakinaleksandr/documents/Test/"); // is directory
        File file2;
        String pathToCat = null;

        file.createNewFile();
        file1.createNewFile();
        if (file1.mkdirs()) {
            System.out.println("Directory " + file1.getName() + " created");
        } else System.out.println("Directory " + file1.getName() + " did not created");

        pathToCat = file1.getAbsolutePath();
        file2 = new File(pathToCat + "/test2.txt");
        file2.createNewFile();
//        System.out.println(System.getProperty("user.home"));
        File userDir = new File("/Users/kurakinaleksandr/");
        getDirectoryTree(userDir);

//        System.out.println(file1.getParentFile().getAbsolutePath());
//        System.out.println(file.getParentFile().getAbsolutePath());
//        System.out.println(File.listRoots());
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(File.separator);
//        System.out.println(File.pathSeparator);
//        for (File f: File.listRoots()){
//            System.out.println(f.getAbsolutePath());
//        }
//
//        System.out.println(getRelativeFileDir());
//        System.out.println(getFilePath());

    }

    public static String getFilePath() {
        String path = "src/com/kademika/day11/theory/";
        path.replace("/", File.separator);
        File thisFile = new File(path, PathToFile.class.getSimpleName() + ".java");

        return thisFile.getAbsolutePath();
    }

    public static String getRelativeFileDir() {
        String path = "src/com/kademika/day11/theory/";
        return path.replace("/", File.separator);
    }

    public static void listRootsToString() {
        for (File file : File.listRoots()) {
            System.out.println(file.getAbsolutePath());

        }
    }

    public static void getDirectoryTree(File rootDir) {

        System.out.println(rootDir.getPath());

        for (File file : rootDir.listFiles()) {
            if (file.isDirectory()) {
                getDirectoryTree(file);
            }
        }
    }


}
