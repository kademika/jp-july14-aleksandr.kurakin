package com.kademika.day11.zipArchiver;

import java.io.File;

/**
 * Created by kurakinaleksandr on 27.08.14.
 */
public class DemoZipArchiver {
    public static void main(String[] args) {
        File testZipArchive = new File("/Users/kurakinaleksandr/documents/for_archivator.zip/");
        File testUnZipArchive = new File("/Users/kurakinaleksandr/documents/for_archivator/");
//        ZipArchiver.main("/Users/kurakinaleksandr/documents/for_archivator/", "zip");
        ZipArchiver.main("/Users/kurakinaleksandr/documents/for_archivator.zip/", "unzip");

    }
    }

