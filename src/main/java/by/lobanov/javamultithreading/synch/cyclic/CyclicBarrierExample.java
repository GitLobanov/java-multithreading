package by.lobanov.javamultithreading.synch.cyclic;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        // Создаем CyclicBarrier, который ждет 3 потока (для 3 участников)
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("Все потоки готовы, начинаем выполнение задачи.");
        });

        // Запускаем 3 потока
        for (int i = 0; i < 6; i++) {
            new Thread(new Task(barrier), "Thread-" + i).start();
        }
    }

    static class Task implements Runnable {
        private final CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " готовится.");
                Thread.sleep((int)(Math.random() * 1000)); // случайная задержка
                // Поток ожидает, пока все другие не достигнут барьера
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " выполняет задачу.");
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
