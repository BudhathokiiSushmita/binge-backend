package com.sushmita.binge_back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sushmita Budhathoki on 2024-07-17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    private String password;
}
