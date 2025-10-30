package com.xuansong.musifyapi.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongRequest {

    private String id;
    private String name;
    private String album;
    private String desc;
    private MultipartFile imageFile;
    private MultipartFile audioFile;
}
