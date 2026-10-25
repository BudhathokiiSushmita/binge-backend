package com.sushmita.binge_back.service;

import com.sushmita.binge_back.config.SecurityConfig;
import com.sushmita.binge_back.dto.UserDto;
import com.sushmita.binge_back.model.User;
import com.sushmita.binge_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.stream.Stream;

/**
 * @author Sushmita Budhathoki on 2024-07-15
 */

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private SecurityConfig securityConfig;

   public User saveUser(User user) throws Exception {
      if(Stream.of(user.getFirstName(),
              user.getLastName(),
              user.getPassword(),
              user.getEmail()).anyMatch(field -> field == null || field.isEmpty())) {
      throw new Exception("Incomplete data");
      }

      if(user.getId() != null) {
         if (!ObjectUtils.isEmpty(userRepository.findById(user.getId()))) {
            throw new Exception("User already exists");
         }
      }

      if (!ObjectUtils.isEmpty(userRepository.findUserByEmail(user.getEmail()))) {
         throw new Exception("User already exists");
      }

      user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
      return userRepository.save(user);
   }

   public User findUserById(String id) {
      return userRepository.findById(id).orElseThrow(() -> new NullPointerException("User not found"));
   }

   public User authenticateUser(UserDto userDto) {
      User existingUser = userRepository.findUserByEmail(userDto.getEmail());

        if(!securityConfig.passwordEncoder().matches(userDto.getPassword(), existingUser.getPassword()) || existingUser == null) {
           throw new NullPointerException("Invalid Credentials");
        }

        return existingUser;
   }
}
