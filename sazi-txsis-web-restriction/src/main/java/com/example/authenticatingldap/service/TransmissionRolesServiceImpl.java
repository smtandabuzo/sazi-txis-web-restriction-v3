package com.example.authenticatingldap.service;

import com.example.authenticatingldap.dto.TransmissionRegistrationDto;
import com.example.authenticatingldap.model.TransmissionRoles;
import com.example.authenticatingldap.repository.TransmissionRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransmissionRolesServiceImpl implements TransmissionRolesService{

    @Autowired
    private TransmissionRoleRepository transmissionRoleRepository;

    public TransmissionRolesServiceImpl(TransmissionRoleRepository transmissionRoleRepository) {
        this.transmissionRoleRepository = transmissionRoleRepository;
    }

    @Override
    public TransmissionRoles findByAdUniqueNumber(Long adUniqueNumber) {
        System.out.println("Inside by ad unique number");
        TransmissionRoles transmissionRoles = transmissionRoleRepository.findByAdUniqueNumber(adUniqueNumber);
        if(transmissionRoles == null ){
            System.out.println("Transmission roles not found");
//            throw new UsernameNotFoundException("Invalid username or password.");
            return null;
        }

        return transmissionRoles;
    }

    @Override
    public void saveUser(TransmissionRegistrationDto transmissionRegistrationDto) {

        TransmissionRoles transmissionRoles = new TransmissionRoles();

        transmissionRoles.setAdAccount(transmissionRegistrationDto.getAdAccount());
//        transmissionRoles.setAdUniqueNumber(Long.valueOf(transmissionRegistrationDto.getAdUniqueNumber()));
        transmissionRoles.setAdFirstName(transmissionRegistrationDto.getAdFirstName());
        transmissionRoles.setAdSurname(transmissionRegistrationDto.getAdSurname());
        transmissionRoles.setAdEmailAddress(transmissionRegistrationDto.getAdEmailAddress());
        transmissionRoles.setTxSisRole(transmissionRegistrationDto.getTxsisRole());
        transmissionRoles.setStatus(transmissionRegistrationDto.getStatus());
        transmissionRoles.setRole(transmissionRegistrationDto.getRole());
        transmissionRoles.setBlocked("0");
                //transmissionRegistrationDto.getTxsisRole(),transmissionRegistrationDto.getStatus(),transmissionRegistrationDto.getRole(),transmissionRegistrationDto.getBlock());

       transmissionRoleRepository.save(transmissionRoles);
//        return "success";
    }

    @Override
    public Optional<TransmissionRoles> findByIdNum(Long id) {
        return transmissionRoleRepository.findById(id);
    }

    @Override
    public void blockUser(String blocked) {
//        if(transmissionRole.)
    }
}
