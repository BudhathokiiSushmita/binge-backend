package com.sushmita.binge_back.controller;

import com.sushmita.binge_back.constants.BingeConstants;
import com.sushmita.binge_back.constants.ContentEnum;
import com.sushmita.binge_back.model.Content;
import com.sushmita.binge_back.model.ResponseBase;
import com.sushmita.binge_back.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Sushmita Budhathoki on 2024-07-15
 */

@CrossOrigin({"https://binge-theta.vercel.app", "http://localhost:3000"})
@RestController
@RequestMapping(BingeConstants.contentAPI)
public class ContentController {

    @Autowired
    private ContentService contentService;


    /*
     * 1.Add Content */
    @PostMapping()
    public ResponseEntity  saveContent(@RequestBody Content content) {
        try {
            ResponseBase responseBase = new ResponseBase("Successfully saved data", Collections.singletonList(contentService.saveContent(content)));
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    * 2 & 3 All Movie or Tv shows*/
    @GetMapping("/all/{type}")
    public ResponseEntity  fetchAllContentBasedOnType(@PathVariable String type) {
        try {
            ResponseBase responseBase = new ResponseBase("Successfully retrieved data", contentService.getAllContentByType(ContentEnum.valueOf(type.toUpperCase())));
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * 4 Title wise filter */
   //TODO
    @GetMapping("/all/{type}/{search}")
    public ResponseEntity  fetchAllContentBasedOnType(@PathVariable String type,
                                                      @PathVariable String search) {
        try {
            ResponseBase responseBase = new ResponseBase("Successfully retrieved data", contentService.getAllContentByTypeAndSearch(ContentEnum.valueOf(type.toUpperCase()), search));
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * 5 & 6 Featured Movie or Tv shows.*/
    @GetMapping("/featured/{type}")
    public ResponseEntity fetchAllFeaturedContentBasedOnType(@PathVariable String type) {
        try {
            ResponseBase responseBase = new ResponseBase("Successfully retrieved data", contentService.getAllFeaturedContentByType(ContentEnum.valueOf(type.toUpperCase())));
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * 7 Specific Movie or Tv shows.*/
    @GetMapping("/{id}")
    public ResponseEntity fetchContentById(@PathVariable String id) {
        try {
            ResponseBase responseBase = new ResponseBase("Successfully retrieved data", Collections.singletonList(contentService.getContentById(id)));
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
     * 8 Update content and 1? */
    //just movie?
    @PutMapping()
    public ResponseEntity  updateContentById(@RequestBody Content content) {
        try {
            ResponseBase responseBase = new ResponseBase("Successfully updated data", Collections.singletonList(contentService.updateContent(content)));
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * 9 Delete content */
    @DeleteMapping()
    public ResponseEntity  deleteContentById(@RequestBody Content content) {
        try {
            contentService.deleteContent(content.getId());
            return new ResponseEntity<>(new ResponseBase("Successfully deleted data" , new ArrayList<>()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBase(e.getMessage(), new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
