package com.example.botscrewtesttask.service.serviceImpl;

import com.example.botscrewtesttask.entity.Department;
import com.example.botscrewtesttask.entity.dto.DepartmentStatisticsDto;
import com.example.botscrewtesttask.repository.DepartmentRepository;
import com.example.botscrewtesttask.repository.LectorRepository;
import com.example.botscrewtesttask.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public void saveDepartment(Department department) {
        if (!departmentRepository.existsByName(department.getName())) {
            departmentRepository.save(department);
        } else {
            System.out.println("Department with name " + department.getName() + " already exists in the database!");
        }
    }

    @Override
    public void updateDepartmentHead(Long departmentId, Long headId) {
        departmentRepository.updateHead(departmentId, headId);
    }

    @Override
    public Long countLectorsInDepartment(String departmentName) {
        return departmentRepository.countLectorsByDepartmentName(departmentName);
    }

    @Override
    public int countAveragesSalaryInDepartment(String departmentName) {
        return departmentRepository.findAverageSalaryByDepartmentName(departmentName);
    }

    @Override
    public DepartmentStatisticsDto getDepartmentStatistic(String departmentName) {
        return departmentRepository.getStatisticsForDepartment(departmentName);
    }

    @Override
    public String getHeadOfDepartment(String departmentName) {
        return departmentRepository.findHeadOfDepartmentName(departmentName);
    }
}
