package com.paytm.local.service.impl;

import com.paytm.local.datasource.dataservice.UserDataService;
import com.paytm.local.datasource.model.User;
import com.paytm.local.dto.UserDetailsDTO;
import com.paytm.local.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDataService userDataService;

    @Override
    public UserDetailsDTO findUser(String name) {
        User user = userDataService.findByName(name);

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

        if(user != null) {
            userDetailsDTO.setDefaultVpa(user.getDefaultVpa());
            userDetailsDTO.setName(user.getName());
        }

        return userDetailsDTO;
    }

    @Override
    public UserDetailsDTO addUser(String name, String vpa) {
        User user = new User();
        user.setName(name);
        user.setDefaultVpa(vpa);

        user = userDataService.addUser(user);

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setDefaultVpa(user.getDefaultVpa());
        userDetailsDTO.setName(user.getName());

        return userDetailsDTO;
    }

    @Override
    public UserDetailsDTO updateUser(String name, String vpa) {
        User user = userDataService.findByName(name);

        if(user != null){
            user.setDefaultVpa(vpa);
        }
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setDefaultVpa(user.getDefaultVpa());
        userDetailsDTO.setName(user.getName());

        return userDetailsDTO;
    }

}
