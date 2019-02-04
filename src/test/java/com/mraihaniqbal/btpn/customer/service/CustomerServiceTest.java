package com.mraihaniqbal.btpn.customer.service;

import com.mraihaniqbal.btpn.customer.entity.Customer;
import com.mraihaniqbal.btpn.customer.enums.Gender;
import com.mraihaniqbal.btpn.customer.repository.CustomerDao;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerDao customerDao;

    private Customer customer;

    @Before
    public void setUp() throws ParseException {
        customer = new Customer();
        customer.setId("CUST-001");
        customer.setName("Test");
        customer.setGender(Gender.MALE);
        customer.setSalary(new BigDecimal(5000000));
        customer.setBirthdate(null);
    }

    @Test
    public void add_ExistId() {
        when(customerDao.existsById(customer.getId())).thenReturn(true);

        Map<String,String> expected = new HashMap<>();
        expected.put("message",customer.getId()+" is already exist. Please use other id");
        Map<String,String> actual = customerService.add(customer);

        assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void add_NotExistId() {
        when(customerDao.existsById(customer.getId())).thenReturn(false);

        Map<String,String> expected = new HashMap<>();
        expected.put("message","New customer successfully added");
        Map<String,String> actual = customerService.add(customer);

        assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void add_Exception(){
        when(customerDao.existsById(customer.getId())).thenReturn(false);
        when(customerDao.save(customer)).thenThrow(new DataAccessException("test") {});

        Map<String,String> expected = new HashMap<>();
        expected.put("message","Adding new customer failed. Please notify your administrator.");
        Map<String,String> actual = customerService.add(customer);

        assertThat(actual, Matchers.is(expected));
    }

    @Test
    public void update_ExistId() {
        when(customerDao.existsById(customer.getId())).thenReturn(true);

        Map<String,String> expected = new HashMap<>();
        expected.put("message","Customer successfully updated");
        Map<String,String> actual = customerService.update(customer);

        assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void update_NotExistId() {
        when(customerDao.existsById(customer.getId())).thenReturn(false);

        Map<String,String> expected = new HashMap<>();
        expected.put("message","ID not found.");
        Map<String,String> actual = customerService.update(customer);

        assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void update_Exception(){
        when(customerDao.existsById(customer.getId())).thenReturn(true);
        when(customerDao.save(customer)).thenThrow(new DataAccessException("test") {});

        Map<String,String> expected = new HashMap<>();
        expected.put("message","Update customer failed. Please notify your administrator.");
        Map<String,String> actual = customerService.update(customer);

        assertThat(actual, Matchers.is(expected));
    }

    @Test
    public void delete_Exception(){
        doThrow(new DataAccessException("test") {}).when(customerDao).deleteById("CUST-002");

        Map<String,String> expected = new HashMap<>();
        expected.put("message","Delete customer failed. Please notify your administrator.");
        Map<String,String> actual = customerService.delete("CUST-002");

        assertThat(actual, Matchers.is(expected));
    }
}