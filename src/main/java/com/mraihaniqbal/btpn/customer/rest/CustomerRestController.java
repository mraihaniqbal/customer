package com.mraihaniqbal.btpn.customer.rest;

import com.mraihaniqbal.btpn.customer.entity.Customer;
import com.mraihaniqbal.btpn.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerRestController {

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/list")
    public List<Customer> list(){
        return customerService.findAll();
    }

    @GetMapping("/customer/search-name/{name}")
    public List<Customer> getByName(@PathVariable String name){
        return customerService.findByName(name);
    }

    @GetMapping("/customer/search-id/{id}")
    public Customer getById(@PathVariable String id){
        return customerService.findById(id);
    }

    @GetMapping("/customer/most-three-salary")
    public List<Customer> threeMostSalary(){
        return customerService.findMostThreeSalary();
    }

    @PostMapping("/customer/add")
    public Map<String,String> add(@RequestBody Customer newCustomer){
        return customerService.add(newCustomer);
    }

    @PutMapping("/customer/update")
    public Map<String,String> update(@RequestBody Customer customer){
        return customerService.update(customer);
    }

    @DeleteMapping("/customer/delete/{id}")
    public Map<String,String> delete(@PathVariable String id){
        return customerService.delete(id);
    }

}
