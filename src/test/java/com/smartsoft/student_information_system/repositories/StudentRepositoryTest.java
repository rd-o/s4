/*
 * Copyright (c) 2019. SmartSoft
 */

package com.smartsoft.student_information_system.repositories;

import com.smartsoft.student_information_system.models.ClassModel;
import com.smartsoft.student_information_system.models.Student;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    private Student student;
    private ClassModel classModel;

    private final String CLASS_CODE = "ENG100";
    private final String FIRST_NAME = "Maria";
    private final String LAST_NAME = "Connor";
    private final Long STUDENT_ID = 7777L;
    private final int LARGE_NAME_SIZE= 51;


    @Before
    public void setUp() {
        classModel = new ClassModel();
        classModel.setCode(CLASS_CODE);

        student = new Student();
        student.setFirstName(FIRST_NAME);
        student.setLastName(LAST_NAME);
        student.setStudentId(STUDENT_ID);
        List<ClassModel> classModels = new ArrayList<>();
        classModels.add(classModel);
        student.setClassModels(classModels);
        studentRepository.save(student);
    }

    @Test
    public void whenFindByFirstName_thenReturnStudents() {

        List<Student> studentList  = studentRepository.findByFirstName(student.getFirstName());

        assert(studentList.size() == 1);
        assert(studentList.stream()
                .findFirst()
                .map(Student::getFirstName)
                .get() == student.getFirstName());
    }

    @Test
    public void whenFindByLastName_thenReturnStudents() {
        List<Student> studentList  = studentRepository.findByLastName(student.getLastName());

        assert(studentList.size() == 1);
        assert(studentList.stream()
                .findFirst()
                .map(Student::getLastName)
                .get() == student.getLastName());
    }

    @Test
    public void whenFindByStudentId_thenReturnStudents() {

        List<Student> studentList  = studentRepository.findByStudentId(student.getStudentId());

        assert(studentList.size() == 1);
        assert(studentList.stream()
                .findFirst()
                .map(Student::getStudentId)
                .get() == student.getStudentId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldCheckUniqueStudentId(){
        student = new Student();
        student.setStudentId(STUDENT_ID);

        studentRepository.save(student);
    }


    @Test(expected = ConstraintViolationException.class)
    public void shouldCheckFirstNameMaxSize(){
        student = new Student();
        student.setFirstName(RandomStringUtils.random(LARGE_NAME_SIZE));

        studentRepository.save(student);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldCheckLastNameMaxSize(){
        student = new Student();
        student.setLastName(RandomStringUtils.random(LARGE_NAME_SIZE));

        studentRepository.save(student);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldCheckFirstNameMinSize(){
        student = new Student();
        student.setFirstName(RandomStringUtils.random(1));

        studentRepository.save(student);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldCheckLastNameMinSize(){
        student = new Student();
        student.setLastName(RandomStringUtils.random(1));

        studentRepository.save(student);
    }
}
