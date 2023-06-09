package com.DTedutech.task1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Event {
    @Id
    private Integer id;
    private String type;
    private String name;
    private String tagline;
    private LocalDateTime schedule;
    private String description;

    private File file;
    private String moderator;
    private String category;
    private String subCategory;
    private int rigorRank;
    private List<String> attendees;



}