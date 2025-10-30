package com.xuansong.musifyapi.service.serviceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.xuansong.musifyapi.document.Album;
import com.xuansong.musifyapi.dto.request.AlbumRequest;
import com.xuansong.musifyapi.dto.response.AlbumListResponse;
import com.xuansong.musifyapi.repository.AlbumRepository;
import com.xuansong.musifyapi.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final Cloudinary cloudinary;

    @Override
    public Album addAlbum(AlbumRequest albumRequest) throws IOException {
       Map<String,Object> imageUploadResult =  cloudinary.uploader().upload(
               albumRequest.getImageFile().getBytes(),
               ObjectUtils.asMap("resource_type","image"));
       Album newAlbum = Album.builder()
               .name(albumRequest.getName())
               .desc(albumRequest.getDesc())
               .bgColour(albumRequest.getBgColour())
               .imageUrl(imageUploadResult.get("secure_url").toString())
               .build();
       return albumRepository.save(newAlbum);
    }

    public AlbumListResponse getAllAlbums() {
        return new AlbumListResponse(true,albumRepository.findAll());
    }

    public Boolean removeAlbum(String id) {
       Album existingAlbum =  albumRepository.findById(id).orElseThrow(()->new RuntimeException("Album not found"));
        albumRepository.delete(existingAlbum);
        return true;
    }
}
