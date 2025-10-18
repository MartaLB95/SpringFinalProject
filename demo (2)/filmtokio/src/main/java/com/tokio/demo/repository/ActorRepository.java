package com.tokio.demo.repository;

import com.tokio.demo.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository <Actor, Long> {
}
