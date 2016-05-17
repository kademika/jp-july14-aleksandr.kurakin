package downloadUtility;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by kurakinaleksandr on 08.05.16.
 */
public class Starter {

    private static int n;
    private static int l;
    private static String o;
    private static String f;
    private static ArrayBlockingQueue<String> queue;


    public void init(String[] args) {
        // java -jar utility.jar -n 5 -l 2000k -o output_folder -f links.txt

        // -n - количество потоков int
        // -l - ограничение скорости скачивания k= *1024, m=*1024*1024
        // -o - выходная папка
        // -f - файл со ссылками
        int length = args.length;
        if (length != 8) {
            System.out.println("Invalid parameters. -n - количество потоков, -l - ограничение скорости скачивания, -o - выходная папка, -f - файл со ссылками");
            System.exit(1);
        }


        // разбираем args[] для установки параметров
        for (int i = 0; i < length; i += 2) {
            if (args[i].equals("-n")) {
                n = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("-l")) {
                l = parseLimit(args[i + 1]);
            } else if (args[i].equals("-o")) {
                o = args[i + 1];
            } else if (args[i].equals("-f")) {
                f = args[i + 1];
            }
        }
        queue = new ArrayBlockingQueue(n+1, true);
        System.out.println("Количество потоков: " + n);
        System.out.println("Общее ограничение скорости скачивания: " + l);
        System.out.println("Выходная папка: " + o);
        System.out.println("Файл со сссылками: " + f);


    }

    //
    public static void readFromFile() {

        String links;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(f));
            for (int i = 0; (links = reader.readLine()) != null; i++) {
                try {
                    queue.put(links);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Link " + i + " added to queue.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int parseLimit(String lim) {
        int limit;

        if (lim.substring(lim.length() - 1).equals("k")) {
            limit = 1024 * Integer.parseInt(lim.substring(0, lim.length() - 1));
        } else if (lim.substring(lim.length() - 1).equals("m")) {
            limit = 1024 * 1024 * Integer.parseInt(lim.substring(0, lim.length() - 1));
        } else limit = Integer.parseInt(lim);
        return limit;
    }

    public int getN() {
        return n;
    }

    public int getL() {
        return l;
    }

    public String getO() {
        return o;
    }

    public ArrayBlockingQueue<String> getQueue() {
        return queue;
    }

}
