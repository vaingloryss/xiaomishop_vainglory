package com.vainglory.service;

import com.vainglory.domain.Address;

import java.util.List;

public interface IAddressService {
    List<Address> getAddressList(Integer id);
    boolean addAddress(Address address);

    boolean deleteAddress(Integer id);
    boolean setDefault(Integer id,Integer uid);

    boolean updateAddress(Address address);
}
