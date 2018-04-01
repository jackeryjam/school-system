package com.jackeryjam.proglang.exp1;

import com.jackeryjam.proglang.exp1.student.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Exp1ApplicationTests {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
		List<String> res = jdbcTemplate.query(
			"SELECT * FROM student ", new Object[] { },
			(rs, rowNum) -> {
				return "{" + "id:\"" + rs.getString("id") + "\"," +
                        "name:\"" + rs.getString("name") + "\"," +
                        "age:" + rs.getInt("age") + "," +
                        "sex:" + rs.getBoolean("sex") + "," +
                        "grade:" + rs.getInt("grade") + "," +
                        "score:" + rs.getInt("score") + "}";
			});
		res.forEach(item -> System.out.println(item));
		System.out.println(res.size());
	}

	@Test
	public void rawStudentGetTest(){

	}
}
