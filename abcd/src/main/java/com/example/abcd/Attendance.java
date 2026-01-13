package com.example.abcd;

public class Attendance {
    private int eventId;
    private int memberId;
    private String status;

    public Attendance(){}

    public Attendance(int eventId, int memberId, String status) {
        this.eventId = eventId;
        this.memberId = memberId;
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
