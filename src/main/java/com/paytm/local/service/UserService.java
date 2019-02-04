package com.paytm.local.service;

import com.paytm.local.datasource.model.User;
import com.paytm.local.dto.UserDetailsDTO;

public interface UserService {

    UserDetailsDTO findUser(String name);

    UserDetailsDTO addUser(String name, String vpa);

    UserDetailsDTO updateUser(String name, String vpa);

}
