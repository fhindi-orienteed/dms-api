package com.dms.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.base.model.Address;
import com.dms.base.repository.AddressRepository;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address findByUserIdAndType(long userId, String type) {
        List<Address> addresses = addressRepository.findAllByUserIdAndType(userId, type);
        if (addresses == null || addresses.isEmpty()) {
            return null;
        }
        return addresses.get(0); 
    }
}
