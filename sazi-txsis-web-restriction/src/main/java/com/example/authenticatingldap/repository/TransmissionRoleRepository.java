package com.example.authenticatingldap.repository;

import com.example.authenticatingldap.model.TransmissionRoles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransmissionRoleRepository extends JpaRepository<TransmissionRoles, Long> {
    String role = "Admin";
//    @Query("SELECT p FROM TransmissionRoles p WHERE " +
//            "p.adAccount = :adUniqueNumber And p.role LIKE CONCAT('%', :role, '%')" )
    @Transactional
    TransmissionRoles findByAdUniqueNumber(String adUniqueNumber);

}
