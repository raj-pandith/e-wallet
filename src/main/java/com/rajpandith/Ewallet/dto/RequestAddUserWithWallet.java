package com.rajpandith.Ewallet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAddUserWithWallet {
    private Long userId;
    private double initialAmount;
}
