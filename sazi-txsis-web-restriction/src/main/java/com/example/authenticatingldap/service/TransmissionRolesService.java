package com.example.authenticatingldap.service;

import com.example.authenticatingldap.dto.TransmissionRegistrationDto;
import com.example.authenticatingldap.model.TransmissionRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;


@Service
public interface TransmissionRolesService {
    TransmissionRoles findByAdUniqueNumber(Long adUniqueNumber);


    void saveUser(TransmissionRegistrationDto transmissionRegistrationDto);

    Optional<TransmissionRoles> findByIdNum(Long id);
    @Query("update TransmissionRoles u set u.blocked = ?1")
    void blockUser(String blocked);
}
