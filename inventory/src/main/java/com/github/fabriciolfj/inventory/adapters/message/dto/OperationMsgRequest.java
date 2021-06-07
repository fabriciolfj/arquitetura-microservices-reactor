package com.github.fabriciolfj.inventory.adapters.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationMsgRequest {

    private String code;
    private Integer qtde;
    private String type;
}
