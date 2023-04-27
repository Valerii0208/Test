package com.example.botscrewtesttask.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
@Data
@Table(name = "departments", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Department {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @ManyToOne
        private Lector head;

        @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
        private List<Lector> lectors = new ArrayList<>();
}
