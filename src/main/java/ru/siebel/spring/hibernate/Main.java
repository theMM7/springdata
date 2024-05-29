package ru.siebel.spring.hibernate;


import ru.siebel.spring.hibernate.entity.Auto;
import ru.siebel.spring.hibernate.entity.User;
import ru.siebel.spring.hibernate.service.UserService;

public class Main {
    //весь пример в директории hibernate - лишь показательный, а не рабочий
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = new User("John",26);
        userService.saveUser(user);
        Auto ferrari = new Auto("BMW", "black");
        ferrari.setUser(user);
        user.addAuto(ferrari);
        Auto ford = new Auto("Mercedes", "white");
        ford.setUser(user);
        user.addAuto(ford);
        userService.updateUser(user);
    }
}
