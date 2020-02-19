package com.assignment.projectfashion;

import com.assignment.projectfashion.Categories;
import com.assignment.projectfashion.CategoriesRepository;
import com.assignment.projectfashion.Feedback;
import com.assignment.projectfashion.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CategoriesController {
    @Autowired
    private com.assignment.projectfashion.CategoriesRepository CategoriesRepository;
    private Categories categories;


    @GetMapping("/Content/{pageNumber}")
    public Page<Categories> getAllCategoriesBlogDetails(@PathVariable int pageNumber){
        return CategoriesRepository.findAll(PageRequest.of(pageNumber, 5));
    }

    @GetMapping("/Categories/{id}")
    public ResponseEntity<Categories> getCategoriesBlogDetailsById(@PathVariable Long id)throws ResourceNotFoundException {

        Categories categories = CategoriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categories Contents not found for this id ::" + id));

        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/categories/{fashionCategories}/{no}")
    public Page<Categories> getCategoriesBlogDetailsByCategories(@PathVariable("fashionCategories") String fashionCategories,  @PathVariable("no") int no){

        return CategoriesRepository.findAllByFashionCategories(fashionCategories, PageRequest.of(no, 5));
    }

    @PostMapping("/categories")
    public Categories createNewCategoriesBlogDetails(@Valid @RequestBody Categories categories){
        return CategoriesRepository.save(categories);}

    @PutMapping("/Categories/{id}")
    public ResponseEntity<Categories> updateCategoriesBlogDetails(@PathVariable(value = "id") Long categoriesId, @Valid @RequestBody Categories fashionCategories) throws ResourceNotFoundException{
        Categories categories = CategoriesRepository.findById(categoriesId).orElseThrow(() -> new ResourceNotFoundException("No details found for the Categories Blog ID :: "+ categoriesId));
        categories.setFashionCategories(fashionCategories.getFashionCategories());
        final Categories updatedCategoriesInfoForTheBlog = CategoriesRepository.save(categories);
        return ResponseEntity.ok(updatedCategoriesInfoForTheBlog);
    }

    @DeleteMapping("/Categories/{id}")
    public Map<String, Boolean> deleteCategoriesBlogDetails(@PathVariable(value = "id") Long categoriesId)
        throws ResourceNotFoundException {
        Categories categories = CategoriesRepository.findById(categoriesId).orElseThrow(() -> new ResourceNotFoundException("No Categories Details found with this ID :: "+ categoriesId));
        CategoriesRepository.delete(categories);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return response;
    }
}
