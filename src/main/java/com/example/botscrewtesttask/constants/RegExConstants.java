package com.example.botscrewtesttask.constants;

public final class RegExConstants {
    public static final String HEAD_OF_DEPARTMENT_REGEX = ".*Who is head of department\\s*\\{(.+?)\\}.*";
    public static final String STATISTIC_REGEX = ".*Show\\s*\\{(.+?)\\}\\s*statistics.*";
    public static final String AVERAGE_SALARY_REGEX = ".*Show the average salary for the department\\s*\\{(.+?)\\}.*";
    public static final String EMPLOYEE_COUNT_REGEX = "Show count of employee for\\s*\\{([^\\}]+)\\}";
    public static final String GLOBAL_SEARCH_REGEX = ".*Global search by\\s*\\{(.+?)\\}.*";
}
