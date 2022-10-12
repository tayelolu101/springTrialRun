package com.example.sprintbootrest.service;

import com.example.sprintbootrest.entity.Department;
import com.example.sprintbootrest.error.DepartmentNotFoundException;
import com.example.sprintbootrest.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartments() {
        return  departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Found");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> deprt = departmentRepository.findById(departmentId);
        if(!deprt.isPresent()){
            throw new DepartmentNotFoundException("Department Not Found");
        }
            departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException {
        Optional<Department> deprt = departmentRepository.findById(departmentId);

        if(!deprt.isPresent()){
            throw new DepartmentNotFoundException("Department Not Found");
        }
        Department dept = deprt.get();
        if(department.getDepartmentName() != null && !department.getDepartmentName().isEmpty())
               dept.setDepartmentName(department.getDepartmentName());
        if(department.getDepartmentCode() != null && !department.getDepartmentCode().isEmpty())
            dept.setDepartmentCode(department.getDepartmentCode());
        if(department.getDepartmentAddress() != null && !department.getDepartmentAddress().isEmpty())
            dept.setDepartmentAddress(department.getDepartmentAddress());

        return departmentRepository.save(dept);
    }

    @Override
    public Department findByDepartmentName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }
}
