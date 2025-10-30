package com.xuansong.musifyapi.repository;

import com.xuansong.musifyapi.document.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {
}
