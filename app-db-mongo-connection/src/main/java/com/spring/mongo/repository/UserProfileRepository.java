package com.spring.mongo.repository;

import com.spring.mongo.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, String>{
}
