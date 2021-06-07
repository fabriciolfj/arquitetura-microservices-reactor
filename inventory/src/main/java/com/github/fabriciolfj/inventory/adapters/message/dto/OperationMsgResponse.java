package com.github.fabriciolfj.inventory.adapters.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationMsgResponse {

    private String code;
    private Integer balance;
}
