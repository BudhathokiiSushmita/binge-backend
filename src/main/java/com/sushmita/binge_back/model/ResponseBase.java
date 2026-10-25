package com.sushmita.binge_back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-07-15
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBase {

   private String message;
   private List body;
}
