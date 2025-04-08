package by.lobanov.javamultithreading.threadlocal.user;

class UserContext {
    private static ThreadLocal<User> currentUser = ThreadLocal.withInitial(() -> null);

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}