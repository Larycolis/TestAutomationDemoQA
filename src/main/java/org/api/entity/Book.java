package org.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    @JsonProperty("publish_date")
    private Date publishDate;
    private String publisher;
    private int pages;
    private String description;
    private String website;
}
