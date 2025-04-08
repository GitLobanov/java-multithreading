package by.lobanov.javamultithreading.threadlocal.user;

class UserService {
    public void printUserInfo() {
        User currentUser = UserContext.getCurrentUser();
        if (currentUser != null) {
            System.out.println("Текущий пользователь: " + currentUser.getUserId());
        } else {
            System.out.println("Пользователь не найден!");
        }
    }
}
