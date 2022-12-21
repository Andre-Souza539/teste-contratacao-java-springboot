package com.api.carownership.controllers;

import com.api.carownership.dtos.PessoaDto;
import com.api.carownership.models.PessoaModel;
import com.api.carownership.service.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Object> savePessoa(@RequestBody @Valid PessoaDto pessoaDto){
        if(pessoaService.existsByCpf(pessoaDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: CPF PRECISA SER ÚNICO");
        }
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel); // Converte o DTO recebido em Model
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
    }

    @GetMapping
    public ResponseEntity<List<PessoaModel>> getAllPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value = "id") Long id) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if (!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable (value = "id") Long id){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro Inexistente");
        }
        pessoaService.delete(pessoaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Registro Excluído com Sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePessoa(@PathVariable(value = "id")Long id,
                                               @RequestBody @Valid PessoaDto pessoaDto){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro Inexistente");
        }
        var pessoaModel = pessoaModelOptional.get();
        pessoaModel.setCpf(pessoaDto.getCpf());
        pessoaModel.setNome(pessoaDto.getNome());
        pessoaModel.setEstado(pessoaDto.getEstado());

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaModel));
    }



}
