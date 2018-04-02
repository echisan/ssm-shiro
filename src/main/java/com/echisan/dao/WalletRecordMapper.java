package com.echisan.dao;

import com.echisan.model.po.WalletRecord;
import com.echisan.model.po.WalletRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalletRecordMapper {
    long countByExample(WalletRecordExample example);

    int deleteByExample(WalletRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WalletRecord record);

    int insertSelective(WalletRecord record);

    List<WalletRecord> selectByExample(WalletRecordExample example);

    WalletRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WalletRecord record, @Param("example") WalletRecordExample example);

    int updateByExample(@Param("record") WalletRecord record, @Param("example") WalletRecordExample example);

    int updateByPrimaryKeySelective(WalletRecord record);

    int updateByPrimaryKey(WalletRecord record);
}