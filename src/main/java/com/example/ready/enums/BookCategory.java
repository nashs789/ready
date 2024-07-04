package com.example.ready.enums;

import java.util.HashMap;
import java.util.Map;

public enum BookCategory{
    TRAVEL("Travel"),
    MYSTERY("Mystery"),
    HISTORICAL_FICTION("Historical Fiction"),
    SEQUENTIAL_ART("Sequential Art"),
    CLASSICS("Classics"),
    PHILOSOPHY("Philosophy"),
    ROMANCE("Romance"),
    WOMENS_FICTION("Womens Fiction"),
    FICTION("Fiction"),
    CHILDRENS("Childrens"),
    RELIGION("Religion"),
    NONFICTION("Nonfiction"),
    MUSIC("Music"),
    DEFAULT("Default"),
    SCIENCE_FICTION("Science Fiction"),
    SPORTS_AND_GAMES("Sports and Games"),
    ADD_A_COMMENT("Add a comment"),
    FANTASY("Fantasy"),
    NEW_ADULT("New Adult"),
    YOUNG_ADULT("Young Adult"),
    SCIENCE("Science"),
    POETRY("Poetry"),
    PARANORMAL("Paranormal"),
    ART("Art"),
    PSYCHOLOGY("Psychology"),
    AUTOBIOGRAPHY("Autobiography"),
    PARENTING("Parenting"),
    ADULT_FICTION("Adult Fiction"),
    HUMOR("Humor"),
    HORROR("Horror"),
    HISTORY("History"),
    FOOD_AND_DRINK("Food and Drink"),
    CHRISTIAN_FICTION("Christian Fiction"),
    BUSINESS("Business"),
    BIOGRAPHY("Biography"),
    THRILLER("Thriller"),
    CONTEMPORARY("Contemporary"),
    SPIRITUALITY("Spirituality"),
    ACADEMIC("Academic"),
    SELF_HELP("Self Help"),
    HISTORICAL("Historical"),
    CHRISTIAN("Christian"),
    SUSPENSE("Suspense"),
    SHORT_STORIES("Short Stories"),
    NOVELS("Novels"),
    HEALTH("Health"),
    POLITICS("Politics"),
    CULTURAL("Cultural"),
    EROTICA("Erotica"),
    CRIME("Crime"),
    UNKNOWN("Define This Category Plz");

    private String categoryName;
    private static final Map<String, BookCategory> BOOK_CATEGORY_MAP = new HashMap<>();

    static {
        for(BookCategory category: BookCategory.values()) {
            BOOK_CATEGORY_MAP.put(category.getCategoryName(), category);
        }
    }

    BookCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static BookCategory getBookCategoryEnum(String category) {
        return BOOK_CATEGORY_MAP.getOrDefault(category, UNKNOWN);
    }
}