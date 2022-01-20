package com.init.User.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.init.User.Model.User;

@Repository
public interface UserDao extends MongoRepository<User, String> {

}
