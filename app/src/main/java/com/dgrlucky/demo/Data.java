package com.dgrlucky.demo;

import com.dgrlucky.demo.bean.Man;
import com.dgrlucky.demo.bean.Person;
import com.dgrlucky.demo.bean.WoMan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dgrlucky
 * @date 2016/3/25 15:00
 * @company dgrlucky
 * @desc
 */
public class Data {

    public static Person getObject() {
        Person person = new Person();
        person.setAge(20);
        person.setName("dgrlucky");
        person.setScore(100.5f);
        return person;
    }

    public static List<Person> getObjectList() {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(getObject());
        }
        return list;
    }

    public static List<String> getStringList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    public static Map<String, Person> getObjectMap() {
        Map<String, Person> map = new HashMap<>();
        map.put("a", getObject());
        map.put("b", getObject());
        map.put("c", getObject());
        return map;
    }

    public static HashMap<String, String> getStringMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "卡兹克");
        map.put("b", "德莱文");
        map.put("c", "维克多");
        return map;
    }

    public static int[] getIntArray() {
        int[] intArray = {1, 3, 5, 7, 9, 11};
        return intArray;
    }


    public static Person[] getObjectArray() {
        Person[] persons = {getObject(), getObject(), getObject(), getObject()};
        return persons;
    }

    public static Person[][] getObjectArray2() {
        Person[][] persons = {{getObject(), getObject(), getObject(), getObject()},
                {getObject(), getObject(), getObject(), getObject()}};
        return persons;
    }

    public static String[] getStringArray() {
        String[] stringArray = {"1", "2", "1", "2", "1", "2", "1", "2", "1", "2"};
        return stringArray;
    }

    public static String[][] getStringArray2() {
        String[][] stringArray2 = {{"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}};
        return stringArray2;
    }

    public static String[][][] getStringArray3() {
        String[][][] stringArray3 = {{{"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}},
                {{"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}}};
        return stringArray3;
    }

    public static String getJson() {
        String json = "{'a':'b','c':{'aa':234,'dd':{'az':12}}}";
        return json;
    }

    public static Man getMan() {
        Man person = new Man(1);
        person.setAge(12);
        person.setName("dgrlucky");
        person.setScore(80.5f);
        return person;
    }

    public static Man getWoMan() {
        Man person = new WoMan(10, "annie", 'x');
        return person;
    }
}
