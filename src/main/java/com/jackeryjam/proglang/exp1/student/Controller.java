package com.jackeryjam.proglang.exp1.student;

import com.jackeryjam.proglang.exp1.utils.Msg;
import com.jackeryjam.proglang.exp1.utils.ResultUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class Controller {
    @Autowired
    private StudentRepository repository;

    @GetMapping("/{id}")
    public Msg getStudent(@PathVariable String id) {
        Optional<Student> student = repository.findById(id);
        if (student.isPresent()) {
            return ResultUtil.success(student);
        } else {
            return ResultUtil.notFount();
        }
    }

    @GetMapping
    public Msg getStudents(@RequestParam(defaultValue = "") String sort, @RequestParam(defaultValue = "0") Boolean desc) {
        List<Student> res = repository.findAll();
        if (sort.equals("id")) {
            if (desc) res.sort((Student o1, Student o2) -> o2.getId().compareTo(o1.getId()));
            else res.sort(Comparator.comparing(Student::getId));
        } else if (sort.equals("name")) {
            if (desc) res.sort((Student o1, Student o2) -> o2.getName().compareTo(o1.getName()));
            else res.sort(Comparator.comparing(Student::getName));
        } else if (sort.equals("age")) {
            if (desc) res.sort((Student o1, Student o2) -> o2.getAge() - o1.getAge());
            else res.sort(Comparator.comparing(Student::getAge));
        } else if (sort.equals("score")) {
            if (desc) res.sort((Student o1, Student o2) -> o2.getScore() - o1.getScore());
            else res.sort(Comparator.comparing(Student::getScore));
        }
        return ResultUtil.success(res);
    }

    @PostMapping
    public Msg creatStudent(@RequestBody Student student) {
        student.toDefault();
        repository.save(student);
        return ResultUtil.success(student);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Msg updateStudent(@PathVariable String id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return ResultUtil.success("删除成功");
        } else {
            return ResultUtil.error(404, "不存在该记录");
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public Msg deleteStudent(@PathVariable String id, @RequestBody Student student) {
        Optional<Student> res = repository.findById(id);
        student.setId(id);
        if (!res.isPresent()) {
            return ResultUtil.error(404, "id not found");
        } else {
            Student stu = res.get().extent(student);
            if (res.get().equals(stu)) {
                return ResultUtil.success("unnessesary to update", stu);
            } else {
                repository.save(stu);
                return ResultUtil.success("update success ", stu);
            }
        }
    }

}
