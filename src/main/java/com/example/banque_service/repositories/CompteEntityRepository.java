package com.example.banque_service.repositories;

import com.example.banque_service.entities.CompteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompteEntityRepository extends JpaRepository<CompteEntity, Long> {

    @Query("SELECT SUM(c.solde) FROM CompteEntity c")
    double calculerSommeSoldes();

    double sumSoldes();
}
