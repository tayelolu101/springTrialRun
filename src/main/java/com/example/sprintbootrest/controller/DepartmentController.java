package com.example.sprintbootrest.controller;

import com.example.sprintbootrest.entity.Department;
import com.example.sprintbootrest.error.DepartmentNotFoundException;
import com.example.sprintbootrest.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        logger.info("");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartments(){
        return  departmentService.fetchDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return  departmentService.fetchDepartmentById(departmentId);
    }

    @GetMapping("/departments/name/{name}")
    public Department findByDepartmentName(@PathVariable("name") String name){
        return  departmentService.findByDepartmentName(name);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
         departmentService.deleteDepartmentById(departmentId);
         return "Department with ID " + departmentId + " successfully deleted!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department) throws DepartmentNotFoundException {
        return departmentService.updateDepartment(departmentId, department);
    }
}
