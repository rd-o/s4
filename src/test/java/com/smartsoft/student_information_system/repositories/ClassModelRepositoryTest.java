/*
 * Copyright (c) 2019. SmartSoft
 */

package com.smartsoft.student_information_system.repositories;

import com.smartsoft.student_information_system.models.ClassModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClassModelRepositoryTest {
    @Autowired
    private ClassModelRepository classModelRepository;

    private final String CLASS_CODE = "CAL101";
    private final String CLASS_TITLE = "Calculus 103";
    private final String CLASS_DESCRIPTION = "A description 5";

    private final String NEW_CLASS_CODE = "MAT101";
    private final String NEW_CLASS_TITLE = "Math 101";
    private final String NEW_CLASS_DESCRIPTION = "A description for MAT101";

    private final String UPDATED_CLASS_CODE = "MAT102";
    private final String INVALID_CLASS_CODE = "INVALID";


    private ClassModel classModel;

    @Before
    public void setUp() {
        classModel = new ClassModel();
        classModel.setCode(NEW_CLASS_CODE);
        classModel.setTitle(NEW_CLASS_TITLE);
        classModel.setDescription(NEW_CLASS_DESCRIPTION);

    }

    @Test
    public void whenFindAll_thenReturnClasses() {
        List<ClassModel> classModels = classModelRepository.findAll();
        assert(classModels.size() == 5);
    }

    @Test
    public void whenFindByCode_thenReturnClasses() {
        List<ClassModel> classModels = classModelRepository.findByCode(CLASS_CODE);
        assert(classModels.size() == 1);
    }

    @Test
    public void whenFindByTitle_thenReturnClasses() {
        List<ClassModel> classModels = classModelRepository.findByTitle(CLASS_TITLE);
        assert(classModels.size() == 1);
    }

    @Test
    public void whenFindByDescription_thenReturnClasses() {
        List<ClassModel> classModels = classModelRepository.findByDescription(CLASS_DESCRIPTION);
        assert(classModels.size() == 1);
    }

    @Test
    public void shouldCreateNewEntryInDB(){
        classModel = classModelRepository.save(classModel);

        assertNotNull(classModel.getId());
    }

    @Test
    public void shouldUpdateExistingEntryInDB(){
        classModel = classModelRepository.save(classModel);

        Long originalId = classModel.getId();

        classModel.setCode(UPDATED_CLASS_CODE);

        classModel = classModelRepository.save(classModel);

        assertEquals(originalId, classModel.getId());
        assertEquals(UPDATED_CLASS_CODE, classModel.getCode());
        assertEquals(NEW_CLASS_DESCRIPTION, classModel.getDescription());
        assertEquals(NEW_CLASS_TITLE, classModel.getTitle());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldCheckIfClassCodeFormatIsValid(){
        classModel = new ClassModel();
        classModel.setCode(INVALID_CLASS_CODE);

        classModelRepository.save(classModel);
    }
}
