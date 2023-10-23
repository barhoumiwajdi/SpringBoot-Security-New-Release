package com.example.springbootjwtsecurity.Controller;

import com.example.springbootjwtsecurity.Model.Customers;
import com.example.springbootjwtsecurity.Services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

@Autowired
private CustomersService customerService ;

/*
    private static final List<Customers> CUSTOMERS = List.of(
            new Customers(1L, "john", "doe", "john@javawhizz.com"),
            new Customers(2L, "mary", "public", "mary@javawhizz.com"),
            new Customers(3L, "elon", "musk","elon@javawhizz.com"),
            new Customers(4L, "dunny","duncan","dunny@javawhizz.com")
    );
*/




    @PostMapping
    public Customers addCustomer (@RequestBody Customers customer ){
        return customerService.addcustomer(customer);
    }
    @GetMapping
    public List<Customers> getcustomers(){
        return customerService.getcustomerlist();
    }

    @GetMapping("/{ID}")
    public Customers getcustomer(@PathVariable("ID") int ID){
        System.out.println("hello");
        return customerService.getCustomer(ID);
    }

}
