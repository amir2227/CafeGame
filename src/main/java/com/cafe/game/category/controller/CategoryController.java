package com.cafe.game.category.controller;

import com.cafe.game.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cafe.game.category.controller.CategoryController.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
@RequiredArgsConstructor
public class CategoryController {
    public static final String BASE_URL = "/api/v1/categories";
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<String>> listCategories(){
        return ResponseEntity.ok(categoryService.listCategories());
    }
}
