package com.example.wardeploydemo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    JdbcTemplate template;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping("/testJdbc")
    public String testJdbc(){
        List<Map<String, Object>> a = template.queryForList("select dept_id from test.dept");
        return String.valueOf(a.size());
    }
    @RequestMapping("/testEM")
    public String testEM(){
        Query query = entityManager.createNativeQuery("select dept_id from test.dept");
        List a = query.getResultList();
        //List<Map<String, Object>> a = template.queryForList("select dept_id from test.dept");
        return String.valueOf(a.size());
    }
}
