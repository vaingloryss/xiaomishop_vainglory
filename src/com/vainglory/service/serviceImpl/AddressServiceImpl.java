package com.vainglory.service.serviceImpl;

import com.sun.org.apache.regexp.internal.RE;
import com.vainglory.dao.daoImpl.AddressDaoImpl;
import com.vainglory.domain.Address;
import com.vainglory.dao.IAddressDao;
import com.vainglory.service.IAddressService;

import java.util.List;

public class AddressServiceImpl implements IAddressService {
    IAddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Address> getAddressList(Integer id) {
        return addressDao.getAddressByUid(id);
    }

    @Override
    public boolean addAddress(Address address) {
        address.setLevel(0);
        int result = addressDao.add(address);
        if (result==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAddress(Integer id) {
        int result = addressDao.deleteById(id);
        if (result==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean setDefault(Integer id, Integer uid) {
        //移除默认地址
        addressDao.removeDefault(uid);
        //设置新的默认地址
        int result = addressDao.setDefault(id);
        if (result==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAddress(Address address) {
        int result = addressDao.update(address);
        if (result==0){
            return false;
        }
        return true;
    }
}
