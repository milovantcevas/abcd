package com.example.abcd;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class Controller {
    private List<Club> clubs = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    @GetMapping("/clubs")
    public ResponseEntity<List<Club>> readClubs(){
        return ResponseEntity.ok(clubs);
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> readMembers(){
        return ResponseEntity.ok(members);
    }

    @PostMapping("/clubs")
    public ResponseEntity<Void> createClub(Club club){
        Club club1 = new Club(club.getId(), club.getTitle(), club.getDescription());
        clubs.add(club1);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/members")
    public ResponseEntity<Void> createMember(Member member){
        members.add(member);
        return ResponseEntity.ok().build();
    }
}
