package com.paytm.local.datasource.repository;

import com.paytm.local.datasource.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByName(String name);

    User findById(Long id);

}
