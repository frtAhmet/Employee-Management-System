package com.frt.employeemanagement.service.impl;

import com.frt.employeemanagement.dto.DTOConverter;
import com.frt.employeemanagement.dto.EmployeeDto;
import com.frt.employeemanagement.entity.Employee;
import com.frt.employeemanagement.exception.ResourceNotFoundException;
import com.frt.employeemanagement.repository.EmployeeRepository;
import com.frt.employeemanagement.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = DTOConverter.convertEmloyeeDTOToEmployee(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);

        return DTOConverter.convertEmployeeToEmployeeDTO(saveEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee isn't exists with given id : " + employeeId)
        );

        return DTOConverter.convertEmployeeToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee) -> DTOConverter.convertEmployeeToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee isn't exists with given id : " + employeeId)
        );

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee updateEmployee = employeeRepository.save(employee);

        return DTOConverter.convertEmployeeToEmployeeDTO(updateEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee isn't exists with given id : " + employeeId)
        );

        employeeRepository.deleteById(employeeId);

    }
}
