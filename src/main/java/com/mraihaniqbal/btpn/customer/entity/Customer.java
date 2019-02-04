package com.mraihaniqbal.btpn.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mraihaniqbal.btpn.customer.enums.Gender;
import com.mraihaniqbal.btpn.customer.utils.CurrencyUtil;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "nasabah")
public class Customer {

    @Id
    @Column(name = "no_nasabah", length = 30)
    private String id;

    @Column(name = "nama_nasabah")
    private String name;

    @Column(name = "tgl_lahir", columnDefinition = "DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthdate;

    @Column(name = "pendapatan")
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "jenis_kelamin")
    private Gender gender;

    @Transient
    private String formattedSalary;

    @PostLoad
    public void populateFormattedSalary(){
        formattedSalary = CurrencyUtil.indonesiaFormat(salary);
    }

    public Customer() {
    }

    public Customer(String id, String name, Date birthdate, BigDecimal salary, Gender gender) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.salary = salary;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
