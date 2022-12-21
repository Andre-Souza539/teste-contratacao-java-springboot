package com.api.carownership.repositories;

import com.api.carownership.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

    boolean existsByCpf(String cpf); // Query para verificar se já existe cliente com o CPF
}
