package com.example.springbootjwtsecurity.Services;

import com.example.springbootjwtsecurity.Model.Customers;
import com.example.springbootjwtsecurity.Repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
@RequiredArgsConstructor
public class CustomersService {
@Autowired
private CustomerRepo cutomerrepo;
    private List<Customers> customerList = new CopyOnWriteArrayList<>();

    public Customers addcustomer (Customers customer ){

        return cutomerrepo.save(customer);

    }
    public List<Customers> getcustomerlist(){

        return  cutomerrepo.findAll();

    }
}
