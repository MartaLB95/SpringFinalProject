package com.tokio.demo.service.impl;

import com.tokio.demo.domain.Director;
import com.tokio.demo.repository.DirectorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Lazy(true)
@Service
public class DirectorServiceImpl implements DirectorService {

    private static final Logger logger = LoggerFactory.getLogger(DirectorServiceImpl.class);

    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Director> findAll(){
        logger.info("Service: fetching all directors");
        return directorRepository.findAll();
    }

    @Override
    public Optional<Director> findById(Long id){
        logger.info("Service: fetching director with id {}", id);
        return directorRepository.findById(id);
    }

    @Override
    public Director save(Director director) {
        logger.info("New director created with id");
        return directorRepository.save(director);
    }

}
