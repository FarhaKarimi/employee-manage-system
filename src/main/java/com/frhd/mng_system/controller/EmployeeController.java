package com.frhd.mng_system.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.frhd.mng_system.dto.EmployeeDTO;
import com.frhd.mng_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/public")
    @JsonView(EmployeeDTO.Views.Public.class)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesPublic() {
        List<EmployeeDTO> employees = employeeService.getAllEmployeesPublic();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/detailed")
    @JsonView(EmployeeDTO.Views.Detailed.class)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesDetailed() {
        List<EmployeeDTO> employees = employeeService.getAllEmployeesDetailed();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/admin")
    @JsonView(EmployeeDTO.Views.Admin.class)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesAdmin() {
        List<EmployeeDTO> employees = employeeService.getAllEmployeesAdmin();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(EmployeeDTO.Views.Admin.class)
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
}