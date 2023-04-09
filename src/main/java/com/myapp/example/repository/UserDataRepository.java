package com.myapp.example.repository;

import com.myapp.example.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends MongoRepository<UserData,Long> {
}
