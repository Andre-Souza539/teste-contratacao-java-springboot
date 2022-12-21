package com.api.carownership.repositories;

import com.api.carownership.models.AutomovelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomovelRepository extends JpaRepository<AutomovelModel, Long> {
    boolean existsByPlaca(String placa);
}
