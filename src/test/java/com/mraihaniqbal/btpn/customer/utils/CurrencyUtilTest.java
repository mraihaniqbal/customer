package com.mraihaniqbal.btpn.customer.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyUtilTest {

    @Test
    public void indonesianCurrency(){

        String actual = CurrencyUtil.indonesiaFormat(5000000);
        String expected = "Rp 5.000.000,00";

        assertEquals(expected,actual);

    }

}