package com.DTedutech.task1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Event {
    @Id
    private Integer id;
    private String name;
    private String tagline;
    private LocalDateTime schedule;
    private String description;

    //private  image file
    private String moderator;
    private String category;
    private String subCategory;
    private int rigorRank;
    private List<String> attendees;



}