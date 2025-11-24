package com.tokio.demo;

import com.tokio.demo.domain.Film;
import com.tokio.demo.domain.Rating;
import com.tokio.demo.domain.User;
import com.tokio.demo.exception.RatingNotFoundException;
import com.tokio.demo.repository.RatingRepository;
import com.tokio.demo.service.impl.RatingServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingServiceImpl;

    private Rating rating;
    private Rating rating2;
    private List<Rating> ratings;
    private User user;
    private User user2;
    private Film film;

    @BeforeEach
    void setup() {

        user = new User(1L, "alice123", "password", "password", "alice@example.com", "Alice", "Wonderland",
                LocalDate.of(1995, 5, 10), new HashSet<>(), new HashSet<>());

        user2 = new User(2L, "maria123", "password", "password", "maria@example.com", "María", "Sarmiento",
                LocalDate.of(1992, 5, 8), new HashSet<>(), new HashSet<>());

        film = new Film(1L,
                "Inception",
                2010,
                Collections.emptySet(), // actores vacíos
                null,                   // sin director
                null,                   // sin póster
                Collections.emptySet());

        rating = new Rating(1L, LocalDateTime.of(2024, 1, 10, 12, 30), 5, film, user);

        rating2 = new Rating(2L, LocalDateTime.of(2025, 4, 13, 18, 30), 4, film, user);

        ratings = List.of(rating, rating2);
    }

    @DisplayName("Given a list of ratings" +
            "When we call findAll()" +
            "We expect that all the ratings are shown")
    @Test
    public void givenDifferentFilmsFindAll()
    {

        Mockito.when(ratingRepository.findAll()).thenReturn(ratings);
        final List<Rating> result = ratingServiceImpl.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(5, result.get(0).getScore());
        Assertions.assertEquals(4, result.get(1).getScore());
        Assertions.assertEquals("Inception", result.get(0).getFilm().getTitle());
        Assertions.assertEquals("Inception", result.get(1).getFilm().getTitle());
        Mockito.verify(ratingRepository).findAll();

    }

    @DisplayName("Given an id" +
            "When we call findById()" +
            "We expect that the rating with the given id is found")

    @Test public void givenIdFindRating()
    {
        Mockito.when(ratingRepository.findById(1L)).thenReturn(Optional.ofNullable(rating));
        Mockito.when(ratingRepository.findById(8L)).thenReturn(Optional.empty());

        final Optional<Rating> result = ratingServiceImpl.findById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(5, result.get().getScore());
        Assertions.assertThrows(RatingNotFoundException.class,() -> {
            ratingServiceImpl.findById(8L);
        });
        Mockito.verify(ratingRepository).findById(1L);

    }

    @DisplayName("Given a rating that we want to create" +
            "When we call save()" +
            "We expect that the rating is created")
    @Test
    public void givenFilmToCreateExpectedCreatedRating()
    {

        Mockito.when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
        final Rating result = ratingServiceImpl.save(rating);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(5, result.getScore());
        Assertions.assertEquals(1L, result.getId());
        Mockito.verify(ratingRepository).save(rating);

    }

    @DisplayName("Given a rating that we want to delete" +
            "When we call delete()" +
            "We expect that the rating is deleted")
    @Test
    public void givenDeleteExpectedDeletedRating()
    {
        Mockito.when(ratingRepository.existsById(1L)).thenReturn(true);

        ratingServiceImpl.delete(1L);

        Mockito.verify(ratingRepository).existsById(1L);
        Mockito.verify(ratingRepository).deleteById(1L);

    }

    @DisplayName("Given a list of ratings" +
            "When we call findAverageScoreByFilmId()" +
            "We expect that the average of the ratings is shown")
    @Test
    public void givenIdFindAverageRating()
    {
        List <Rating> ratings=List.of(rating, rating2);

        Mockito.when(ratingRepository.findAverageScoreByFilmId(1L)).thenReturn(4.5);
        final double result = ratingServiceImpl.findAverageScoreByFilmId(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(4.5, result);
        Mockito.verify(ratingRepository).findAverageScoreByFilmId(1L);

    }









}
