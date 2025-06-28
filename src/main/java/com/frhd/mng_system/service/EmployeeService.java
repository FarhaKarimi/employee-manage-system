package com.frhd.mng_system.service;


import com.frhd.mng_system.dto.EmployeeDTO;
import com.frhd.mng_system.model.Employee;
import com.frhd.mng_system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.firstName());
        employee.setLastName(employeeDTO.lastName());
        employee.setEmail(employeeDTO.email());
        employee.setDepartment(employeeDTO.department());
        employee.setSalary(employeeDTO.salary());
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartment(),
                savedEmployee.getSalary()
        );
    }

    // Read all (Public view)
    public List<EmployeeDTO> getAllEmployeesPublic() {
        return employeeRepository.findAll().stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        null, // email hidden in public view
                        null, // department hidden in public view
                        null  // salary hidden in public view
                ))
                .collect(Collectors.toList());
    }

    // Read all (Detailed view)
    public List<EmployeeDTO> getAllEmployeesDetailed() {
        return employeeRepository.findAll().stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getDepartment(),
                        null  // salary hidden in detailed view
                ))
                .collect(Collectors.toList());
    }

    // Read all (Admin view)
    public List<EmployeeDTO> getAllEmployeesAdmin() {
        return employeeRepository.findAll().stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getDepartment(),
                        employee.getSalary()
                ))
                .collect(Collectors.toList());
    }

    // Read by ID
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            return new EmployeeDTO(
                    emp.getId(),
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getEmail(),
                    emp.getDepartment(),
                    emp.getSalary()
            );
        }
        throw new RuntimeException("Employee not found with id: " + id);
    }

    // Update
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setFirstName(employeeDTO.firstName());
            employee.setLastName(employeeDTO.lastName());
            employee.setEmail(employeeDTO.email());
            employee.setDepartment(employeeDTO.department());
            employee.setSalary(employeeDTO.salary());
            Employee updatedEmployee = employeeRepository.save(employee);
            return new EmployeeDTO(
                    updatedEmployee.getId(),
                    updatedEmployee.getFirstName(),
                    updatedEmployee.getLastName(),
                    updatedEmployee.getEmail(),
                    updatedEmployee.getDepartment(),
                    updatedEmployee.getSalary()
            );
        }
        throw new RuntimeException("Employee not found with id: " + id);
    }

    // Delete
    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }
}