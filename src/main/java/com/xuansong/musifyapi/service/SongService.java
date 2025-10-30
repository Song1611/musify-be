package com.xuansong.musifyapi.service;

import com.xuansong.musifyapi.document.Song;
import com.xuansong.musifyapi.dto.request.SongRequest;
import com.xuansong.musifyapi.dto.response.SongListResponse;

import java.io.IOException;

public interface SongService {
    Song addSong(SongRequest songRequest) throws IOException;
    Boolean removeSong(String id);
    SongListResponse getAllSongs();
}
