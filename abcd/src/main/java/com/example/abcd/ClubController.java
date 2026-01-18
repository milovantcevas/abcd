package com.example.abcd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clubs")
class ClubController {

    @Autowired
    private ClubRepository clubRepository;

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club saved = clubRepository.save(club);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return ResponseEntity.ok(clubs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClub(@PathVariable Long id) {
        Optional<Club> club = clubRepository.findById(id);
        if (club.isPresent()) {
            return ResponseEntity.ok(club.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club newClub) {
        if (!clubRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        newClub.setId(id);
        Club updated = clubRepository.save(newClub);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Long id) {
        if (!clubRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clubRepository.deleteById(id);
        return ResponseEntity.ok("Кружок удален");
    }
}
