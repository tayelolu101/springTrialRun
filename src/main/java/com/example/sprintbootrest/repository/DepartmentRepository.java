package com.example.sprintbootrest.repository;

import com.example.sprintbootrest.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
     Department findByDepartmentName(String name);
}
