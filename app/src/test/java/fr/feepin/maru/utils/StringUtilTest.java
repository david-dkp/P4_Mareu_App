package fr.feepin.maru.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class StringUtilTest {

    @Test
    public void joinListOfStringWithSeparator_withSuccess() {
        List<String> strings = Arrays.asList("Mario", "Luigi", "Maison", "Soleil", "Bonheur");

        String joinedList = StringUtil.join("-", strings);

        Assert.assertEquals("Mario-Luigi-Maison-Soleil-Bonheur", joinedList);
    }
}
