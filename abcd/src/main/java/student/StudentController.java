package student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudents(@PathVariable("id") Long id){
        if(!studentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return ResponseEntity.ok(student.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student newStudent){
        if(!studentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        newStudent.setId(id);
        Student updated = studentRepository.save(newStudent);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id){
        if(!studentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/students/grade/{grade}")
    public ResponseEntity<List<Student>> getStudentsByGrade(@PathVariable("grade") int grade){
        if(grade < 1 || grade > 11){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentRepository.findByGrade(grade));
    }
}
