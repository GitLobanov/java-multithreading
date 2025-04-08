package by.lobanov.javamultithreading.threadlocal.user;

public class ThreadLocalUserExample {
    public static void main(String[] args) throws InterruptedException {
        // Симуляция обработки двух различных запросов в разных потоках

        Thread thread1 = new Thread(() -> {
            // Устанавливаем текущего пользователя для первого потока
            UserContext.setCurrentUser(new User("user123"));

            // Сервис обрабатывает запрос с контекстом пользователя
            UserService userService = new UserService();
            userService.printUserInfo();

            // Очищаем контекст после обработки запроса
            UserContext.clear();
        });

        Thread thread2 = new Thread(() -> {
            // Устанавливаем текущего пользователя для второго потока
            UserContext.setCurrentUser(new User("user456"));

            // Сервис обрабатывает запрос с контекстом пользователя
            UserService userService = new UserService();
            userService.printUserInfo();

            UserContext.clear();
        });

        // Запускаем потоки, которые имитируют обработку HTTP-запросов
        thread1.start();
        thread2.start();

        // Ждем завершения потоков
        thread1.join();
        thread2.join();

        UserService userService = new UserService();
        userService.printUserInfo();
    }
}