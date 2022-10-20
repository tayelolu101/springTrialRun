package com.example.sprintbootrest.controller;

import com.example.sprintbootrest.entity.Department;
import com.example.sprintbootrest.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentAddress("AKURE")
                .departmentCode("GEO-001")
                .departmentName("GEOPHYSICS")
                .departmentId(1L)
                .build();
    }
    @Test
    void saveDepartment() throws Exception {
      Department  InputDepartment = Department.builder()
                .departmentAddress("AKURE")
                .departmentCode("GEO-001")
                .departmentName("GEOPHYSICS")
                .build();

        Mockito.when(departmentService.saveDepartment(InputDepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\" : \"GEOPHYSICS\",\n" +
                        "    \"departmentAddress\" : \"AKURE\",\n" +
                        "    \"departmentCode\" : \"GEO-001\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName")
                        .value(department.getDepartmentName()));
    }
}