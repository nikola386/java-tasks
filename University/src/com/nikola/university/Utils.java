package com.nikola.university;

import java.util.Random;

public class Utils {
    static int getRandom(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }
}
