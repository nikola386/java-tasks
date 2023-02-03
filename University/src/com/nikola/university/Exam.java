package com.nikola.university;
import java.time.Duration;
import java.util.ArrayList;

public class Exam {
    private final Subject subject;
    private final ArrayList<Question> questions;
    private final Duration duration;

    public Exam(Subject subject, ArrayList<Question> questions, Duration duration) {
        this.subject = subject;
        this.duration = duration;
        this.questions = questions;
    }

    ArrayList<Question> getQuestions() {
        return questions;
    }
}
