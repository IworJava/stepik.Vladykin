package part6_2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Deque<String> list = new LinkedList<>();

        try (Scanner sc = new Scanner(System.in)) {
            int i = 0;
            while (sc.hasNext()) {
                String el = sc.next();
                if (i++ % 2 != 0) {
                    list.addFirst(el);
                }
            }
        }
        System.out.print(String.join(" ", list));
    }
}
