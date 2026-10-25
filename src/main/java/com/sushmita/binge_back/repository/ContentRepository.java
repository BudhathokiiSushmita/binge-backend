package com.sushmita.binge_back.repository;

import com.sushmita.binge_back.constants.ContentEnum;
import com.sushmita.binge_back.model.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sushmita Budhathoki on 2024-07-15
 */

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {

   List<Content> findAllByType(ContentEnum type);


   List<Content> findAllByTypeAndIsFeatured(ContentEnum type, Boolean isFeatured);

}
