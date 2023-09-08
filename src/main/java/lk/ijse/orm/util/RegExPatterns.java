package lk.ijse.orm.util;

import java.util.regex.Pattern;

public class RegExPatterns {
    private static final Pattern namePattern = Pattern.compile("^[a-zA-Z '.-]{4,}$");
    private static final Pattern emailPattern = Pattern.compile("(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])(com)$");
    private static final Pattern doublePattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");
    private static final Pattern intPattern = Pattern.compile("^[1-9]\\d*$");
    private static final Pattern contactPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
    private static final Pattern studentIdPattern = Pattern.compile("^S\\d{3,}$");
    private static final Pattern roomIdPattern = Pattern.compile("^RM-\\d{4,}$");
    private static final Pattern reserveIdPattern = Pattern.compile("^R\\d{3,}$");
    private static final Pattern addressPattern = Pattern.compile("^[A-Za-z0-9'\\/\\.\\,]{5,}$");

    public static Pattern getNamePattern() {
        return namePattern;
    }

    public static Pattern getAddressPattern() { return addressPattern; }

    public static Pattern getDoublePattern() {
        return doublePattern;
    }

    public static Pattern getEmailPattern() {
        return emailPattern;
    }

    public static Pattern getIntPattern() {
        return intPattern;
    }

    public static Pattern getStudentIdPattern() {
        return studentIdPattern;
    }

    public static Pattern getRoomIdPattern() { return roomIdPattern; }

    public static Pattern getReserveIdPattern() { return reserveIdPattern; }

    public static Pattern getContactPattern() {
        return contactPattern;
    }
}
