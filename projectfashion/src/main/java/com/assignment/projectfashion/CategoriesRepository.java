package com.assignment.projectfashion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long>{


    Page<Categories> findAllByFashionCategories(String fashionCategories, PageRequest pageable);
}
