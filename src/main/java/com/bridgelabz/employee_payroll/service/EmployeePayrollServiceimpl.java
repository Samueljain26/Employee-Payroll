package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.exception.EmployeePayrollException;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeePayrollServiceimpl implements IEmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployeePayrollData() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeePayrollDataById(int empId) {
        return employeeRepository.findById(empId).get();
    }

    public Employee createEmployeePayrollData(EmployeeDTO empPayrollDTO) {
        try {
            Employee employee = new Employee(empPayrollDTO);
            return employeeRepository.save(employee);
        }
        catch(Exception e){
            throw new EmployeePayrollException(e.getMessage());
        }
    }

    public Employee updateEmployeePayrollData(int empId, EmployeeDTO empPayrollDTO) {
       try {
           Employee empData = this.getEmployeePayrollDataById(empId);
           empData.setName(empPayrollDTO.getName());
           empData.setSalary(empPayrollDTO.getSalary());
           return employeeRepository.save(empData);
       }
       catch(Exception e){
           throw new EmployeePayrollException(e.getMessage());
       }
    }

    public String deleteEmployeePayrollData(int empId) {
        try {
            employeeRepository.deleteById(empId);
            return "Succesfully deleted";
        } catch (Exception e) {
            throw new EmployeePayrollException(e.getMessage());
        }
    }
}
