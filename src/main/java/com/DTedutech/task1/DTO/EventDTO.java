package com.DTedutech.task1.DTO;

import com.DTedutech.task1.Entity.File;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventDTO {
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
}
