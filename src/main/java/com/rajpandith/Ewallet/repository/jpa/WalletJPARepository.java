package com.rajpandith.Ewallet.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajpandith.Ewallet.enitity.WalletEntity;

@Repository
public interface WalletJPARepository extends JpaRepository<WalletEntity, Long> {

    Optional<WalletEntity> findByUser_Id(Long userId);

}
