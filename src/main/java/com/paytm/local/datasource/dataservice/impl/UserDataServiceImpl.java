package com.paytm.local.datasource.dataservice.impl;

import com.paytm.local.datasource.dataservice.UserDataService;
import com.paytm.local.datasource.model.User;
import com.paytm.local.datasource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(transactionManager = "localTransactionManager",rollbackFor = Exception.class)
    public User findByName(String name){
        return userRepository.findByName(name);
    }

    @Override
    @Transactional(transactionManager = "localTransactionManager",rollbackFor = Exception.class)
    public User addUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

}
