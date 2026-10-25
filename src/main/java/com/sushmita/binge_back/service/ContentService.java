package com.sushmita.binge_back.service;

import com.sushmita.binge_back.constants.ContentEnum;
import com.sushmita.binge_back.model.Content;
import com.sushmita.binge_back.model.User;
import com.sushmita.binge_back.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Sushmita Budhathoki on 2024-07-15
 */

@Service
public class ContentService {

   @Autowired
   private ContentRepository contentRepository;

   @Autowired
   private MongoTemplate mongoTemplate;

   public List<Content> getAllContentByType(ContentEnum type) {
      List<Content> contentList = contentRepository.findAllByType(type);
      if (ObjectUtils.isEmpty(contentList)) {
         throw new NullPointerException("Cannot find " + type);
      }

      return contentList;
   }

   public List<Content> getAllContentByTypeAndSearch(ContentEnum type, String search) {
      Query query = new Query();
      query.addCriteria(Criteria.where("title").regex("^" + search, "i"));
      query.addCriteria(Criteria.where("type").is(type));
      List<Content> contentList =  mongoTemplate.find(query, Content.class);

      if (ObjectUtils.isEmpty(contentList)) {
         throw new NullPointerException("Not found");
      }

      return contentList;
   }

   public List<Content> getAllFeaturedContentByType(ContentEnum type) {
      List<Content> contentList = contentRepository.findAllByTypeAndIsFeatured(type, Boolean.TRUE);
      if (ObjectUtils.isEmpty(contentList)) {
         throw new NullPointerException("Cannot find " + type);
      }

      return contentList;
   }

   public Content getContentById(String id) {
      return contentRepository.findById(id).orElseThrow(() -> new NullPointerException("Invalid ID"));
   }

   public Content saveContent(Content content) {
      if(Stream.of(content.getTitle(),
              content.getPrice()  == null ? "" : content.getPrice().toString(),
              content.getSynopsis(),
              content.getType()  == null ? "" : content.getType().toString(),
              content.getSmallPoster(),
              content.getLargePoster(),
              content.getRentPrice() == null ? "" : content.getRentPrice().toString(),
              content.getPurchasePrice()  == null ? "" : content.getPurchasePrice().toString(),
              content.getIsFeatured()  == null ? "" : content.getIsFeatured().toString())
              .anyMatch(field -> field == null || field.isEmpty())) {
         throw new NullPointerException("Incomplete data");
      }

      return contentRepository.save(content);
   }
   public Content updateContent(Content content) {
      if(content.getId().isEmpty()) {
         throw new NullPointerException("No data to update");
      }

      if(Stream.of(content.getTitle(),
                      content.getPrice()  == null ? "" : content.getPrice().toString(),
                      content.getSynopsis(),
                      content.getType()  == null ? "" : content.getType().toString(),
                      content.getSmallPoster(),
                      content.getLargePoster(),
                      content.getRentPrice() == null ? "" : content.getRentPrice().toString(),
                      content.getPurchasePrice()  == null ? "" : content.getPurchasePrice().toString(),
                      content.getIsFeatured()  == null ? "" : content.getIsFeatured().toString())
              .anyMatch(field -> field == null || field.isEmpty())) {
         throw new NullPointerException("Cannot update data");
      }

      return contentRepository.save(content);
   }

   public void deleteContent(String id) throws Exception {
      try {
         contentRepository.deleteById(id);
      } catch (Exception e) {
         throw new Exception("Cannot delete");
      }
   }
}
