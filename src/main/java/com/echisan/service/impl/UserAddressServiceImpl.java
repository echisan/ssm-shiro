package com.echisan.service.impl;

import com.echisan.dao.UserAddressMapper;
import com.echisan.model.po.UserAddress;
import com.echisan.model.po.UserAddressExample;
import com.echisan.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author E73AN
 */
@Service
@Transactional
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;


    @Override
    public void saveUserAddress(UserAddress userAddress) {
        userAddressMapper.insertSelective(userAddress);
    }

    @Override
    public void deleteUserAddressById(Long addressId) {
        userAddressMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    public void updateUserAddress(UserAddress userAddress) {
        userAddressMapper.updateByPrimaryKey(userAddress);
    }


    @Override
    public List<UserAddress> listUserAddressesById(Long userId) {
        UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserAddress> userAddresses = userAddressMapper.selectByExample(userAddressExample);

        if (userAddresses!=null && userAddresses.size()!=0){
            return userAddresses;
        }

        return null;
    }

    @Override
    public UserAddress getUserDefaultAddressById(Long userId) {
        UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andIsDefaultAddressEqualTo((byte) 1);
        List<UserAddress> userAddresses = userAddressMapper.selectByExample(userAddressExample);
        if (userAddresses!=null && userAddresses.size()!=0) {
            return userAddresses.get(0);
        }

        return listUserAddressesById(userId).get(0);
    }
}
