package com.example.sprintbootrest.service;

import com.example.sprintbootrest.entity.Department;
import com.example.sprintbootrest.error.DepartmentNotFoundException;
import com.example.sprintbootrest.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .departmentName("MECH_ENG")
                        .departmentAddress("ABUJA")
                        .departmentCode("MECH001")
                        .departmentId(1L)
                        .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("MECH_ENG"))
                .thenReturn(department);

        Mockito.when(departmentRepository.findById(1L))
                .thenReturn(java.util.Optional.ofNullable(department));
    }

    @Test
    @DisplayName("Get date based on Department Name")
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "MECH_ENG";
        Department found = departmentService.findByDepartmentNameIgnoreCase(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }

    @Test
    @DisplayName("Get date based on Department Id")
    public void whenValidDepartmentId_thenDepartmentShouldFound() throws DepartmentNotFoundException {
        Long departmentId = 1L;
        Department found = departmentService.fetchDepartmentById(departmentId);

        assertEquals(departmentId, found.getDepartmentId());
    }
}