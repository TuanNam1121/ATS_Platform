package com.platform.ats.services;

import com.platform.ats.dto.DepartmentDto;
import com.platform.ats.entities.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department createDepartment(DepartmentDto dept);

    Department findById(Long id);
}
