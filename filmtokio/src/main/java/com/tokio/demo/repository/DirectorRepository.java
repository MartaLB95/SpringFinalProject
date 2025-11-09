package com.tokio.demo.repository;

import com.tokio.demo.domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository <Director, Long> {
}
