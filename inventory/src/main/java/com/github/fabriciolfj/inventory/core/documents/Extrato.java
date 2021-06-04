package com.github.fabriciolfj.inventory.core.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "extrato")
public class Extrato {

    @Id
    private String id;
    private String code;
    private Integer saldo;
    private Integer entrada;
    private Integer saida;
    private LocalDateTime mov;

    public static Extrato add(final Integer entrada, final Extrato extrato) {
        return Extrato.builder()
                .code(extrato.getCode())
                .entrada(entrada)
                .saida(0)
                .mov(LocalDateTime.now())
                .saldo(extrato.getSaldo() + entrada)
                .build();
    }

    public static Extrato exit(final Integer saida, final Extrato extrato) {
        return Extrato.builder()
                .code(extrato.getCode())
                .saida(saida)
                .entrada(0)
                .mov(LocalDateTime.now())
                .saldo(extrato.getSaldo() - saida)
                .build();
    }
}
