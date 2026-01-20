package student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id){
        if(!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book newBook){
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        newBook.setId(id);
        Book updated = bookRepository.save(newBook);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id){
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> getByTitle(@RequestParam(required = false) String title){
        if(title == null || title.trim().isEmpty()){
            return ResponseEntity.ok(bookRepository.findAll());
        }
        return ResponseEntity.ok(bookRepository.findByTitle(title.trim()));
    }
}
