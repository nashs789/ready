package com.example.ready.entity;

import com.example.ready.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Problem {
    Long problemNo;
    String title;
    Level level;
    Long solved;
    Long tryCnt;
    String rate;
}
