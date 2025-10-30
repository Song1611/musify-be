package com.xuansong.musifyapi.service.serviceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.xuansong.musifyapi.document.Song;
import com.xuansong.musifyapi.dto.request.SongRequest;
import com.xuansong.musifyapi.dto.response.SongListResponse;
import com.xuansong.musifyapi.repository.SongRepository;
import com.xuansong.musifyapi.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final Cloudinary cloudinary;

    @Override
    public Song addSong(SongRequest request) throws IOException {
        //System.out.println("Uploading audio...");
      Map<String,Object> audioUploadResult = cloudinary.uploader().upload(request.getAudioFile().getBytes(), ObjectUtils.asMap("resource_type","auto"));
        //System.out.println("Audio uploaded.");
        //System.out.println("Uploading image...");
      Map<String, Object> imageUploadResult = cloudinary.uploader().upload(request.getImageFile().getBytes(), ObjectUtils.asMap("resource_type","image"));
        //System.out.println("Image uploaded.");
      Double durationSeconds = (Double) audioUploadResult.get("duration");
      String duration = formatDuration(durationSeconds);

      Song newSong = Song.builder()
              .desc(request.getDesc())
              .album(request.getAlbum())
              .name(request.getName())
              .file(audioUploadResult.get("secure_url").toString())
              .image(imageUploadResult.get("secure_url").toString())
              .duration(duration).build();
      return songRepository.save(newSong);
    }

    public SongListResponse getAllSongs() {
        return new SongListResponse(true,songRepository.findAll());
    }

    public Boolean removeSong(String id){
        Song existingSong = songRepository.findById(id).orElseThrow(()->new RuntimeException("Song not found"));
        songRepository.delete(existingSong);
        return true;
    }

    private String formatDuration(Double durationSeconds) {
        if(durationSeconds == null){
            return "0:00";
        }

        int minutes = (int)(durationSeconds/60);
        int seconds = (int)(durationSeconds%60);

        return String.format("%d:%02d", minutes,seconds);

    }
}
