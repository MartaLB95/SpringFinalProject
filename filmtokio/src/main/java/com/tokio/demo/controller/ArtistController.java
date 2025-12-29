package com.tokio.demo.controller;

import com.tokio.demo.domain.Actor;
import com.tokio.demo.domain.Artist;
import com.tokio.demo.domain.Director;
import com.tokio.demo.service.impl.ArtistServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    private final ArtistServiceImpl artistServiceImpl;

    public ArtistController(ArtistServiceImpl artistServiceImpl) {
        this.artistServiceImpl = artistServiceImpl;
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        logger.info("GET/create/ called");
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        model.addAttribute("types", List.of("Actor", "Director"));
        logger.info("Create artist form displayed");
        return "createArtist";
    }


    @PostMapping("/create")
    public String post(@RequestParam String type, @RequestParam String name, @RequestParam String lastname, @ModelAttribute Model model) {
        Artist artist;

        if ("Director".equals(type)) {
            artist = new Director();
        } else {
            artist = new Actor();
        }

        artist.setName(name);
        artist.setLastname(lastname);

        Artist createdArtist = artistServiceImpl.save(artist);

        logger.info("Artist created with ID: {} and name: {}", createdArtist.getId(), createdArtist.getName());
        return "redirect:/films";
    }

}
