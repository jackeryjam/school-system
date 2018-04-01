package com.jackeryjam.proglang.exp1.rawStudent;

import com.jackeryjam.proglang.exp1.utils.Msg;
import com.jackeryjam.proglang.exp1.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("raw/student")
public class RawStudentController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/{id}")
    public Msg getStudent(@PathVariable String id) {
        List<String> res = jdbcTemplate.query(
            "SELECT * FROM student where id = ?", new Object[] { id },
            (rs, rowNum) -> {
                return "{" + "id:'" + rs.getString("id") + "'," +
                        "name:'" + rs.getString("name") + "'," +
                        "age:" + rs.getInt("age") + "," +
                        "sex:" + rs.getBoolean("sex") + "," +
                        "grade:" + rs.getInt("grade") + "," +
                        "score:" + rs.getInt("score") + "}";
            });
        if(res.size() == 1){
            return ResultUtil.success(res.get(0));
        } else {
            return ResultUtil.notFount();
        }
    }

    @GetMapping
    public Msg getStudents(@RequestParam(defaultValue = "0") Boolean sort, @RequestParam(defaultValue = "0") Boolean desc) {
        List<String> res = jdbcTemplate.query(
                "SELECT * FROM student ", new Object[] { },
                (rs, rowNum) -> {
                    return "{" + "id:'" + rs.getString("id") + "'," +
                            "name:'" + rs.getString("name") + "'," +
                            "age:" + rs.getInt("age") + "," +
                            "sex:" + rs.getBoolean("sex") + "," +
                            "grade:" + rs.getInt("grade") + "," +
                            "score:" + rs.getInt("score") + "}";
                });
        if (sort && !desc) res.sort(Comparator.naturalOrder());
        else if (sort && desc) res.sort(Comparator.reverseOrder());
        return ResultUtil.success(res.toString());
    }

    @PostMapping
    public Msg creatStudent(@RequestParam(value = "name", required = true) String name,
                            @RequestParam(value = "age", required = true) String age,
                            @RequestParam(value = "sex", required = true) String sex,
                            @RequestParam(value = "grade", required = true) String grade,
                            @RequestParam(value = "score", required = true) String score) {
        jdbcTemplate.update(
                "INSERT INTO student(id, name, age, sex, grade, score) VALUES (UUID(), ?, ?, ?, ?, ?)", new Object[] { name, age, sex, grade, score });
        return ResultUtil.success();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Msg deleteStudent(@PathVariable String id) {
        jdbcTemplate.update("DELETE FROM student WHERE id = ?", new Object[] { id });
        return ResultUtil.success();
    }

    @PutMapping("/{id}")
    @Transactional
    public Msg updateStudent(@PathVariable String id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "age") String age,
                             @RequestParam(value = "sex") String sex,
                             @RequestParam(value = "grade") String grade,
                             @RequestParam(value = "score") String score) {
        if (name != null)jdbcTemplate.update(
                "UPDATE  student SET name = ? WHERE id = ?", new Object[] { name, id });
        if (age != null)jdbcTemplate.update(
                "UPDATE  student SET age = ? WHERE id = ?", new Object[] { age, id });
        if (sex != null)jdbcTemplate.update(
                "UPDATE  student SET sex = ? WHERE id = ?", new Object[] { sex, id });
        if (grade != null)jdbcTemplate.update(
                "UPDATE  student SET grade = ? WHERE id = ?", new Object[] { grade, id });
        if (score != null)jdbcTemplate.update(
                "UPDATE  student SET score = ? WHERE id = ?", new Object[] { score, id });
        return ResultUtil.success();
    }
}
