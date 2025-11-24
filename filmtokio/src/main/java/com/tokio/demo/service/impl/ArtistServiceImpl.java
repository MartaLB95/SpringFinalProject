package com.tokio.demo.service.impl;

import com.tokio.demo.controller.RatingRestController;
import com.tokio.demo.domain.Artist;
import com.tokio.demo.repository.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Lazy(true)
@Service
public class ArtistServiceImpl implements ArtistService {

    private static final Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Artist> findAll(){
        return artistRepository.findAll();
    }

    @Override
    public Optional <Artist> findById(Long id){
        return artistRepository.findById(id);
    }

    @Override
    public Artist save(Artist artist){
        return artistRepository.save(artist);
    }

}
