package com.sushmita.binge_back.controller;

import com.sushmita.binge_back.config.SecurityConfig;
import com.sushmita.binge_back.constants.BingeConstants;
import com.sushmita.binge_back.dto.UserDto;
import com.sushmita.binge_back.model.ResponseBase;
import com.sushmita.binge_back.model.User;
import com.sushmita.binge_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Sushmita Budhathoki on 2024-07-15
 */

@CrossOrigin({"https://binge-theta.vercel.app", "http://localhost:3000"})
@RestController
@RequestMapping(BingeConstants.userAPI)
public class UserController {

   @Autowired
   private UserService userService;

   @PostMapping
   public ResponseEntity registerUser(@RequestBody User user) {
      try {
         return new ResponseEntity<>(new ResponseBase("Successfully saved User", Collections.singletonList(userService.saveUser(user))), HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.BAD_REQUEST);
      }
   }

   @GetMapping("/{id}")
   public ResponseEntity getUser(@PathVariable String id) {
      try {
         return new ResponseEntity<>(new ResponseBase("Successfully retrieved", Collections.singletonList(userService.findUserById(id))), HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.NOT_FOUND);

      }
   }

   @PostMapping("/authenticate")
   public ResponseEntity authenticateUser(@RequestBody UserDto userDto) {
      try {
         return new ResponseEntity<>(new ResponseBase("Successfully logged in", Collections.singletonList(userService.authenticateUser(userDto))), HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.BAD_REQUEST);
      }
   }


}
