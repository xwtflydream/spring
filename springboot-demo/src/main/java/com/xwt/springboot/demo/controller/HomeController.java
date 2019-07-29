package com.xwt.springboot.demo.controller;

import com.xwt.springboot.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepo;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

    @RequestMapping(value = "/test/error")
    public String testError() throws Exception {
        throw new Exception("发生错误");
    }
}
