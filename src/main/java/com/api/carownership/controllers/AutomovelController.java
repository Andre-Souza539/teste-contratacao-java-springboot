package com.api.carownership.controllers;

import com.api.carownership.dtos.AutomovelDto;
import com.api.carownership.models.AutomovelModel;
import com.api.carownership.service.AutomovelService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/automovel")
public class AutomovelController {
    final AutomovelService automovelService;

    public AutomovelController(AutomovelService automovelService) {
        this.automovelService = automovelService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAutomovel(@RequestBody @Valid AutomovelDto automovelDto){
        if(automovelService.existsByPlaca(automovelDto.getPlaca())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: O VEÍCULO JÁ ESTÁ REGISTRADO");
        }
        var automovelModel = new AutomovelModel();
        BeanUtils.copyProperties(automovelDto,automovelModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(automovelService.save(automovelModel));
    }

    @GetMapping
    public ResponseEntity<List<AutomovelModel>> getAllAutomovel(){
        return ResponseEntity.status(HttpStatus.OK).body(automovelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAutomovel(@PathVariable(value = "id")Long id){
        Optional<AutomovelModel> automovelModelOptional = automovelService.findById(id);
        if(!automovelModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo Inexistente");
        }
        return ResponseEntity.status(HttpStatus.OK).body(automovelModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAutomovel(@PathVariable(value = "id") Long id){
        Optional<AutomovelModel> automovelModelOptional = automovelService.findById(id);
        if (!automovelModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo Inexistênte");
        }
        automovelService.delete(automovelModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Veículo Excluído com Sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAutomovel(@PathVariable(value = "id") Long id,
                                                  @RequestBody @Valid AutomovelDto automovelDto){
        Optional<AutomovelModel> automovelModelOptional = automovelService.findById(id);
        if(!automovelModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo Inexistente");
        }
        var automovelModel = automovelModelOptional.get();
        automovelModel.setPlaca(automovelDto.getPlaca());
        automovelModel.setVeiculo(automovelDto.getVeiculo());
        return ResponseEntity.status(HttpStatus.OK).body(automovelService.save(automovelModel));
    }
}
