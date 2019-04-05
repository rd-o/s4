package com.example.s4.repositories;

import com.example.s4.models.ClassModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "classModel", path = "class")
public interface ClassModelRepository extends PagingAndSortingRepository<ClassModel, Long> {
    List<ClassModel> findAll();

    List<ClassModel> findByCode(@Param("code") String code);

    List<ClassModel> findByTitle(@Param("title") String title);

    List<ClassModel> findByDescription(@Param("description") String description);
}
