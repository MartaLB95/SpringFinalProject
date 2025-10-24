package com.tokio.demo.controller;
import com.tokio.demo.domain.*;
import com.tokio.demo.service.impl.ActorServiceImpl;
import com.tokio.demo.service.impl.DirectorServiceImpl;
import com.tokio.demo.service.impl.FilmServiceImpl;
import com.tokio.demo.service.impl.RatingServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/films")

public class FilmController {
    private final FilmServiceImpl filmServiceImpl;
    private final DirectorServiceImpl directorServiceImpl;
    private final ActorServiceImpl actorServiceImpl;
    private final RatingServiceImpl ratingServiceImpl;

    public FilmController(FilmServiceImpl filmServiceImpl, DirectorServiceImpl directorServiceImpl, ActorServiceImpl actorServiceImpl, RatingServiceImpl ratingServiceImpl) {
        this.filmServiceImpl = filmServiceImpl;
        this.directorServiceImpl = directorServiceImpl;
        this.actorServiceImpl = actorServiceImpl;
        this.ratingServiceImpl = ratingServiceImpl;
    }

    @GetMapping
    public String getAll(String titleFragment, Model model){
        if(titleFragment != null && !titleFragment.isEmpty() ){
             model.addAttribute("films", filmServiceImpl.findByTitleContaining(titleFragment));
        }else {
            model.addAttribute("films", filmServiceImpl.findAll());
        }
        model.addAttribute("searchQuery", titleFragment);
        return "searchFilm";
    }

    @GetMapping ("/details/{id}")
    public String getById(@PathVariable Long id, Model model){
        /**Hay que indicar que Film es un optional y pasarle a thymeleaf algo distinto, ya que no puede
         * leer optionals. Además, si no pasamos "film" al model, thymeleaf no lo encontrará.
         */
        Optional<Film> optionalFilm=filmServiceImpl.findById(id);
        Film film= optionalFilm.orElseThrow(()->new RuntimeException("Film not found"));
        model.addAttribute("film", film);
        model.addAttribute("averageRating", ratingServiceImpl.findAverageScoreByFilmId(id));
        return "detailsView";
    }

    @GetMapping ("/create")
    public String showCreateForm(Model model){
        Film film= new Film();
        film.setDirector(new Director());
        model.addAttribute("film", film);

        List<Director> directors = directorServiceImpl.findAll();
        if (directors == null) directors = new ArrayList<>();
        model.addAttribute("directors", directors);

        List <Actor> actors = actorServiceImpl.findAll();
        if (actors == null) actors = new ArrayList<>();
        model.addAttribute("actors", actors);

        return "createFilm";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Optional<Film> optionalFilm=filmServiceImpl.findById(id);
        Film film= optionalFilm.orElseThrow(()->new RuntimeException("Film not found"));
        model.addAttribute("film", film);
        //Para que se muestren todos los actores y todos los directores disponibles
        model.addAttribute("directors", directorServiceImpl.findAll());
        model.addAttribute("actors", actorServiceImpl.findAll());
        return "editFilm";

    }

    @GetMapping ("/rate/{id}")
    public String showRateForm(@PathVariable Long id, Model model) {
        Optional<Film> optionalFilm = filmServiceImpl.findById(id);
        Film film = optionalFilm.orElseThrow(() -> new RuntimeException("Film not found"));
        Rating rating=new Rating();
        model.addAttribute("film", film);
        model.addAttribute("rating", rating);
        return "rateFilm";
    }


    @PostMapping ("/create")
    public String post(@ModelAttribute Film film, @RequestParam Long directorId, Model model,
                       @RequestParam("posterFile") MultipartFile poster) throws IOException {

        // Recuperar el director DB
        Director director = directorServiceImpl.findById(directorId)
                .orElseThrow(() -> new RuntimeException("Director not found"));

        // Reemplazar el "transient" por el director real
        film.setDirector(director);

        //Añadir atributo
        model.addAttribute("film", film);

        //Añadir imagen para poster
        if (poster != null && !poster.isEmpty()) {
            // Validar tipo de archivo
            if (!poster.getContentType().startsWith("image/")) {
                model.addAttribute("errorMsg", "Solo se permiten imágenes");
                return "redirect:/films/create";
            }

            String fileName= UUID.randomUUID() + "-" + poster.getOriginalFilename();
            fileName = Paths.get(fileName).getFileName().toString();
            Path uploadImage = Paths.get("uploads/posters");
            Files.createDirectories(uploadImage);
            poster.transferTo(uploadImage.resolve(fileName));
            film.setPoster(fileName);
    }


        //Guardar película
        filmServiceImpl.save(film);

        //Devolver vista (volver a la lista de películas)
            return "redirect:/films";
    }

    @PostMapping ("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Film film, @RequestParam Long directorId, Model model){
        Director director = directorServiceImpl.findById(directorId)
                .orElseThrow(() -> new RuntimeException("Director not found"));

        film.setDirector(director);
        //Nos aseguramos de que el form se envía con el mismo id
        film.setId(id);
        //Guardar película editada
        filmServiceImpl.save(film);
        //Devolver vista (volver a la lista de películas)
        return "redirect:/films";
    }

    @PostMapping ("/rate/{id}")
    public String rate(@PathVariable Long id, @ModelAttribute Rating rating){

        //Nos aseguramos de que el form se envía con el mismo id. Convierto el optional en Film para que thymeleaf
        //pueda procesarlo
        Optional<Film> optionalFilm = filmServiceImpl.findById(id);
        Film film = optionalFilm.orElseThrow(() -> new RuntimeException("Film not found"));


        //Guardar rating de la película
        rating.setFilm(film);

        //Ponemos id null para que se haga un insert y no un update
        rating.setId(null);

        //rating.setUserId(getUserId()); Esto lo tendré que incluir cuando tenga la parte de user!!!
        rating.setCreatedAt(LocalDate.now().atStartOfDay());
        
        User dummyUser = new User();
        dummyUser.setId(1L); // suponiendo que el id 1 exista o se puede crear uno temporal
        rating.setUser(dummyUser);

        ratingServiceImpl.save(rating);


        //Devolver vista (volver a la lista de películas)
        return "redirect:/films";
    }




}
