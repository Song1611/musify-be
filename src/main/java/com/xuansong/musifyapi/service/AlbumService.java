package com.xuansong.musifyapi.service;

import com.xuansong.musifyapi.document.Album;
import com.xuansong.musifyapi.dto.request.AlbumRequest;
import com.xuansong.musifyapi.dto.response.AlbumListResponse;

import java.io.IOException;

public interface AlbumService {
    Album addAlbum(AlbumRequest albumRequest) throws IOException;
    AlbumListResponse getAllAlbums();
    Boolean removeAlbum(String id);
}
