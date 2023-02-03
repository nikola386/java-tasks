package com.nikola.university;

public class Question {
    private final String question;
    private final int answer;

    public Question(String question, int answer) {
        this.question = question;
        this.answer = answer;
    }

    int getAnswer() {
        return answer;
    }
}
