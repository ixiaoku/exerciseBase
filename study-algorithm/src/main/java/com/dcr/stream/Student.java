package com.dcr.stream;

import lombok.Data;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/4/25 17:15
 */
@Data
public class Student {

    private Integer no;

    private String name;

    private Integer sort;

    private Integer age;

    private String major;

    private String school;

    public Student(Integer no, String name, Integer sort, Integer age, String major, String school) {
        this.no = no;
        this.name = name;
        this.sort = sort;
        this.age = age;
        this.major = major;
        this.school = school;
    }
}
