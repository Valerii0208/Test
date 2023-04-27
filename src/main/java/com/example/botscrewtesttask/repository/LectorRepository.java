package com.example.botscrewtesttask.repository;

import com.example.botscrewtesttask.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findByLastNameContainingOrFirstNameContainingIgnoreCase(String lastName, String firstName);
}
