package com.rajpandith.Ewallet.enitity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonSerialize
@JsonDeserialize

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "wallet_table")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount = 0.0;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @OneToMany(mappedBy = "senderWallet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionEntity> sentTransactions;

    @OneToMany(mappedBy = "receiverWallet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionEntity> receivedTransactions;

}
