package com.j6d1.demo;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Jackson2 {
    public static void main(String[] args) throws Exception {
        // System.out.println("Demo 1");
        // demo1();
        // System.out.println("Demo 2");
        // demo2();
        // System.out.println("Demo 3");
        // demo3();
        // System.out.println("Demo 4");
        // demo4();
        // System.out.println("Demo 5");
        // demo5();
        // System.out.println("Demo 6");
        // demo6();
        System.out.println("Demo 7");
        demo7();
    }

    private static void demo7() throws Exception {
        Contact contact = new Contact("teonv@gmail.com", "0913745789", null);
        List<String> subjects = Arrays.asList("WEB205", "COM108");
        Student2 student = new Student2("Nguyễn Văn Tèo", true, 7.5, contact, subjects);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(student);
        mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, student);
        mapper.writeValue(new File("c:/temp/student.json"), student);

    }

    private static void demo6() throws Exception {
        Map<String, Object> contact = new HashMap<String, Object>();
        contact.put("email", "teonv@gmail.com");
        contact.put("phone", "0913745789");

        List<String> subjects = Arrays.asList("WEB205", "COM108");

        Map<String, Object> student = new HashMap<String, Object>();
        student.put("name", "Nguyễn Văn Tèo");
        student.put("marks", 7.5);
        student.put("gender", true);
        student.put("contact", contact);
        student.put("subjects", subjects);

        ObjectMapper mapper = new ObjectMapper();
        // Write to String
        String json = mapper.writeValueAsString(student);
        System.out.println(json);
        // Write to output
        mapper.writeValue(System.out, student);
        // Write to file
        mapper.writeValue(new File("d:/FPL/Ky5_Block2/SOF3061-Java6/student1.json"), student);

    }

    private static void demo5() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode student = mapper.createObjectNode();

        student.put("name", "Nguyễn Văn Tèo");
        student.put("marks", 7.5);
        student.put("gender", true);

        ObjectNode contact = student.putObject("contact");
        contact.put("email", "teonv@gmail.com");
        contact.put("phone", "0913745789");

        ArrayNode subjects = student.putArray("subjects");
        subjects.add("WEB205");
        // Write to String
        String json = mapper.writeValueAsString(student);
        // Write to output
        mapper.writeValue(System.out, student);
        // Write to file
        mapper.writeValue(new File("d:/FPL/Ky5_Block2/SOF3061-Java6/student.json"), student);
    }

    private static void demo4() throws Exception {
        String path = "D:\\VSCode\\Java6\\J6D1\\demo\\src\\main\\java\\com\\j6d1\\demo\\students.json";
        TypeReference<List<Student2>> type = new TypeReference<List<Student2>>() {
        };
        ObjectMapper mapper = new ObjectMapper();
        List<Student2> students = mapper.readValue(new File(path), type);

        students.forEach(student -> {
            System.out.println(">> Name: " + student.getName());
        });

    }

    private static void demo3() throws Exception {
        String path = "D:\\VSCode\\Java6\\J6D1\\demo\\src\\main\\java\\com\\j6d1\\demo\\student.json";
        ObjectMapper mapper = new ObjectMapper();
        Student2 student = mapper.readValue(new File(path), Student2.class);

        System.out.println(">> Name: " + student.getName());
        System.out.println(">> Marks: " + student.getMarks());
        System.out.println(">> Gender: " + student.getGender());

        Contact contact = student.getContact();
        System.out.println(">> Email: " + contact.getEmail());
        System.out.println(">> Phone: " + contact.getPhone());

        List<String> subjects = student.getSubjects();
        subjects.forEach(subject -> {
            System.out.println(">> Subject: " + subject);
        });

    }

    @SuppressWarnings("unchecked")
    private static void demo2() throws Exception {
        String path = "D:\\VSCode\\Java6\\J6D1\\demo\\src\\main\\java\\com\\j6d1\\demo\\students.json";
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> students = mapper.readValue(new File(path), List.class);
        students.forEach(student -> {
            System.out.println(">> Name:" + student.get("name"));
        });
    }

    @SuppressWarnings("unchecked")
    private static void demo1() throws Exception {
        String path = "D:\\VSCode\\Java6\\J6D1\\demo\\src\\main\\java\\com\\j6d1\\demo\\student.json";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> student = mapper.readValue(new File(path), Map.class);

        System.out.println(">> Name: " + student.get("name"));
        System.out.println(">> Marks: " + student.get("marks"));
        System.out.println(">> Gender: " + student.get("gender"));

        Map<String, Object> contact = (Map<String, Object>) student.get("contact");
        System.out.println(">> Email: " + contact.get("email"));
        System.out.println(">> Phone: " + contact.get("phone"));

        List<String> subjects = (List<String>) student.get("subjects");
        subjects.forEach(subject -> {
            System.out.println(">> Subject: " + subject);
        });

    }
}
