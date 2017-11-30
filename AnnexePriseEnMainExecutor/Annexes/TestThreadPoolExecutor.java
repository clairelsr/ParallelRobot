package Annexes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolExecutor {

  public static void main(String[] args) throws InterruptedException {

    ExecutorService executorService = new ThreadPoolExecutor(2, 4, 60,
        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    for (int i = 0; i < 5; i++) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          System.out.println("debut tache " + Thread.currentThread().getName());
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("fin tache");
        }
      });
    }

    System.out.println("Autre traitement");

    executorService.shutdown();
    executorService.awaitTermination(300, TimeUnit.SECONDS);

    System.out.println("Fin thread principal");
  }
}