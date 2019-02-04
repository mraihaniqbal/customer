package com.mraihaniqbal.btpn.customer.repository;

import com.mraihaniqbal.btpn.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, String> {

    @Transactional
    @Query("from Customer where name like %?1%")
    List<Customer> searchByName(String name);

    @Transactional
    @Query(value = "SELECT * FROM nasabah ORDER BY pendapatan DESC LIMIT 3", nativeQuery = true)
    List<Customer> mostThreeSalary();

}
