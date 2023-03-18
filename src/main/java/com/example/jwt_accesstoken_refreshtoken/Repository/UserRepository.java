package com.example.jwt_accesstoken_refreshtoken.Repository;

import com.example.jwt_accesstoken_refreshtoken.Document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
