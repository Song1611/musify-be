package com.xuansong.musifyapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuansong.musifyapi.dto.request.AlbumRequest;
import com.xuansong.musifyapi.dto.response.AlbumListResponse;
import com.xuansong.musifyapi.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<?> addAlbum(@RequestPart("request") String request,
                                      @RequestPart("file") MultipartFile file) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            AlbumRequest albumRequest = mapper.readValue(request,AlbumRequest.class);
            albumRequest.setImageFile(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(albumService.addAlbum(albumRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping
    public ResponseEntity<?> getAllAlbums() {
        try{
            return ResponseEntity.ok(albumService.getAllAlbums());
        }catch(Exception e){
            return ResponseEntity.ok(new AlbumListResponse(false,null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAlbum(@PathVariable("id") String id) {
        try{
            Boolean removed = albumService.removeAlbum(id);
            if(removed){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
