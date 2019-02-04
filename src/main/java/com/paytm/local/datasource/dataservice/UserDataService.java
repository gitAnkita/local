package com.paytm.local.datasource.dataservice;

import com.paytm.local.datasource.model.User;

public interface UserDataService {

    User findByName(String name);

    User addUser(User user);

    User updateUser(User user);

    User findById(Long id);

}
