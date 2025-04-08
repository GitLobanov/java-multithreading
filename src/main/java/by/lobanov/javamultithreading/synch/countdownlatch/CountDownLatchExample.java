package by.lobanov.javamultithreading.synch.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        // Создаем CountDownLatch с начальным значением 3
        CountDownLatch latch = new CountDownLatch(3);

        // Запускаем 3 потока
        for (int i = 0; i < 3; i++) {
            new Thread(new Task(latch), "Thread-" + i).start();
        }

        // Главный поток ждет, пока счетчик не дойдет до нуля
        latch.await();

        // После того как все потоки завершили свою работу
        System.out.println("Все потоки завершены. Главный поток продолжает выполнение.");
    }

    static class Task implements Runnable {
        private final CountDownLatch latch;

        public Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // Имитация работы потока
                System.out.println(Thread.currentThread().getName() + " выполняет задачу.");
                Thread.sleep((int) (Math.random() * 1000)); // случайная задержка
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Снижаем счетчик
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + " завершил свою задачу.");
            }
        }
    }
}
