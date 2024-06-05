package com.driver;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {


    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        // your code goes here
        studentMap.put(student.getName(), student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            teacherStudentMapping.putIfAbsent(teacher, new ArrayList<>());
            teacherStudentMapping.get(teacher).add(student);
            teacherMap.get(teacher).setNumberOfStudents(teacherStudentMapping.get(teacher).size());
        }
    }

    public Student findStudent(String student){

        return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){

        return teacherMap.get(teacher);
    }

    public List<String> findStudentsFromTeacher(String teacher){

        return teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
    }

    public List<String> findAllStudents(){

        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){
        // your code goes here

        if (teacherStudentMapping.containsKey(teacher)) {
            List<String> students = teacherStudentMapping.get(teacher);
            for (String student : students) {
                studentMap.remove(student);
            }
            teacherStudentMapping.remove(teacher);
        }
        teacherMap.remove(teacher);

    }

    public void deleteAllTeachers(){
        // your code goes here

        for (String teacher : teacherMap.keySet()) {
            if (teacherStudentMapping.containsKey(teacher)) {
                List<String> students = teacherStudentMapping.get(teacher);
                for (String student : students) {
                    studentMap.remove(student);
                }
                teacherStudentMapping.remove(teacher);
            }
        }
        teacherMap.clear();

    }
}