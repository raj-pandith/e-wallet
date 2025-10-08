package com.rajpandith.Ewallet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckBalanceDto {
    private String username;
    private Double balance;
}
