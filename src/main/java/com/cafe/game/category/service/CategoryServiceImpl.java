package com.cafe.game.category.service;

import com.cafe.game.category.model.Category;
import com.cafe.game.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;


    @Override
    public List<String> listCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(Category::getCode)
                .toList();
    }
}
