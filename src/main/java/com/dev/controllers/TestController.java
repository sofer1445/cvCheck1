package com.dev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dev.readingFile;
import com.dev.cvFile;
import com.dev.checkFile;

@Controller
@RequestMapping("/cvCheck")
public class TestController {

    @GetMapping



    @ResponseBody
    public String handleGetRequest() {
        // Handle GET request
        return "Hello, World!";
    }

    @PostMapping
    @ResponseBody
    public String handlePostRequest() {
        // Handle POST request
        return "POST request received!";
    }

    // Other HTTP methods (PUT, DELETE, etc.) can be handled similarly using appropriate annotations.
}
