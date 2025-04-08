package by.lobanov.javamultithreading.synch.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        // Создаем Semaphore с количеством разрешений = 3
        Semaphore semaphore = new Semaphore(3);

        // Запускаем 5 потоков, которые пытаются получить доступ к ресурсу
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(semaphore), "Thread-" + i).start();
        }
    }

    static class Task implements Runnable {
        private final Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // Поток пытается получить разрешение
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " получил доступ к ресурсу.");
                Thread.sleep(1000); // имитация работы с ресурсом
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Поток освобождает разрешение
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " освободил ресурс.");
            }
        }
    }
}
