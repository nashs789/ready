package com.example.ready.entity;

import com.example.ready.enums.BookCategory;
import com.example.ready.enums.Stars;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Timestamp{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Stars stars;

    @Column(nullable = false)
    private String upc;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String tax;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private String reviews;
}