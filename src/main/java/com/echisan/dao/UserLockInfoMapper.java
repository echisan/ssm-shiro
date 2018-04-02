package com.echisan.dao;

import com.echisan.model.po.UserLockInfo;
import com.echisan.model.po.UserLockInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLockInfoMapper {
    long countByExample(UserLockInfoExample example);

    int deleteByExample(UserLockInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLockInfo record);

    int insertSelective(UserLockInfo record);

    List<UserLockInfo> selectByExample(UserLockInfoExample example);

    UserLockInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLockInfo record, @Param("example") UserLockInfoExample example);

    int updateByExample(@Param("record") UserLockInfo record, @Param("example") UserLockInfoExample example);

    int updateByPrimaryKeySelective(UserLockInfo record);

    int updateByPrimaryKey(UserLockInfo record);
}