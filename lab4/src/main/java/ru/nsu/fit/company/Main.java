package ru.nsu.fit.company;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNext()) {
            company.terminate();
        }
    }
}
