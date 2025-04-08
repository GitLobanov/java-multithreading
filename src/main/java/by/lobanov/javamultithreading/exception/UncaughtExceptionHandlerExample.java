package by.lobanov.javamultithreading.exception;

public class UncaughtExceptionHandlerExample {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            if (throwable instanceof JavaCoreUncaughtException) {
                System.out.println("Опа! Поймали!");
            } else {
                System.out.println("Необработанное исключение: " + throwable.getClass().getSimpleName());
                throwable.printStackTrace();
            }
        });

        throw new JavaCoreUncaughtException("Что-то пошло не так!");
    }
}
