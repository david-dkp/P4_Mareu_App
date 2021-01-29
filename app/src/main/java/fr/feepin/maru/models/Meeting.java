package fr.feepin.maru.models;

import java.util.List;

public class Meeting {
    private int id;
    private long startingTime;
    private String place;
    private String subject;
    private List<String> participantsEmail;

    public Meeting(int id, long startingTime, String place, String subject, List<String> participantsEmail) {
        this.startingTime = startingTime;
        this.place = place;
        this.subject = subject;
        this.participantsEmail = participantsEmail;
        this.id = id;
    }

    public long getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(long startingTime) {
        this.startingTime = startingTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getParticipantsEmail() {
        return participantsEmail;
    }

    public void setParticipantsEmail(List<String> participantsEmail) {
        this.participantsEmail = participantsEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
