package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public static Properties prop = new Properties();
    public static String fileName;

    public void fillInPropertiesMap(){
        //implement this method - реализуйте этот метод
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            fileName = consoleReader.readLine();
            consoleReader.close();

            FileInputStream fileInputStream = new FileInputStream(Solution.fileName);
            load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        prop.clear();
        for (Map.Entry<String, String> pair : properties.entrySet()) {
            prop.put (pair.getKey(), pair.getValue());
        }

        prop.store(outputStream,"");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        prop.load(inputStream);
        for (Map.Entry<Object, Object> pair: prop.entrySet()) {
            properties.put((String) pair.getKey(), (String) pair.getValue());
        }
    }

    public static void main(String[] args) throws Exception{
        Solution solution = new Solution();
        solution.fillInPropertiesMap ();

        FileOutputStream fileOutputStream = new FileOutputStream(Solution.fileName);
        solution.save(fileOutputStream);
        fileOutputStream.close();
    }
}