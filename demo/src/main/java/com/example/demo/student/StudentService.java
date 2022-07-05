package com.example.demo.student;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){

        return studentRepository.findAll();

    }

    public void addNewStudent(Student s) {

        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(s.getEmail());
        if (studentByEmail.isPresent()){

            throw new IllegalStateException("email taken");
        }
        studentRepository.save(s);

    }

    public void deleteStudent(Long id) {

        if(studentRepository.existsById(id)){

            studentRepository.deleteById(id);
        }else{

            throw new IllegalStateException("student " + id + " not found");
        }
    }

    @Transactional
    public void updateStudent(long studentId, String email, String name) {

        Student s = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("student " + studentId + " not found"));

        if(email!=null && email.length()>0 && !Objects.equals(s.getEmail(), email)){

            Optional<Student> stEmail = studentRepository.findStudentByEmail(email);
            if(stEmail.isPresent()){

                throw new IllegalStateException("email taken");
            }
            s.setEmail(email);
        }

        if(name!=null && name.length()>0 && !Objects.equals(s.getName(), name)){

            s.setName(name);
        }
    }
}
