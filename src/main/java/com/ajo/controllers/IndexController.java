package com.ajo.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public @ResponseBody ProjectInfo indexPath() {
        URL docs = null;
        try{
             docs = new URL("http://localhost/swagger-ui/");
        }catch (MalformedURLException ignored){
            //ignored
        }

        return new ProjectInfo(
                "Project Ajo",
                "create an ajo project",
                "Emmanuel Ogbinaka",
                docs
        );
    }

    @AllArgsConstructor
    @Data
    private class ProjectInfo{
        private String name,description, author;
        private URL docs;
    }
}
