package downloadUtility;

import java.util.concurrent.*;

/**
 * Created by kurakinaleksandr on 28.04.16.
 */
public class Utility {
    private static long totalSize = 0;
    private static int n;
    private static int l;
    private static String o;
    private static ArrayBlockingQueue<String> queue;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Starter starter = new Starter();
        starter.init(args);

        n = starter.getN();
        l = starter.getL();
        o = starter.getO();
        queue = starter.getQueue();

        // Запускаем в отдельном потоке считывание строк из файла с ссылками
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread READER started!");
                starter.readFromFile();

            }
        }).start();

        // Запускаем пул потоков фиксированной длинны, тут будут исполняться задания, результатом каждого будет общее количество байтов скачанных каждым потоком
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        Future<Long>[] result = new Future[n];
        for (int i = 0; i < n; i++) {

            // Создание объектов, инициация и старт потоков
            Downloader downloader = new Downloader();
            downloader.setFolder(o);
            downloader.setQueue(queue);
            downloader.setLimit(l / n);
            result[i] = executorService.submit(downloader);
            System.out.println("Thread " + i + " started!");
        }
        // Обработка полученных после выполнения заданий данных
        for (Future<Long> result1 : result) {
            try {
                totalSize += result1.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

        // Вывод окончательной статистики
        System.out.println("Total bytes downloaded: " + totalSize);
        System.out.println("Total time: " + (int) ((System.currentTimeMillis() - startTime) / 1000) + " seconds.");
    }

}


