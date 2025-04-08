package by.lobanov.javamultithreading.synch.phaser;

import java.util.concurrent.Phaser;

public class PhaserDynamicExample {

    public static void main(String[] args) throws InterruptedException {
        // Создаем Phaser с 0 участниками
        Phaser phaser = new Phaser(1); // 1 - главный поток

        // Запускаем два потока, которые начинают работать сразу
        new Thread(new Task(phaser), "Thread-1").start();
        new Thread(new Task(phaser), "Thread-2").start();

        // Ждем некоторое время, чтобы оба потока начали свою работу
        Thread.sleep(1000);

        // Добавляем ещё два потока в фазер
        phaser.register();  // Регистрируем 1-й поток
        new Thread(new Task(phaser), "Thread-3").start();
        new Thread(new Task(phaser), "Thread-4").start();

        // Главный поток будет ждать завершения всех участников
        System.out.println("Главный поток ожидает завершения всех потоков...");

        // Главный поток синхронизируется с другими потоками на барьере
        phaser.awaitAdvance(phaser.getPhase());

        System.out.println("Все потоки завершили выполнение.");
    }

    static class Task implements Runnable {
        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                // Имитация работы потока
                System.out.println(Thread.currentThread().getName() + " начал выполнение.");
                Thread.sleep((int)(Math.random() * 1000)); // случайная задержка
                System.out.println(Thread.currentThread().getName() + " завершил свою часть работы.");

                // Поток достигает фазы и синхронизируется
                phaser.arriveAndAwaitAdvance(); // Ожидаем всех участников

                // Работаем после синхронизации
                System.out.println(Thread.currentThread().getName() + " продолжает выполнение.");

                // Завершаем работу потока и "выходим" из Phaser
                phaser.arriveAndDeregister();
                System.out.println(Thread.currentThread().getName() + " завершил выполнение.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
