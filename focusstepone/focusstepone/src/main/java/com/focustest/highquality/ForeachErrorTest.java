package com.focustest.highquality;

import java.util.ArrayList;
import java.util.List;

/**
 * focus Create in 15:21 2018/12/28
 */
public class ForeachErrorTest {
    public static void main(String[] args) {
        List<String> books = new ArrayList<String>();
        books.add("book1");
        books.add("book2");
        books.add("book3");
        for (String book : books) {
            book = "book4";
            System.out.println(book);
        }
        System.out.println(books);

        Person[] personList = new Person[3];
        personList[0] = new Person("focus", 28);
        personList[1] = new Person("cloud", 28);
        personList[2] = new Person("fish", 1);

        for (Person person : personList) {
            person = new Person("temp", 22);
            System.out.println(person);
        }
        for (Person person : personList) {
            System.out.println(person
            );
        }
    }


}

class Person {
    private int age;
    private String name;
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString(){
        String str = "name = " + name + ", age = " + age;
        return str;
    }
}
