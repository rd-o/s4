package com.example.s4.repositories;

import com.example.s4.models.ClassModel;
import com.example.s4.models.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void whenFindByStudentId_thenReturnStudents() {

        List<Student> studentList  = studentRepository.findByStudentId(student.getStudentId());

        assert(studentList.size() == 1);
        assert(studentList.stream()
                .findFirst()
                .map(Student::getStudentId)
                .get() == student.getStudentId());
    }


}
