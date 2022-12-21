package com.api.carownership.models;

import jakarta.persistence.*;

@Entity
@Table(name = "automovel")
public class AutomovelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String placa;
    public String veiculo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }
}
