package pl.employee.demo.domain;

import pl.employee.demo.Employee;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel_D'AGE on 01.12.2018.
 */
public class MainApp {
    public static void main(String[] args) throws IOException {

        Path inputPath = Paths.get("./EmployeeData.txt");
        List<String> lines = Files.readAllLines(inputPath, Charset.forName("UTF-8"));

        //lines.forEach(System.out::println);

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            if ((lines.get(i).equals("EmployeeData"))) {
                String[] name = lines.get(i + 1).split(", ");
                int age = Integer.parseInt(lines.get(i + 2));
                boolean fullTime = lines.get(i + 3).equals("y");

                Employee emp = new Employee(name[0], name[1], age, fullTime);
                employees.add(emp);
            }

        }

        employees.get(0).setLastName("Midor");
        //zapisanie do pliku json

        Path outputFile = Paths.get("./employeeData.json");
        Files.write(outputFile, toJson(employees).getBytes(Charset.forName("UTF-8")));
    }


    //przerobienie tabeli pracowników do jsona

    private static String toJson(List<Employee> employees) {
        String empl = employees.stream()
                .map(Employee::toJson)
                .collect(Collectors.joining(","));
        return "{\"employees\": [" + empl + "]}";

    }


}
