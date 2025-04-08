package by.lobanov.javamultithreading.exception;

class JavaCoreUncaughtException extends RuntimeException {
    public JavaCoreUncaughtException(String message) {
        super(message);
    }

    public JavaCoreUncaughtException(String message, Throwable cause) {
        super(message, cause);
    }
}