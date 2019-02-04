package com.mraihaniqbal.btpn.customer.rest;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CustomerRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllCustomerList() throws JSONException {
        String response =restTemplate.getForObject("/customer/list", String.class);
        JSONAssert.assertEquals("[{id:BTPN000001},{id:BTPN000002},{id:MITRAIS000001},{id:MITRAIS000002},{id:MITRAIS000003},{id:BTPN000003}]", response, false);
    }

    @Test
    public void getMostThreeSalary() throws JSONException {
        String response =restTemplate.getForObject("/customer/most-three-salary", String.class);

        //Use (strict = true) because the order have to descended by salary (pendapatan)
        JSONAssert.assertEquals("[" +
        "{\"id\":\"MITRAIS000002\",\"name\":\"Soni Jatmoko\",\"birthdate\":\"10-10-1998\",\"salary\":10000000.00,\"gender\":\"MALE\"}," +
        "{\"id\":\"BTPN000002\",\"name\":\"Nurhayati\",\"birthdate\":\"12-02-1991\",\"salary\":7000000.00,\"gender\":\"FEMALE\"}," +
        "{\"id\":\"BTPN000001\",\"name\":\"Raihan Iqbal\",\"birthdate\":\"17-04-1992\",\"salary\":5000000.00,\"gender\":\"MALE\"}" +
        "]", response, true);
    }

    @Test
    public void searchName_useThreeChar() throws JSONException {
        String response =restTemplate.getForObject("/customer/search-name/ra", String.class);
        JSONAssert.assertEquals("[{id:BTPN000001},{id:BTPN000003}]", response, false);
    }
}