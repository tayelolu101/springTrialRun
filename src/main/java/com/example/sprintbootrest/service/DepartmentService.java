package com.example.sprintbootrest.service;

import com.example.sprintbootrest.entity.Department;
import com.example.sprintbootrest.error.DepartmentNotFoundException;


import java.util.List;


public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> fetchDepartments();

    Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException;

    Department findByDepartmentName(String name);
}
