package com.example.abcd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member saved = memberRepository.save(member);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return ResponseEntity.ok(member.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member newMember) {
        if (!memberRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        newMember.setId(id);
        Member updated = memberRepository.save(newMember);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        if (!memberRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        memberRepository.deleteById(id);
        return ResponseEntity.ok("Участник удален");
    }
}
