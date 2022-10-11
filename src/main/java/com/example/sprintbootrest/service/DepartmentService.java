package com.example.sprintbootrest.service;

import com.example.sprintbootrest.entity.Department;
import com.example.sprintbootrest.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchDepartments();

    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException;

    public Department fetchDepartmentByName(String name);
}
