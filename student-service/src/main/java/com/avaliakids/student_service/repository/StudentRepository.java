package com.avaliakids.student_service.repository;

import com.avaliakids.student_service.model.Student;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findByParentId(String parentId);
}
