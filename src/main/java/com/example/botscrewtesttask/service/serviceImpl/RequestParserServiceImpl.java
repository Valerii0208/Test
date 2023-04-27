package com.example.botscrewtesttask.service.serviceImpl;

import com.example.botscrewtesttask.service.RequestParserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParserServiceImpl implements RequestParserService {
    private final Pattern pattern;

    public RequestParserServiceImpl(String regex) {
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public String[] parse(String request) {
        Matcher matcher = pattern.matcher(request);
        if (matcher.find()) {
            String[] arguments = new String[matcher.groupCount()];
            for (int i = 0; i < arguments.length; i++) {
                arguments[i] = matcher.group(i + 1);
            }
            return arguments;
        }
        return null;
    }
}
