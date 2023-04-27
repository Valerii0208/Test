package com.example.botscrewtesttask.repository;

import com.example.botscrewtesttask.entity.Department;
import com.example.botscrewtesttask.entity.dto.DepartmentStatisticsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
    @Modifying
    @Transactional
    @Query("UPDATE Department d SET d.head.id = :lectorId WHERE d.id = :deptId")
    void updateHead(@Param("deptId") Long deptId, @Param("lectorId") Long lectorId);

    @Query("SELECT COUNT(l) FROM Lector l JOIN l.departments d WHERE d.name = :departmentName")
    Long countLectorsByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT AVG(l.salary) FROM Lector l JOIN l.departments d WHERE d.name = :departmentName")
    int findAverageSalaryByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT " +
            "NEW com.example.botscrewtesttask.entity.dto.DepartmentStatisticsDto(" +
            "SUM(CASE WHEN l.degree = 'ASSISTANT' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN l.degree = 'ASSOCIATE_PROFESSOR' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN l.degree = 'PROFESSOR' THEN 1 ELSE 0 END)) " +
            "FROM Lector l " +
            "JOIN l.departments d " +
            "WHERE d.name = :departmentName")
    DepartmentStatisticsDto getStatisticsForDepartment(@Param("departmentName") String departmentName);

    @Query("SELECT CONCAT(l.firstName, ' ', l.lastName) FROM Department d JOIN Lector l ON d.head.id = l.id WHERE d.name = :departmentName")
    String findHeadOfDepartmentName(@Param("departmentName") String departmentName);
}
