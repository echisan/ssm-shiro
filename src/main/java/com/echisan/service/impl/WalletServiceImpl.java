package com.echisan.service.impl;

import com.echisan.dao.WalletMapper;
import com.echisan.dao.WalletRecordMapper;
import com.echisan.model.po.Wallet;
import com.echisan.model.po.WalletExample;
import com.echisan.model.po.WalletRecord;
import com.echisan.model.po.WalletRecordExample;
import com.echisan.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private WalletRecordMapper walletRecordMapper;

    @Override
    public void saveWallet(Wallet wallet) {

        walletMapper.insertSelective(wallet);
    }

    @Override
    public void updateWallet(Wallet wallet) {

        walletMapper.updateByPrimaryKeySelective(wallet);
    }

    @Override
    public void updateWalletBalanceByUserId(Long userId, BigDecimal balance) {

        WalletExample walletExample = new WalletExample();
        walletExample.createCriteria().andUserIdEqualTo(userId);
        List<Wallet> wallets = walletMapper.selectByExample(walletExample);

        if (wallets!=null && wallets.size()!=0){
            // 如果钱包存在
            Wallet wallet = wallets.get(0);
            wallet.setBalance(balance);
            walletMapper.updateByPrimaryKeySelective(wallet);
        }else {
            // 不存在

            Wallet wallet = new Wallet();
            wallet.setUserId(userId);
            wallet.setBalance(balance);
            saveWallet(wallet);
        }


    }


    @Override
    public void deleteWalletById(Long id) {

        walletMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteWalletByUserId(Long userId) {

        WalletExample walletExample = new WalletExample();
        walletExample.createCriteria().andUserIdEqualTo(userId);
        walletMapper.deleteByExample(walletExample);

    }

    @Override
    public Wallet getWalletById(Long id) {
        return walletMapper.selectByPrimaryKey(id);
    }

    @Override
    public Wallet getWalletByUserId(Long userId) {
        WalletExample walletExample = new WalletExample();
        walletExample.createCriteria().andUserIdEqualTo(userId);
        List<Wallet> wallets = walletMapper.selectByExample(walletExample);
        if (wallets!=null && wallets.size()!=0){
            return wallets.get(0);
        }else {
            Wallet wallet = new Wallet();
            wallet.setBalance(new BigDecimal(0));
            wallet.setUserId(userId);
            saveWallet(wallet);
            return wallet;
        }
    }

    @Override
    public List<Wallet> listWallet() {
        WalletExample example = new WalletExample();
        return walletMapper.selectByExample(example);
    }

    @Override
    public void saveWalletRecord(WalletRecord walletRecord) {

        walletRecordMapper.insertSelective(walletRecord);

    }

    @Override
    public void deleteWalletRecordById(Long id) {

        walletRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteWalletRecordByUserId(Long userId) {

        WalletRecordExample example = new WalletRecordExample();
        example.createCriteria().andUserIdEqualTo(userId);
        walletRecordMapper.deleteByExample(example);
    }

    @Override
    public void updateWalletRecord(WalletRecord walletRecord) {

        WalletRecordExample example = new WalletRecordExample();
        walletRecordMapper.updateByExampleSelective(walletRecord,example);
    }

    @Override
    public List<WalletRecord> listWalletRecordByUserId(Long userId) {

        WalletRecordExample example = new WalletRecordExample();
        example.setOrderByClause("create_time desc");
        example.createCriteria().andUserIdEqualTo(userId);
        return walletRecordMapper.selectByExample(example);
    }
}
