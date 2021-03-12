package fr.feepin.maru.data.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.utils.DateUtil;

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
            new Meeting(1, DateUtil.getDateMillisFromTime(17, 45), Room.LUIGI, "Stocks", generateRandomParticipants(6)),
            new Meeting(2, DateUtil.getDateMillisFromTime(8, 00), Room.WARIO, "Daily", generateRandomParticipants(2)),
            new Meeting(3, DateUtil.getDateMillisFromTime(14, 20), Room.YOSHI, "Etat des lieux", generateRandomParticipants(3)),
            new Meeting(4, DateUtil.getDateMillisFromTime(9, 30), Room.MARIO, "Marketing", generateRandomParticipants(4)),
            new Meeting(5, DateUtil.getDateMillisFromTime(16, 50), Room.PEACH, "Projet 23", generateRandomParticipants(2)),
            new Meeting(6, DateUtil.getDateMillisFromTime(8, 30), Room.TOAD, "Briefing", generateRandomParticipants(8)),
            new Meeting(7, DateUtil.getDateMillisFromTime(15, 00), Room.WALUIGI, "Brainstorming", generateRandomParticipants(6)),
            new Meeting(8, DateUtil.getDateMillisFromTime(19, 20), Room.DK, "Review", generateRandomParticipants(2)),
            new Meeting(9, DateUtil.getDateMillisFromTime(7, 45), Room.LUIGI, "Daily", generateRandomParticipants(1)),
            new Meeting(10, DateUtil.getDateMillisFromTime(19, 45), Room.MARIO, "Recap", generateRandomParticipants(4)),
            new Meeting(11, DateUtil.getDateMillisFromTime(10, 20), Room.DAISY, "Design", generateRandomParticipants(3)),
            new Meeting(12, DateUtil.getDateMillisFromTime(11, 25), Room.BROWSER, "CodeReview", generateRandomParticipants(6)),
            new Meeting(13, DateUtil.getDateMillisFromTime(16, 20), Room.WALUIGI, "Code Architecture", generateRandomParticipants(8)),
            new Meeting(14, DateUtil.getDateMillisFromTime(15, 15), Room.ROSALINA, "Design", generateRandomParticipants(3)),

    };

}
