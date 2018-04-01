package com.jackeryjam.proglang.exp1.student;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private Integer age;
    private Boolean sex;
    private Integer grade;
    private Integer score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getAge() == student.getAge() &&
                isSex() == student.isSex() &&
                getGrade() == student.getGrade() &&
                getScore() == student.getScore() &&
                Objects.equals(getId(), student.getId()) &&
                Objects.equals(getName(), student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), isSex(), getGrade(), getScore());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", grade=" + grade +
                ", score=" + score +
                '}';
    }

    public void toDefault(){
        name = name == null ? "" : name;
        age = age == null ? 0 : age;
        sex = sex == null ? true : false;
        grade = grade == null ? 0 : grade;
        score = score == null ? 0 : score;
    }

    public Student extent(Student student){
        Student extendStudent = new Student();
        extendStudent.setId(this.id);
        extendStudent.name = student.name == null ? this.name : student.name;
        extendStudent.age = student.age == null ? this.age : student.age;
        extendStudent.sex = student.sex == null ? this.sex : student.sex;
        extendStudent.grade = student.grade == null ? this.grade : student.grade;
        extendStudent.score = student.score == null ? this.score : student.score;
        return extendStudent;
    }
}
