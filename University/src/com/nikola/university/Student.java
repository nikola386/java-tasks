package com.nikola.university;

import java.util.ArrayList;
import java.util.Random;

public class Student {
    private final String name;
    private final int age;
    private final String major;
    private final ArrayList<Subject> subjects;

    public Student(String name, int age, String major, ArrayList<Subject> subjects) {
        this.name = name;
        this.age = age;
        this.major = major;
        this.subjects = subjects;
    }

    int answerQuestion(Question q) {
        return Utils.getRandom(1, 5);
    }
}
