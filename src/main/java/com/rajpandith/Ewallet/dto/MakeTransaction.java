package com.rajpandith.Ewallet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakeTransaction {

    private long senderid;
    private Long receiverid;
    private Double transferAmount;
    private String message;
}
