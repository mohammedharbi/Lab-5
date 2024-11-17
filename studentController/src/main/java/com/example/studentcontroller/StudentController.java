package com.example.studentcontroller;

import com.example.studentcontroller.ApiRespnse.ApiResonse;
import com.example.studentcontroller.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/Student")
public class StudentController {

    ArrayList<Student> students = new ArrayList<>();

    @PostMapping("/add")
    public ApiResonse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResonse("add success");
    }
    @GetMapping("/get")
    public ArrayList<Student> getStudents() {
        return students;
    }
    @PutMapping("/update/{index}")
    public ApiResonse updateStudent(@PathVariable int index, @RequestBody Student student) {
        students.set(index,student);
        return new ApiResonse("Update Success");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResonse deleteStudent(@PathVariable int index) {
        students.remove(index);
        return new ApiResonse("Delete Success");
    }
    @GetMapping("/get-honors/{index}")
    public ApiResonse getHonors(@PathVariable int index) {
            if (students.get(index).getGPA() >= 4.75) {
                return new ApiResonse("First honor");
            } else if (students.get(index).getGPA() >= 4.25 && students.get(index).getGPA() < 4.75) {
                return new ApiResonse("Second honor");
            } else return new ApiResonse("Third honor");
    }
    //• Based on GPA, classify students into honors categories.
    @GetMapping("/get-avgGpa")
    public ArrayList<Student> getAvgGpa() {
        ArrayList<Student> avgHigherGpa = new ArrayList<>();
        int avgGpa = 0;
        for (Student student : students) {
            avgGpa += student.getGPA();
        }
        avgGpa /= students.size();

        for (Student student : students) {
            if (student.getGPA() > avgGpa){
                avgHigherGpa.add(student);
            }
        }
        return avgHigherGpa;
    }
    //• Display a group of students whose GPA is greater than the average
    //GPA.

}
