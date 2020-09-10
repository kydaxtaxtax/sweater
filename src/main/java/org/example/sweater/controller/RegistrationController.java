package org.example.sweater.controller;

import org.example.sweater.domian.Role;
import org.example.sweater.domian.User;
import org.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER)); //ожидается на вход коллекция в ви де set, но так как у нас 1 значение мы можем использовать шот кат из стандартной беблиотеки который создает set с одним значением
        userRepo.save(user); //сохранение пользователя

        return "redirect:/login";
    }
}
