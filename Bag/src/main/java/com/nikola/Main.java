package com.nikola;

import com.nikola.bag.Bag;

public class Main {
    public static void main(String[] args) {
        Bag<Object> bag = new Bag<>(100);

        for (int i = 0; i < 10; i++) {
            bag.add("Item " + i);
            bag.add("Item " + i);
        }

        System.out.println("Grab random items");
        System.out.println(bag.grab());
        System.out.println(bag.grab());
        System.out.println(bag.grab());

        System.out.println("Grab items by index");
        for (int i = 0; i < 5; i++) {
            System.out.println(bag.grab(i));
        }

        System.out.println("Remove non-existent item: " + bag.remove("non existent"));

        System.out.println("Remove items by index");
        for (int i = 0; i < 5; i++) {
            bag.remove(i);
        }

        System.out.println("Remove items by item ref");
        for (int i = 0; i < 5; i++) {
            Object item = bag.grab();
            bag.remove(item);
        }

        System.out.println("Check if bag contains an item");
        for (int i = 0; i < 5; i++) {
            Object item = bag.grab();
            System.out.println(item + ": " + bag.contains(item));
        }

        System.out.println("Bag size: " + bag.size());
        System.out.println("Bag to array: " + bag.toArray());

        System.out.println("Bag to string: " + bag);

        System.out.println("Bag is empty: " + bag.isEmpty());
        System.out.println("Clearing the bag");
        bag.clear();
        System.out.println("Bag is empty: " + bag.isEmpty());

        try {
            System.out.println("Grab item from empty bag:");
            bag.grab();
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        }
    }
}