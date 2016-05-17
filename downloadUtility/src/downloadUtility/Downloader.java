package downloadUtility;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * Created by kurakinaleksandr on 08.05.16.
 */
public class Downloader implements Callable<Long> {
    private String folder;
    private ArrayBlockingQueue<String> queue;
    private long threadTotalReceived = 0;
    private int speedLimit;

    private final int BUFFSIZE = 131072; //131072 8192 размер буфера нагуглил, особенной разницы в быстродействии не заметил
    private long cycleTime;

    private OutputStream outStream;
    private InputStream inStream;
    private byte[] buf;
    private int byteRead;
    private String line;

    @Override
    public Long call() {

        // Один поток в цикле работает пока в очереди остаются строки с заданиями
        while ((line = queue.poll()) != null) {
            // Разбор очередной строки из очереди
            String urlAdress = line.substring(0, line.lastIndexOf(" "));
            String fileName = line.substring(line.lastIndexOf(" ") + 1);

            // Создаем файл для записи
            File destination = new File(folder + fileName);
            try {

                // подготовка ресурсов к скачиванию и сохранению фалов
                URL url = new URL(urlAdress);
                outStream = new BufferedOutputStream(new FileOutputStream(destination), BUFFSIZE);
                URLConnection uCon = url.openConnection();
                System.out.println(fileName + " dowloading, size: " + uCon.getContentLength());
                inStream = new BufferedInputStream(uCon.getInputStream(), BUFFSIZE);
                // inStream = uCon.getInputStream();

                long totalBytesRead = 0;
                buf = new byte[BUFFSIZE];

                // Фиксация времени первой итерации
                long startTime = System.currentTimeMillis();
                long fileStartTime = System.currentTimeMillis();

                // Ситаем данные из сети и пишем в файл
                while ((byteRead = inStream.read(buf)) != -1) {

                    outStream.write(buf, 0, byteRead);
                    totalBytesRead += byteRead;

                    // Одна транзакция должна выполняться в течение byteRead / speedLimit таким образом гарантируется ограничение скорости скачивания
                    cycleTime = 1000 * byteRead / speedLimit;
                    long endTime = System.currentTimeMillis();

                    // Расчитываем фактическое время 1 транзакции
                    long lastedTime = endTime - startTime;

                    // Если время транзакции менее расчетного - требуется ограничение, поток останавливается на недостающее время
                    if (cycleTime > lastedTime) {

                        try {
                            Thread.sleep(cycleTime - lastedTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // Задаем начальное время новой транзакции и уходим в начало цикла
                    startTime = System.currentTimeMillis();
                }

                // Подсчет скачанных данных
                threadTotalReceived += totalBytesRead;
                // Вывод статистики по файлу
                System.out.println(fileName + " downloaded ok. Total bytes received: " + totalBytesRead
                        + ", downloaded in: " + (System.currentTimeMillis() - fileStartTime) / 1000 + " seconds.");

            } catch (Exception e) {
                e.printStackTrace();
                // Closing Streams
            } finally {
                if (outStream != null) {
                    try {
                        outStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Thread total size downloaded: " + threadTotalReceived);
        return threadTotalReceived;
    }

    // Setter-ы
    public void setLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setQueue(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }


}


