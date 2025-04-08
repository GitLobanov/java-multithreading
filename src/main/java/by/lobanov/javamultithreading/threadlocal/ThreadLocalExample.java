package by.lobanov.javamultithreading.threadlocal;

public class ThreadLocalExample {
    // Создаем ThreadLocal для хранения строки, уникальной для каждого потока
    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Начальное значение");

    public static void main(String[] args) throws InterruptedException {
        // Запускаем несколько потоков для демонстрации
        Thread thread1 = new Thread(() -> {
            // В потоке 1 получаем значение ThreadLocal
            System.out.println("Thread 1, значение 1: " + threadLocal.get());

            // Меняем значение ThreadLocal в потоке 1
            threadLocal.set("Измененное значение 1");

            // В потоке 1 снова получаем значение ThreadLocal
            System.out.println("Thread 1, значение 2: " + threadLocal.get());
        });

        Thread thread2 = new Thread(() -> {
            // В потоке 2 получаем значение ThreadLocal
            System.out.println("Thread 2, значение 1: " + threadLocal.get());

            // Меняем значение ThreadLocal в потоке 2
            threadLocal.set("Измененное значение 2");

            // В потоке 2 снова получаем значение ThreadLocal
            System.out.println("Thread 2, значение 2: " + threadLocal.get());
        });

        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Ждем завершения потоков
        thread1.join();
        thread2.join();

        // Получаем значение ThreadLocal в основном потоке
        System.out.println("Main thread, значение: " + threadLocal.get());
    }
}