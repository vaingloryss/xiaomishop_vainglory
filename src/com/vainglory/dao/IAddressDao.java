package com.vainglory.dao;

import com.vainglory.domain.Address;

import java.util.List;

public interface IAddressDao {
    List<Address> getAddressByUid(Integer uid);
    int add(Address address);

    int deleteById(int id);
    int removeDefault(int uid);
    int setDefault(int id);

    int update(Address address);

    List<Address> findByUid(Integer uid);

    Address findById(Integer aid);
}
