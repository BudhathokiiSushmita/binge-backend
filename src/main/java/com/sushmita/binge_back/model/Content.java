package com.sushmita.binge_back.model;

import com.sushmita.binge_back.constants.ContentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Sushmita Budhathoki on 2024-07-15
 */

@Document(collection = "content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id
    private String id;
    private String title;
    private Float price;
    private String synopsis;
    private ContentEnum type;
    private String smallPoster;
    private String largePoster;
    private Float rentPrice;
    private Float purchasePrice;
    private Boolean isFeatured;
}
