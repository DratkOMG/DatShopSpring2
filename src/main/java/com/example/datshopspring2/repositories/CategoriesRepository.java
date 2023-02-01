package com.example.datshopspring2.repositories;

import com.example.datshopspring2.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    Categories findByCategoriesId(Long id);
}