package com.xuansong.musifyapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuansong.musifyapi.dto.request.SongRequest;
import com.xuansong.musifyapi.dto.response.SongListResponse;
import com.xuansong.musifyapi.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;


    @PostMapping
    public ResponseEntity<?> addSong(@RequestPart("request") String requestString,
                                     @RequestPart("audio") MultipartFile audioFile,
                                     @RequestPart("image") MultipartFile imageFile)  {

        try{
            ObjectMapper mapper = new ObjectMapper();
            SongRequest request = mapper.readValue(requestString,SongRequest.class);
            request.setAudioFile(audioFile);
            request.setImageFile(imageFile);
            return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(request));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSongs() {
        try{
            return ResponseEntity.ok(songService.getAllSongs());
        }catch(Exception e){
            return ResponseEntity.ok(new SongListResponse(false,null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSong(@PathVariable String id) {
        try{
          Boolean removed = songService.removeSong(id);
          if(removed){
              //.build() chỉ xây response rỗng (chỉ chứa header và status code, không có body).
              return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
          }
          else{
              return ResponseEntity.badRequest().build();
          }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
