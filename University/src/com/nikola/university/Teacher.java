package com.nikola.university;

import java.util.ArrayList;

public class Teacher {
    private final String name;
    private final Title title;
    private final ArrayList<Exam> exams;

    public Teacher(String name, Title title, ArrayList<Exam> exams) {
        this.name = name;
        this.title = title;
        this.exams = exams;
    }

    private void examStudent(Student s) {
        Exam exam = exams.get(Utils.getRandom(0, exams.size()));
        exam.getQuestions().forEach((q) -> {
            int answer = s.answerQuestion(q);
            if(answer == q.getAnswer()) {
                System.out.println("Correct answer");
            } else {
                System.out.println("Wrong answer");
            }
        });
    }
}
