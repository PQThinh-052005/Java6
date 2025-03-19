package com.j6d1.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lambda {

    static List<Student> list = Arrays.asList(
            new Student("Nguyễn Văn Tèo", true, 7.5),
            new Student("Trần Thị Thu Hương", false, 5.5),
            new Student("Phạm Đức Cường", true, 9.5),
            new Student("Lê Thị Mỹ Hồng", false, 6.5),
            new Student("Đoàn Thị Kim Ty", false, 8.0));

    public static void main(String[] args) {
        System.out.println("Demo 1");
        demo1();
        System.out.println("Demo 2");
        demo2();
        System.out.println("Demo 3");
        demo3();
        System.out.println("Demo 4");
        demo4();
    }

    private static void demo4() {
       Demo4Interface obj = x -> System.out.println("m1: " + x);
         obj.m1(100);
         obj.m2(200);
         
    }

    private static void demo3() {
        // bỏ "-" để sắp xếp tăng dần
        Collections.sort(list, (sv1, sv2) -> -sv1.getMarks().compareTo(sv2.getMarks()));
        list.forEach(sv -> {
            System.out.println(">> Name: " + sv.getName());
            System.out.println(">> Marks:" + sv.getMarks());
            System.out.println();

        });
    }

    private static void demo2() {

        List<Student> list = Arrays.asList(
                new Student("Nguyễn Văn Tèo", true, 7.5),
                new Student("Trần Thị Thu Hương", false, 5.5),
                new Student("Phạm Đức Cường", true, 9.5),
                new Student("Lê Thị Mỹ Hồng", false, 6.5),
                new Student("Đoàn Thị Kim Ty", false, 8.0));

        list.forEach(sv -> {
            System.out.println(">> Name:" + sv.getName());
            System.out.println(">> Marks:" + sv.getMarks());
            System.out.println();

        });
    }

    public static void demo1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.forEach(System.out::println);
    }
}

@FunctionalInterface
interface Demo4Interface {
    void m1(int x);
    default void m2(int x) {
        System.out.println("m2: " + x);
    }
    public static void m3() {
        
    }
}