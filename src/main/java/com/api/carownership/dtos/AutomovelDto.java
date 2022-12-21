package com.api.carownership.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AutomovelDto {

    @NotBlank
    @Size(max = 7)
    private String placa;
    @NotBlank
    private String veiculo;

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
