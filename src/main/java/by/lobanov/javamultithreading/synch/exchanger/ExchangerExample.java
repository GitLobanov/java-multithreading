package by.lobanov.javamultithreading.synch.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        // Создаем Exchanger для обмена строками
        Exchanger<String> exchanger = new Exchanger<>();

        // Поток 1 генерирует данные
        new Thread(new Task1(exchanger)).start();

        // Поток 2 обрабатывает данные
        new Thread(new Task2(exchanger)).start();
    }

    static class Task1 implements Runnable {
        private final Exchanger<String> exchanger;

        public Task1(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                String data = "Данные от потока 1";
                System.out.println("Поток 1 отправляет данные: " + data);
                // Обмен данными
                exchanger.exchange(data);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Task2 implements Runnable {
        private final Exchanger<String> exchanger;

        public Task2(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                String data = exchanger.exchange(null); // Получаем данные от первого потока
                System.out.println("Поток 2 получил данные: " + data);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}