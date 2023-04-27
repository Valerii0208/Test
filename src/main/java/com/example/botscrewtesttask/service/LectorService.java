package com.example.botscrewtesttask.service;

import com.example.botscrewtesttask.entity.Department;
import com.example.botscrewtesttask.entity.Lector;
import com.example.botscrewtesttask.entity.enm.Degree;

import java.util.List;

public interface LectorService {
    void saveLector(Lector lector);

    void addLectorToDepartment(Long lectorId, Long departmentId);

    List<String> globalSearchByTemplate(String template);
}
