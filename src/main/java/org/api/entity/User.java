package org.api.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    private String password;
    private String userID;
    @JsonProperty("userName")
    @JsonAlias("username")
    private String username;
    private ArrayList<Object> books;
}
