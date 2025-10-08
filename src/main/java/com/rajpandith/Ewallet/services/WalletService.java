package com.rajpandith.Ewallet.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rajpandith.Ewallet.dto.AddMoneyDto;
import com.rajpandith.Ewallet.dto.CheckBalanceDto;
import com.rajpandith.Ewallet.dto.RequestAddUserWithWallet;
import com.rajpandith.Ewallet.enitity.UserEntity;
import com.rajpandith.Ewallet.enitity.WalletEntity;
import com.rajpandith.Ewallet.repository.jpa.UserJPARepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final UserJPARepository userJPARepository;
    private final com.rajpandith.Ewallet.repository.jpa.WalletJPARepository walletJPARepository;

    public WalletEntity initializeWallet(RequestAddUserWithWallet requestUserWithWallet) throws Exception {
        Optional<UserEntity> userEntity = userJPARepository.findById(requestUserWithWallet.getUserId());
        if (!userEntity.isPresent()) {
            throw new Exception("User Not Found !");
        }
        if (userEntity.get().getWalletEntity() != null) {
            throw new Exception("e-wallet already Linked");
        }
        WalletEntity newWalletEntity = new WalletEntity();
        newWalletEntity.setAmount(requestUserWithWallet.getInitialAmount());
        newWalletEntity.setUser(userEntity.get());
        return walletJPARepository.save(newWalletEntity);

    }

    public CheckBalanceDto checkBalance(Long userid) throws Exception {
        Optional<UserEntity> user = userJPARepository.findById(userid);
        if (!user.isPresent()) {
            throw new Exception("user not found !..");
        }

        CheckBalanceDto checkBalanceDto = new CheckBalanceDto();
        checkBalanceDto.setBalance(user.get().getWalletEntity().getAmount());
        checkBalanceDto.setUsername(user.get().getUsername());
        return checkBalanceDto;

    }

    @Transactional
    public boolean addMoney(AddMoneyDto addMoneyDto) throws Exception {
        Optional<UserEntity> user = userJPARepository.findById(addMoneyDto.getUserId());
        if (!user.isPresent()) {
            throw new Exception("user not found !..");
        }

        Double initmoney = user.get().getWalletEntity().getAmount();
        user.get().getWalletEntity().setAmount(initmoney + addMoneyDto.getInserMoney());
        return true;
    }

}
