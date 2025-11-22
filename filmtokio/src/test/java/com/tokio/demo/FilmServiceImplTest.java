package com.tokio.demo;

import com.tokio.demo.domain.Film;
import com.tokio.demo.repository.FilmRepository;
import com.tokio.demo.service.impl.FilmServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class FilmServiceImplTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmServiceImpl filmServiceImpl;

    private Film film;
    private Film film2;
    private List<Film> films;

    @BeforeEach
   void setup() {
         film = new Film(1L,
                "Inception",
                2010,
                Collections.emptySet(), // actores vacíos
                null,                   // sin director
                null,                   // sin póster
                Collections.emptySet());

         film2 = new Film(2L,
                "Harry Potter",
                2002,
                Collections.emptySet(), // actores vacíos
                null,                   // sin director
                null,                   // sin póster
                Collections.emptySet());

         films = List.of(film, film2);
    }

    @DisplayName("Given a list of films" +
            "When we call findAll()" +
            "We expect that all the films are shown")
    @Test
    public void givenDifferentFilmsFindAll()
    {

        Mockito.when(filmRepository.findAll()).thenReturn(films);
        final List<Film> result = filmServiceImpl.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Inception", result.get(0).getTitle());
        Assertions.assertEquals("Harry Potter", result.get(1).getTitle());
        Assertions.assertEquals(2010, result.get(0).getReleaseDate());
        Assertions.assertEquals(2002, result.get(1).getReleaseDate());
        Mockito.verify(filmRepository).findAll();

    }

    @DisplayName("Given an id" +
            "When we call findById()" +
            "We expect that the film with the given id is found")
    @Test
    public void givenIdFindFilm()
    {

        Mockito.when(filmRepository.findById(1L)).thenReturn(Optional.ofNullable(film));
        final Film result = filmServiceImpl.findById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Inception", result.getTitle());
        Assertions.assertEquals(2010, result.getReleaseDate());
        Assertions.assertThrows(EntityNotFoundException.class,() -> {
        filmServiceImpl.findById(8L);
    });
        Mockito.verify(filmRepository).findById(1L);

    }

    @DisplayName("Given a film that we want to create" +
            "When we call save()" +
            "We expect that the film is created")
    @Test
    public void givenFilmToCreateExpectedCreatedFilm()
    {

        Mockito.when(filmRepository.save(any(Film.class))).thenReturn(film);
        final Film result = filmServiceImpl.save(film);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Inception", result.getTitle());
        Assertions.assertEquals(1L, result.getId());
        Mockito.verify(filmRepository).save(film);

    }

    @DisplayName("Given a text" +
            "When we call findByTitleContaining()" +
            "We see all the films containing this text in their title")
    @Test
    public void givenTextSearchFilmsByTitle()
    {
        List<Film> expected = List.of(film2);

        Mockito.when(filmRepository.findByTitleContaining("ott")).thenReturn(expected);
        final List <Film> result = filmServiceImpl.findByTitleContaining("ott");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, result);
        Mockito.verify(filmRepository).findByTitleContaining("ott");
        Assertions.assertEquals("Harry Potter", result.get(0).getTitle());

    }





}
