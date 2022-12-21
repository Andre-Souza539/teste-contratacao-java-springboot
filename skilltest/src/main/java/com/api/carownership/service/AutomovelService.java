package com.api.carownership.service;

import com.api.carownership.models.AutomovelModel;
import com.api.carownership.repositories.AutomovelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutomovelService {
    final AutomovelRepository automovelRepository;

    public AutomovelService(AutomovelRepository automovelRepository) {
        this.automovelRepository = automovelRepository;
    }
    @Transactional
    public AutomovelModel save(AutomovelModel automovelModel) {
        return automovelRepository.save(automovelModel);

    }

    public boolean existsByPlaca(String placa) {
        return automovelRepository.existsByPlaca(placa);
    }

    public List<AutomovelModel> findAll() {
        return automovelRepository.findAll();
    }

    public Optional<AutomovelModel> findById(Long id) {
        return automovelRepository.findById(id);
    }

    @Transactional
    public void delete(AutomovelModel automovelModel) {
        automovelRepository.delete(automovelModel);
    }


}
