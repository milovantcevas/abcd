package com.example.abcd;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String className;
    private String email;

    public Member() {
    }

    public Member(Long id, String name, String className, String email) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

