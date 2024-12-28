package com.dnth_underdog_241.online_fashion_shopping.config;


import com.dnth_underdog_241.online_fashion_shopping.model.Category;
import com.dnth_underdog_241.online_fashion_shopping.model.Size;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.CategoryEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import com.dnth_underdog_241.online_fashion_shopping.repository.CategoryRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.SizeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import com.dnth_underdog_241.online_fashion_shopping.model.Colour;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import com.dnth_underdog_241.online_fashion_shopping.repository.ColourRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@Transactional
public class DataInitializer
{
    @Bean
    public CommandLineRunner initializeColourAndSizeData(ColourRepository colourRepository, SizeRepository sizeRepository)
    {
        return args -> {
            // Initialize Colour data
            if (colourRepository.count() == 0) {
                Arrays.stream(ColourEnum.values())
                        .forEach(colourEnum -> {
                            Colour colour = Colour.builder()
                                    .colour(colourEnum)
                                    .build();
                            colourRepository.save(colour);
                        });
                log.info("Inserted initial data into the 'colour' table.");
            } else {
                log.info("'Colour' table already initialized.");
            }

            // Initialize Size data
            if (sizeRepository.count() == 0) {
                Arrays.stream(SizeEnum.values())
                        .forEach(sizeEnum -> {
                            Size size = Size.builder()
                                    .size(sizeEnum)
                                    .build();
                            sizeRepository.save(size);
                        });
                log.info("Inserted initial data into the 'size' table.");
            } else {
                log.info("'Size' table already initialized.");
            }
        };
    }


    @Bean
    CommandLineRunner initializeCategoryData(CategoryRepository categoryRepository)
    {
        return args ->
        {
            if (categoryRepository.count() == 0)
            {
                Category men = new Category(null, "Men", CategoryEnum.MEN, null, new ArrayList<>(), new ArrayList<>());
                Category women = new Category(null, "Women", CategoryEnum.WOMEN, null, new ArrayList<>(), new ArrayList<>());
                Category kids = new Category(null, "Kids", CategoryEnum.KIDS, null, new ArrayList<>(), new ArrayList<>());

                categoryRepository.saveAll(List.of(men, women, kids));

                createSubcategories(men, "Outerwear", CategoryEnum.OUTERWEAR, categoryRepository);
                createSubcategories(men, "Sweaters and Cardigans", CategoryEnum.SWEATERS_AND_CARDIGANS, categoryRepository);
                createSubcategories(men, "T-Shirts, Sweats, and Fleece", CategoryEnum.T_SHIRTS_SWEATS_AND_FLEECE, categoryRepository);
                createSubcategories(men, "Shirts and Blouses", CategoryEnum.SHIRTS_AND_BLOUSES, categoryRepository);
                createSubcategories(men, "Bottoms", CategoryEnum.BOTTOMS, categoryRepository);
                createSubcategories(men, "Accessories", CategoryEnum.ACCESSORIES, categoryRepository);
                createSubcategories(men, "Underwear", CategoryEnum.INNERWEAR_UNDERWEAR, categoryRepository);

                createSubcategories(women, "Outerwear", CategoryEnum.OUTERWEAR, categoryRepository);
                createSubcategories(women, "Sweaters and Cardigans", CategoryEnum.SWEATERS_AND_CARDIGANS, categoryRepository);
                createSubcategories(women, "T-Shirts, Sweats, and Fleece", CategoryEnum.T_SHIRTS_SWEATS_AND_FLEECE, categoryRepository);
                createSubcategories(women, "Shirts and Blouses", CategoryEnum.SHIRTS_AND_BLOUSES, categoryRepository);
                createSubcategories(women, "Bottoms", CategoryEnum.BOTTOMS, categoryRepository);
                createSubcategories(women, "Dresses and Skirts", CategoryEnum.DRESSES_AND_SKIRTS, categoryRepository);
                createSubcategories(women, "Accessories", CategoryEnum.ACCESSORIES, categoryRepository);
                createSubcategories(women, "Underwear", CategoryEnum.INNERWEAR_UNDERWEAR, categoryRepository);

                createSubcategories(kids, "Outerwear", CategoryEnum.OUTERWEAR, categoryRepository);
                createSubcategories(kids, "Sweaters and Cardigans", CategoryEnum.SWEATERS_AND_CARDIGANS, categoryRepository);
                createSubcategories(kids, "T-Shirts, Sweats, and Fleece", CategoryEnum.T_SHIRTS_SWEATS_AND_FLEECE, categoryRepository);
                createSubcategories(kids, "Shirts and Blouses", CategoryEnum.SHIRTS_AND_BLOUSES, categoryRepository);
                createSubcategories(kids, "Bottoms", CategoryEnum.BOTTOMS, categoryRepository);
                createSubcategories(kids, "Dresses and Skirts", CategoryEnum.DRESSES_AND_SKIRTS, categoryRepository);
                createSubcategories(kids, "Accessories", CategoryEnum.ACCESSORIES, categoryRepository);
                createSubcategories(kids, "Underwear", CategoryEnum.INNERWEAR_UNDERWEAR, categoryRepository);

                log.info("Inserted initial data into the 'category' table.");
            }
            else
            {
                log.info("'Category table already initialized.");
            }
        };
    }


    private void createSubcategories(Category parentCategory, String name, CategoryEnum categoryEnum, CategoryRepository categoryRepository)
    {
        Category subcategory = new Category(null, name, categoryEnum, parentCategory, new ArrayList<>(), new ArrayList<>());
        parentCategory.getChildren().add(subcategory);
        subcategory.setParent(parentCategory);
        categoryRepository.save(subcategory);
    }
}