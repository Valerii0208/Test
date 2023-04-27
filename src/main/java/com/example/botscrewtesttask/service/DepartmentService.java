package com.example.botscrewtesttask.service;

import com.example.botscrewtesttask.entity.Department;
import com.example.botscrewtesttask.entity.Lector;
import com.example.botscrewtesttask.entity.dto.DepartmentStatisticsDto;

public interface DepartmentService {
    void saveDepartment(Department department);

    void updateDepartmentHead(Long id, Long headId);

    Long countLectorsInDepartment(String departmentName);

    int countAveragesSalaryInDepartment(String departmentName);

    DepartmentStatisticsDto getDepartmentStatistic(String departmentName);

    String getHeadOfDepartment(String departmentName);
}
