package com.roy.springbootcache.controller;

import com.roy.springbootcache.entity.Employee;
import com.roy.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){

        return employeeService.getEmp(id);
    }

    @GetMapping("/emp")
    public Employee updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return employee;
    }

    @GetMapping("/clear")
    public String clearCache(){
        employeeService.clearCache();
        return "Success";
    }

@GetMapping("/test")
    public String setCache(){
        employeeService.test();
        return "Success";
    }
}
