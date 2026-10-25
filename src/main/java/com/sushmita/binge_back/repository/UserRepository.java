package com.sushmita.binge_back.repository;

import com.sushmita.binge_back.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Sushmita Budhathoki on 2024-07-15
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findUserByEmail(String email);
}
