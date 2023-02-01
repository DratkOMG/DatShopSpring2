package com.example.datshopspring2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Five {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String choice = scanner.nextLine();
        String[] strings = choice.split(" ");

        List<Integer> list = new ArrayList<>();
        for (String str : strings) {
            list.add(Integer.valueOf(str));
        }
        double battery = 100;
        int sumDistance = 0;
        int i = 1;
        while (battery > 0 && i < list.size()) {
            int distance = list.get(i);
            if (distance == 0) {
                battery += 25;
            } else if (distance < 0) {
                sumDistance += (-distance);
                while (distance <= -10) {
                    battery++;
                    distance += 10;
                }
            } else {
                double percentNeed = distance / 2.15;
                if (percentNeed > battery) {
                    sumDistance += battery * 2.15;
                    battery = 0;
                } else {
                    sumDistance += distance;
                    battery -= percentNeed;
                }
            }
            if (battery > 100) {
                battery = 100;
            }
            i++;
        }
        System.out.println(sumDistance);
    }
}

