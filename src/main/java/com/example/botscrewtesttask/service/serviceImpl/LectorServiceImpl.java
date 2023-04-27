package com.example.botscrewtesttask.service.serviceImpl;

import com.example.botscrewtesttask.entity.Department;
import com.example.botscrewtesttask.entity.Lector;
import com.example.botscrewtesttask.repository.DepartmentRepository;
import com.example.botscrewtesttask.repository.LectorRepository;
import com.example.botscrewtesttask.service.LectorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LectorServiceImpl implements LectorService {
    private final LectorRepository lectorRepository;
    private final DepartmentRepository departmentRepository;
    @Override
    public void saveLector(Lector lector) {
        lector.setFirstName(lector.getFirstName());
        lector.setLastName(lector.getLastName());
        lector.setDegree(lector.getDegree());
        lector.setSalary(lector.getSalary());
        lectorRepository.save(lector);
    }

    @Override
    public void addLectorToDepartment(Long lectorId, Long departmentId) {
        Lector lector = lectorRepository.findById(lectorId).orElseThrow(() ->
                new EntityNotFoundException("Lector not found"));
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new EntityNotFoundException("Department not found"));
        List<Department> departments = lector.getDepartments();
        if (!departments.contains(department)) {
            departments.add(department);
            department.getLectors().add(lector);
            lectorRepository.save(lector);
            departmentRepository.save(department);
        }
    }

    @Override
    public List<String> globalSearchByTemplate(String template) {
        List<Lector> lectors = lectorRepository.
                findByLastNameContainingOrFirstNameContainingIgnoreCase(template, template);
        List<String> results = new ArrayList<>();
        for (Lector lector : lectors) {
            results.add(lector.getFirstName() + " " + lector.getLastName());
        }
        return results;
    }
}
