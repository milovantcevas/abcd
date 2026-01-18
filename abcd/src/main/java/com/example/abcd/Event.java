package com.example.abcd;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime datetime;
    private String title;
    private String description;

    public Event() {
    }

    public Event(Long id, LocalDateTime datetime, String title, String description, Club club) {
        this.id = id;
        this.datetime = datetime;
        this.title = title;
        this.description = description;
        this.club = club;
    }

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDatetime() { return datetime; }
    public void setDatetime(LocalDateTime datetime) { this.datetime = datetime; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }
}
