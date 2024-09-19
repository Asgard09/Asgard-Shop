package com.example.backend.repository;

import com.example.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.level = :level")
    public Category findByNameAndLevel(@Param("name") String name, @Param("level") int level);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.parentCategory = :parentCategory AND c.level = :level")
    public Category findByNameAndParentAndLevel(@Param("name") String name,
                                                @Param("parentCategory") Category parentCategory,
                                                @Param("level") int level);
}
