package com.assignment.projectfashion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    private Feedback feedback;


    @GetMapping("/Feedback")
    public List<Feedback> getAllFeedbackBlogDetails(){
        return feedbackRepository.findAll();
    }

    @GetMapping("/Feedback/{id}")
    public ResponseEntity<Feedback> getFeedbackBlogDetailsById(@PathVariable Long id)throws ResourceNotFoundException {

        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feedback Contents not found for this id ::" + id));

        return ResponseEntity.ok().body(feedback);
    }

    @GetMapping("/feedback/{categoriesid}")
    public List<Feedback> getFeedbackBlogDetailsByFeedback(@PathVariable String categoriesid){

       return feedbackRepository.findAllByCategoriesId(categoriesid);
    }

    @PostMapping("/Feedback/{id}")
    public Feedback createNewFeedbackBlogDetails(@Valid @RequestBody Feedback fashionFeedback, @PathVariable Long id)throws ResourceNotFoundException {
        return categoriesRepository.findById(id).map(category -> {
                fashionFeedback.setCategories(category);
        return feedbackRepository.save(fashionFeedback);
        }).orElseThrow(() -> new ResourceNotFoundException("categories not found"));
    }

    @PutMapping("/Feedback/{id}")
    public ResponseEntity<Feedback> updateFeedbackBlogDetails(@PathVariable(value = "id") Long feedbackId, @Valid @RequestBody Feedback fashionFeedback) throws ResourceNotFoundException{
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new ResourceNotFoundException("No details found for the Feedback Blog ID :: "+ feedbackId));
        feedback.setCategories(feedback.getCategories());
        final Feedback updatedFeedbackInfoForTheBlog = feedbackRepository.save(feedback);
        return ResponseEntity.ok(updatedFeedbackInfoForTheBlog);
    }

    @PutMapping("/Feedback/{id}/likes")
    public ResponseEntity<Feedback> updateFeedbackBlogLikesDetails(@PathVariable(value = "id") Long feedbackId, @Valid @RequestBody Feedback fashionFeedback) throws ResourceNotFoundException{
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new ResourceNotFoundException("No details found for the Feedback Blog ID :: "+ feedbackId));
        Long likes = feedback.getLikes();
        feedback.setLikes(++likes);
        final Feedback updatedFeedbackInfoForTheBlog = feedbackRepository.save(feedback);
        return ResponseEntity.ok(updatedFeedbackInfoForTheBlog);
    }

    @PutMapping("/Feedback/{id}/dislikes")
    public ResponseEntity<Feedback> updateFeedbackBlogLikeDetails(@PathVariable(value = "id") Long feedbackId, @Valid @RequestBody Feedback fashionFeedback) throws ResourceNotFoundException{
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new ResourceNotFoundException("No details found for the Feedback Blog ID :: "+ feedbackId));
        Long likes = feedback.getLikes();
        feedback.setLikes(--likes);
        final Feedback updatedFeedbackInfoForTheBlog = feedbackRepository.save(feedback);
        return ResponseEntity.ok(updatedFeedbackInfoForTheBlog);
    }

    @DeleteMapping("/Feedback/{id}")
    public Map<String, Boolean> deleteFeedbackBlogDetails(@PathVariable(value = "id") Long feedbackId)
        throws ResourceNotFoundException {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new ResourceNotFoundException("No Feedback Details found with this ID :: "+ feedbackId));
        feedbackRepository.delete(feedback);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return response;
    }
}
