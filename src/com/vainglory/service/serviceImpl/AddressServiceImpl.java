package com.vainglory.service.serviceImpl;

import com.vainglory.dao.IAddressDao;
import com.vainglory.dao.daoImpl.DaoFactory;
import com.vainglory.domain.Address;
import com.vainglory.service.IAddressService;

import java.util.List;

public class AddressServiceImpl implements IAddressService {

    private IAddressDao addressDao = DaoFactory.getAddressDao();

    @Override
    public List<Address> getAddressList(Integer id) {
        return addressDao.getAddressByUid(id);
    }

    @Override
    public boolean addAddress(Address address) {
        address.setLevel(0);
        int result = addressDao.add(address);
        return result == 1;
    }

    @Override
    public boolean deleteAddress(Integer id) {
        int result = addressDao.deleteById(id);
        return result == 1;
    }

    @Override
    public boolean setDefault(Integer id, Integer uid) {
        //移除默认地址
        addressDao.removeDefault(uid);
        //设置新的默认地址
        int result = addressDao.setDefault(id);
        return result == 1;
    }

    @Override
    public boolean updateAddress(Address address) {
        int result = addressDao.update(address);
        return result != 0;
    }
}
