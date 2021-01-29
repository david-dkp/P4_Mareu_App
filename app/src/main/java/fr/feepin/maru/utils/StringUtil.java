package fr.feepin.maru.utils;

import java.util.List;

public class StringUtil {
    public static String join(String separator, List<String> list) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));

            if (i != list.size()-1) {
                stringBuilder.append(separator);
            }
        }

        return stringBuilder.toString();
    }
}
