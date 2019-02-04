package com.paytm.local.controller;

import com.paytm.local.dto.UserDetailsDTO;
import com.paytm.local.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/local")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get-user")
    public UserDetailsDTO findUserByName(@RequestParam String name){
        return userService.findUser(name);
    }

    @RequestMapping("/add-user")
    public UserDetailsDTO addUser(@RequestParam String name,
                                  @RequestParam String vpa){
        return userService.addUser(name,vpa);
    }

    @RequestMapping("/update-user")
    public UserDetailsDTO updateUser(@RequestParam String name,
                                  @RequestParam String vpa){
        return userService.updateUser(name,vpa);
    }

}
