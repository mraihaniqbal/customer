package com.mraihaniqbal.btpn.customer.service;

import com.mraihaniqbal.btpn.customer.entity.Customer;
import com.mraihaniqbal.btpn.customer.repository.CustomerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> findByName(String name){
        return customerDao.searchByName(name);
    }

    public List<Customer> findAll(){
        return customerDao.findAll();
    }

    public Customer findById(String id){
        return customerDao.getOne(id);
    }

    public List<Customer> findMostThreeSalary(){
        return customerDao.mostThreeSalary();
    }

    public Map<String,String> add(Customer customer){
        Map<String,String> result = new HashMap<>();

        try{
            if(customerDao.existsById(customer.getId())){
                result.put("message",customer.getId()+" is already exist. Please use other id");
            }else{
                customerDao.save(customer);
                result.put("message","New customer successfully added");
            }
        }catch (DataAccessException e){
            result.put("message","Adding new customer failed. Please notify your administrator.");
            logger.error("error", e);
        }

        return result;
    }

    public Map<String,String> update(Customer customer){
        Map<String,String> result = new HashMap<>();

        try{
            if(!customerDao.existsById(customer.getId())){
                result.put("message","ID not found.");
            }else{
                customerDao.save(customer);
                result.put("message", "Customer successfully updated");
            }
        }catch (DataAccessException e){
            result.put("message","Update customer failed. Please notify your administrator.");
            logger.error("error", e);
        }

        return result;
    }

    public Map<String,String> delete(String id){
        Map<String,String> result = new HashMap<>();

        try{
            customerDao.deleteById(id);
            result.put("message","Customer successfully deleted");
        }catch (DataAccessException e){
            result.put("message","Delete customer failed. Please notify your administrator.");
            logger.error("error", e);
        }

        return result;

    }
}
