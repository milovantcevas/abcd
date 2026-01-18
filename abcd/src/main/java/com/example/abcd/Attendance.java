package com.example.abcd;

import jakarta.persistence.*;

@Entity
@Table(name = "attendances")
class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String status;

    public Attendance() {
    }

    public Attendance(Long id, Event event, Member member, String status) {
        this.id = id;
        this.event = event;
        this.member = member;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
