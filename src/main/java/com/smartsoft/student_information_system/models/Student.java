/*
 * Copyright (c) 2019. SmartSoft
 */

package com.smartsoft.student_information_system.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    Long studentId;

    @Size(min = 3, max = 50)
    String lastName;

    @Size(min = 3, max = 50)
    String firstName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "class_model_student",
    joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "class_model_id",
    referencedColumnName = "id"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "class_model_id"}))
    List<ClassModel> classModels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<ClassModel> getClassModels() {
        return classModels;
    }

    public void setClassModels(List<ClassModel> classModels) {
        this.classModels = classModels;
    }
}
