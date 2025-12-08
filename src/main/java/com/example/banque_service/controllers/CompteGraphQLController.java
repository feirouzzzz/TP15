package com.example.banque_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.banque_service.entities.CompteEntity;
import com.example.banque_service.repositories.CompteEntityRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CompteGraphQLController {

    private final CompteEntityRepository compteRepo;

    @QueryMapping
    public List<CompteEntity> getComptes() {
        return compteRepo.findAll();
    }

    @QueryMapping
    public CompteEntity getCompte(@Argument Long id) {
        return compteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucun compte trouvé avec l’ID : " + id));
    }

    @MutationMapping
    public CompteEntity createCompte(@Argument CompteEntity compte) {
        return compteRepo.save(compte);
    }

    @QueryMapping
    public Map<String, Object> statistiquesSoldes() {
        long totalComptes = compteRepo.count();
        double soldeTotal = compteRepo.sumSoldes();
        double soldeMoyen = totalComptes != 0 ? soldeTotal / totalComptes : 0;

        Map<String, Object> stats = new HashMap<>();
        stats.put("nombreComptes", totalComptes);
        stats.put("sommeSoldes", soldeTotal);
        stats.put("soldeMoyen", soldeMoyen);
        return stats;
    }
}
