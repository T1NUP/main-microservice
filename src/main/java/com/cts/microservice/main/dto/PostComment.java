package com.cts.microservice.main.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {

    private long id;
    private String username;
    private String description;
    private Date targetDate;

//    public PostComment() {
//        // Default consturctor
//    }
//
//    public PostComment(String username, String description, Date targetDate) {
//        this.username = username;
//        this.description = description;
//        this.targetDate = targetDate;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public Date getTargetDate() {
//        return targetDate;
//    }

    @Override
    public String toString() {
        return String.format("%s: %s\n", username, description);
    }
}
