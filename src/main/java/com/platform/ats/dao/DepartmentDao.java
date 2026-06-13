package com.platform.ats.dao;

import com.platform.ats.entities.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> findAll();

    Department createDepartment(Department dept);

    Department findById(Long id);

    boolean isExisted(String name);

}
