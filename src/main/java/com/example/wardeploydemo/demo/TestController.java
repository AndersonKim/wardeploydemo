package com.example.wardeploydemo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    JdbcTemplate template;

    @RequestMapping("/test")
    public String getList(){
        List<Map<String, Object>> a = template.queryForList("select dept_id from test.dept");
        return String.valueOf(a.size());
    }
}
