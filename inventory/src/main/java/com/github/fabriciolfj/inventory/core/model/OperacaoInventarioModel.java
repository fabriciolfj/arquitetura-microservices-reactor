package com.github.fabriciolfj.inventory.core.model;

import lombok.Data;
@Data
public class OperacaoInventarioModel {

    private String code;
    private Integer qtdeEntrada;
    private Integer qtdeSaida;
}
