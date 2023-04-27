package com.example.botscrewtesttask;

import com.example.botscrewtesttask.constants.RegExConstants;
import com.example.botscrewtesttask.service.DepartmentService;
import com.example.botscrewtesttask.service.LectorService;
import com.example.botscrewtesttask.service.RequestParserService;
import com.example.botscrewtesttask.service.serviceImpl.RequestParserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
@RequiredArgsConstructor
public class BotsCrewTestTaskApplication implements CommandLineRunner {
    private final LectorService lectorService;
    private final DepartmentService departmentService;

    public static void main(String[] args) {
        SpringApplication.run(BotsCrewTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RequestParserService headOfDepartmentParser = new RequestParserServiceImpl(RegExConstants.HEAD_OF_DEPARTMENT_REGEX);
        RequestParserService statisticsParser = new RequestParserServiceImpl(RegExConstants.STATISTIC_REGEX);
        RequestParserService averageSalaryParser = new RequestParserServiceImpl(RegExConstants.AVERAGE_SALARY_REGEX);
        RequestParserService employeeCountParser = new RequestParserServiceImpl(RegExConstants.EMPLOYEE_COUNT_REGEX);
        RequestParserService globalSearchParser = new RequestParserServiceImpl(RegExConstants.GLOBAL_SEARCH_REGEX);

        /*REQUESTS
        * Who is head of department {department_name}.
        * Show {department_name} statistics
        * Show the average salary for the department {department_name}.
        * Show count of employee for {department_name}.
        * Global search by {template}.
        */

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your request: ");
            String request = scanner.nextLine();
            String[] arguments;
            if ((arguments = headOfDepartmentParser.parse(request)) != null) {
                String departmentName = arguments[0];
                String headOfDepartmentName = departmentService.getHeadOfDepartment(departmentName);
                System.out.println("Head of " + departmentName + " department is " + headOfDepartmentName);
            } else if ((arguments = statisticsParser.parse(request)) != null) {
                String departmentName = arguments[0];
                System.out.println(departmentService.getDepartmentStatistic(departmentName));
            } else if ((arguments = averageSalaryParser.parse(request)) != null) {
                String departmentName = arguments[0];
                int avgSalary = departmentService.countAveragesSalaryInDepartment(departmentName);
                System.out.println("The average salary of " + departmentName + " is " + avgSalary);
            }  else if ((arguments = employeeCountParser.parse(request)) != null) {
                String departmentName = arguments[0];
                Long lectorsCount = departmentService.countLectorsInDepartment(departmentName);
                System.out.println(lectorsCount);
            } else if ((arguments = globalSearchParser.parse(request)) != null) {
                String template = arguments[0];
                if (template.length() > 0) {
                    List<String> searchResult = lectorService.globalSearchByTemplate(template);
                    System.out.println(searchResult.toString());
                } else {
                    System.out.println("No results found.");
                }
            } else {
                System.out.println("Wrong request!");
            }
        }
    }
}
