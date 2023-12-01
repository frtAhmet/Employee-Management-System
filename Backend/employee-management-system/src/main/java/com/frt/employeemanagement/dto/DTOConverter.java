package com.frt.employeemanagement.dto;

import com.frt.employeemanagement.entity.Employee;

public class DTOConverter {
    public static EmployeeDto convertEmployeeToEmployeeDTO(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee convertEmloyeeDTOToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
