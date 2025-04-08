package by.lobanov.javamultithreading.synch.phaser;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        // Создаем Phaser для 3 потоков
        Phaser phaser = new Phaser(3);

        // Запускаем 3 потока, каждый из которых участвует в нескольких фазах
        for (int i = 0; i < 3; i++) {
            new Thread(new Task(phaser), "Thread-" + i).start();
        }
    }

    static class Task implements Runnable {
        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                // Фаза 1
                System.out.println(Thread.currentThread().getName() + " выполняет фазу 1.");
                Thread.sleep(500); // имитация работы
                phaser.arriveAndAwaitAdvance(); // Переход к следующей фазе

                // Фаза 2
                System.out.println(Thread.currentThread().getName() + " выполняет фазу 2.");
                Thread.sleep(500); // имитация работы
                phaser.arriveAndAwaitAdvance(); // Переход к следующей фазе

                // Фаза 3
                System.out.println(Thread.currentThread().getName() + " выполняет фазу 3.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}