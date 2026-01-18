package com.example.abcd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        // Проверить существование кружка
        Optional<Club> club = clubRepository.findById(event.getClub().getId());
        if (!club.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Клуб не найден");
        }
        event.setClub(club.get());

        Event saved = eventRepository.save(event);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(
            @RequestParam(required = false) Long clubId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Event> events;

        if (clubId != null && date != null) {
            List<Event> byClub = eventRepository.findByClubId(clubId);
            events = new ArrayList<>();

            for (Event e : byClub) {
                if (e.getDatetime().toLocalDate().equals(date)) {
                    events.add(e);
                }
            }
        } else if (clubId != null) {
            events = eventRepository.findByClubId(clubId);
        } else if (date != null) {
            events = eventRepository.findByDate(date);
        } else {
            events = eventRepository.findAll();
        }

        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event newEvent) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Club> club = clubRepository.findById(newEvent.getClub().getId());
        if (!club.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Клуб не найден");
        }

        newEvent.setId(id);
        newEvent.setClub(club.get());
        Event updated = eventRepository.save(newEvent);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(id);
        return ResponseEntity.ok("Событие удалено");
    }

    // Пригласить участника
    @PostMapping("/{eventId}/invite")
    public ResponseEntity<String> inviteMember(
            @PathVariable Long eventId,
            @RequestParam Long memberId) {

        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (!eventOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Событие не найдено");
        }

        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (!memberOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Участник не найден");
        }

        Attendance existing = attendanceRepository.findByEventIdAndMemberId(eventId, memberId);
        if (existing != null) {
            return ResponseEntity.ok("Участник уже приглашен");
        }

        Attendance attendance = new Attendance();
        attendance.setEvent(eventOpt.get());
        attendance.setMember(memberOpt.get());
        attendance.setStatus("не приглашён");

        attendanceRepository.save(attendance);
        return ResponseEntity.ok("Участник приглашен");
    }

    @GetMapping("/{eventId}/attendance")
    public ResponseEntity<List<Attendance>> getEventAttendance(@PathVariable Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Событие не найдено");
        }

        List<Attendance> attendances = attendanceRepository.findByEventId(eventId);
        return ResponseEntity.ok(attendances);
    }

    private boolean isValidStatus(String status) {
        return status.equals("присутствует") ||
                status.equals("отсутствует") ||
                status.equals("опоздал") ||
                status.equals("не приглашён");
    }
}
