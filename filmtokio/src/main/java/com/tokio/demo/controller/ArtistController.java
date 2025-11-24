package com.tokio.demo.controller;

import com.tokio.demo.domain.Artist;
import com.tokio.demo.service.impl.ArtistServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    private final ArtistServiceImpl artistServiceImpl;

    public ArtistController(ArtistServiceImpl artistServiceImpl) {
        this.artistServiceImpl = artistServiceImpl;
    }


    @GetMapping("/create")
    public String showCreateForm(Model model){
        logger.info("GET/create/ called");
        Artist artist= new Artist();
        model.addAttribute("artist", artist);
        logger.info("Create artist form displayed");
        return "createArtist";
    }


    @PostMapping("/create")
    public String post(@ModelAttribute Artist artist, Model model){
        Artist createdArtist=artistServiceImpl.save(artist);
        model.addAttribute("artist", createdArtist);
        logger.info("Artist created with ID: {} and name: {}", createdArtist.getId(), createdArtist.getName());
        return "redirect:/films";
    }

}
