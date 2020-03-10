package com.example.blog.model;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
public class Post {

    private String id;
    private String author;
    private String title;
    private String content;
    private Date date;

}
