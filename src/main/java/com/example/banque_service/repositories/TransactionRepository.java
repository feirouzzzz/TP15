package com.example.banque_service.repositories;

import com.example.banque_service.entities.Transaction;
import com.example.banque_service.entities.TypeTransaction;
import com.example.banque_service.entities.CompteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Récupérer toutes les transactions liées à un compte spécifique
    List<Transaction> findAllByAccount(CompteEntity account);

    // Obtenir la somme des transactions selon leur type
    @Query("SELECT COALESCE(SUM(bt.amount), 0) FROM BankTransaction bt WHERE bt.transactionType = :transactionType")
    double getTotalAmountByType(@Param("transactionType") TypeTransaction transactionType);
}
