package com.j6d1.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {
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
                double average = list.stream()
                .mapToDouble(sv -> sv.getMarks())
                .average().getAsDouble();
                System.out.println("average: " + average);
                
                double sum = list.stream()
                .mapToDouble(sv -> sv.getMarks())
                .sum();
                System.out.println("sum: " + sum);
                
                double min_marks = list.stream()
                .mapToDouble(sv -> sv.getMarks())
                .min().getAsDouble();
                System.out.println("min_marks: " + min_marks);
                
                boolean all_passed = list.stream().allMatch(sv -> sv.getMarks() >= 5);
                System.out.println("all_passed:" + all_passed);
                
                Student min_sv = list.stream()
                .reduce(list.get(0), (min, sv) -> sv.getMarks() < min.getMarks() ? sv : min);
                System.out.println("min_sv: " + min_sv.getName());
                
            }
        
            private static void demo3() {
        List<Student> result = list.stream()
                .filter(sv -> sv.getMarks() >= 7)
                .peek(sv -> sv.setName(sv.getName().toUpperCase()))
                .collect(Collectors.toList());

        result.forEach(sv -> {
            System.out.println(">> Name: " + sv.getName());
            System.out.println(">> Marks: " + sv.getMarks());
            System.out.println();
        });
    }

    private static void demo2() {
        List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);
        @SuppressWarnings("unused")
        List<Double> result = list.stream() // Stream <Integer>
                .filter(n -> n % 2 == 0) // Stream <Integer>
                .map(n -> Math.sqrt(n)) // Stream<Double>
                .peek(d -> System.out.println(d)) // Stream<Double>
                .collect(Collectors.toList()); // List<Double>
    }

    private static void demo1() {
        Stream<Integer> stream1 = Stream.of(1, 9, 4, 7, 5, 2);
        stream1.forEach(n -> {
            System.out.println(n);

        });

        List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);
        // Stream<Integer> stream2 = list.stream();
        list.stream().forEach(n -> {
            System.out.println(n);

        });
    }
}
