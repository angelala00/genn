package com.cjteam.xiao.util;

import com.google.common.collect.ImmutableList;
import com.cjteam.xiao.model.User;
import org.apache.commons.lang3.StringUtils;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by ChenLong on 14-3-3.
 */
public class ToolKits {

    public static Object ranDomSelect(List<?> source, Random sourceRandom) {
        return source.get(Math.abs(sourceRandom.nextInt() % source.size()));
    }

    public final static List<Integer> sleepMinutes = ImmutableList.of(1, 2, 3, 4, 7, 9, 10, 20, 23, 13, 25, 12, 18, 14, 11, 22, 16, 19);
    public final static Random sleepMinutesRandom = new Random(sleepMinutes.size() + System.currentTimeMillis());
    public final static List<Integer> sleepSeconds = ImmutableList.of(1, 2, 3, 4, 7, 9, 10, 20, 23, 13, 30, 25, 43, 20, 30, 49, 59, 26, 12, 43, 53, 23, 41, 43, 24, 47, 26, 8, 9, 17, 43, 16, 29, 38, 37, 33, 1, 34, 23, 43, 56);
    public final static Random sleepSecondsRandom = new Random(sleepMinutes.size() + System.currentTimeMillis());
    public final static List<Integer> hours = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
    public final static Random hoursRandom = new Random(hours.size() + System.currentTimeMillis());
    public final static List<Integer> minutes = ImmutableList.of(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
    public final static Random minutesRandom = new Random(minutes.size() + System.currentTimeMillis());
    public final static List<Integer> seconds = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
    public final static Random secondsRandom = new Random(seconds.size() + System.currentTimeMillis());
    static List<String> chars = ImmutableList.of("a", "b", "g", "v", "w", "h", "i", "j", "k", "n", "o", "p", "c", "d", "e", "f", "q", "r", "l", "m", "s", "t", "u", "x", "y", "z",
            "c", "u", "v", "d", "e", "f", "g", "k", "l", "m", "a", "b", "n", "o", "p", "q", "r", "s", "t", "w", "h", "i", "j", "x", "y", "z",
            "0", "2", "5", "1", "9", "6", "7", "3", "4", "8");
    static final Random charsRandom = new Random(chars.size() + System.currentTimeMillis());

    public static String randomString(int length) {
        int count = 0;
        StringWriter stringWriter = new StringWriter();
        while (count < length) {
            stringWriter.write((String) ranDomSelect(chars, charsRandom));
            count++;
        }
        return stringWriter.toString();
    }

    public static User randomParamToCreateUser() {
        User user = new User();
        user.setMac(randomMac());
        user.setToken(randomToken());
        user.setOpenUdid(randomOpenUdid());
        return user;
    }

    private static String randomOpenUdid() {
        return randomString(40);
    }

    private static String randomToken() {
        return randomString(64);
    }

    private static String randomMac() {
        StringBuilder sb = new StringBuilder();
        sb.append(randomString(8)).append("-");
        sb.append(randomString(4)).append("-");
        sb.append(randomString(4)).append("-");
        sb.append(randomString(4)).append("-");
        sb.append(randomString(12));
        return StringUtils.upperCase(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(randomString(5));
    }
}
