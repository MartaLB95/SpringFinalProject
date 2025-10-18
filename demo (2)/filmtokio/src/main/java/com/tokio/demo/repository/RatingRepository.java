package com.tokio.demo.repository;

import com.tokio.demo.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository <Rating, Long> {

    //Calcular la media de los scores

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.film.id = :filmId")
    Double findAverageScoreByFilmId(@Param("filmId") Long filmId);
}
