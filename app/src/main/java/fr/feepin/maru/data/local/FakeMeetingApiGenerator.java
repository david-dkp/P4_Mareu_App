package fr.feepin.maru.data.local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.utils.DateUtils;

public class FakeMeetingApiGenerator {

    public static String[] fakeEmails = {
            "jeanmichel@gmail.com",
            "marie.venden@gmail.com",
            "david.dekeuwer@gmail.com",
            "allan78marcher@yahoo.com",
            "brice2nice@gmail.com",
            "mayalabeille@gmail.com",
            "anderson.bruno@hotmail.com",
            "vald755@gmail.com",
            "valores.miamor@jsp.com",
            "manque.inspiration@bienvenue.com",
            "lafete.cestmaintenant@lafete.mtn",
            "ceciestuneadresse@adress.com",
            "manger.bouger@yahoo.com"
    };

    public static ArrayList<Meeting> generateMeetings() {
        return new ArrayList<Meeting>(Arrays.asList(fakeMeetings));
    }

    public static List<String> generateRandomParticipants(int numberOfParticipants) {
        ArrayList<String> emails = new ArrayList<>();

        while (emails.size() != numberOfParticipants) {
            String randomEmail = fakeEmails[new Random().nextInt(fakeEmails.length)];
            if (emails.contains(randomEmail)) continue;
            emails.add(randomEmail);
        }

        return emails;
    }

    public static Meeting[] fakeMeetings = {
            new Meeting(1, DateUtils.getDateMillisFromTime(17, 45), Room.LUIGI, "Stocks", generateRandomParticipants(6)),
            new Meeting(2, DateUtils.getDateMillisFromTime(8, 00), Room.WARIO, "Daily", generateRandomParticipants(2)),
            new Meeting(3, DateUtils.getDateMillisFromTime(14, 20), Room.YOSHI, "Etat des lieux", generateRandomParticipants(3)),
            new Meeting(4, DateUtils.getDateMillisFromTime(9, 30), Room.MARIO, "Marketing", generateRandomParticipants(4)),
            new Meeting(5, DateUtils.getDateMillisFromTime(16, 50), Room.PEACH, "Projet 23", generateRandomParticipants(2)),
            new Meeting(6, DateUtils.getDateMillisFromTime(8, 30), Room.TOAD, "Briefing", generateRandomParticipants(8)),
            new Meeting(7, DateUtils.getDateMillisFromTime(15, 00), Room.WALUIGI, "Brainstorming", generateRandomParticipants(6)),
            new Meeting(8, DateUtils.getDateMillisFromTime(19, 20), Room.DK, "Review", generateRandomParticipants(2)),
    };

}
