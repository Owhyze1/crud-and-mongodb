package com.example.springmongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springmongo.api.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

	UserModel findByUsername(String username);
}
