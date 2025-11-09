package com.tokio.demo.controller;

import com.tokio.demo.domain.Artist;
import com.tokio.demo.service.impl.ArtistServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistServiceImpl artistServiceImpl;

    public ArtistController(ArtistServiceImpl artistServiceImpl) {
        this.artistServiceImpl = artistServiceImpl;
    }


    @GetMapping("/create")
    public String showCreateForm(Model model){
        Artist artist= new Artist();
        model.addAttribute("artist", artist);
        return "createArtist";
    }


    @PostMapping("/create")
    public String post(@ModelAttribute Artist artist, Model model){
        Artist createdArtist=artistServiceImpl.save(artist);
        model.addAttribute("artist", createdArtist);
        return "redirect:/films";
    }

}
