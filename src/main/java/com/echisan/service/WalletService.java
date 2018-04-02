package com.echisan.service;

import com.echisan.model.po.Wallet;
import com.echisan.model.po.WalletRecord;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author E73AN
 */
public interface WalletService {

    Byte PAY = 0;
    Byte INCOME = 1;

    void saveWallet(Wallet wallet);

    void updateWallet(Wallet wallet);

    void updateWalletBalanceByUserId(Long userId,BigDecimal balance);

    void deleteWalletById(Long id);

    void deleteWalletByUserId(Long userId);

    Wallet getWalletById(Long id);

    Wallet getWalletByUserId(Long userId);

    List<Wallet> listWallet();

    void saveWalletRecord(WalletRecord walletRecord);

    void deleteWalletRecordById(Long id);

    void deleteWalletRecordByUserId(Long userId);

    void updateWalletRecord(WalletRecord walletRecord);

    List<WalletRecord> listWalletRecordByUserId(Long userId);

}
