package com.xuansong.musifyapi.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Songs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Song {

    @Id
    @JsonProperty("_id")
    private String id;
    private String name;
    private String album;
    private String desc;
    private String image;
    private String file;
    private String duration;
}
