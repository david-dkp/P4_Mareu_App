package fr.feepin.maru.models;

import java.util.List;
import java.util.Objects;

public class Meeting {
    private int id;
    private long startingTime;
    private Room room;
    private String subject;
    private List<String> participantsEmail;

    public Meeting(int id, long startingTime, Room room, String subject, List<String> participantsEmail) {
        this.startingTime = startingTime;
        this.room = room;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", startingTime=" + startingTime +
                ", room=" + room +
                ", subject='" + subject + '\'' +
                ", participantsEmail=" + participantsEmail +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id &&
                startingTime == meeting.startingTime &&
                room == meeting.room &&
                Objects.equals(subject, meeting.subject) &&
                Objects.equals(participantsEmail, meeting.participantsEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startingTime, room, subject, participantsEmail);
    }
}
