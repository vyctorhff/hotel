package br.estudo.tw.exam.domain;

/**
 * Created by torugo on 30/08/15.
 */
public enum WeekDayEnum {
    MONDAY("mon"),
    TUESDAY("tues"),
    WEDNESDAY("wed"),
    THURSDAY("thurs"),
    FRIDAY("fri"),
    SATURDAY("sat"),
    SUNDAY("sun"),
    WEEKDAY("wd"),
    WEEKEND("we");

    private final String day;

    WeekDayEnum(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public static WeekDayEnum getWeekDayByString(String strWeekDay) {
        for (WeekDayEnum weekDay : WeekDayEnum.values()) {
            if (weekDay.getDay().equalsIgnoreCase(strWeekDay)) {
                return weekDay;
            }
        }
        return null;
    }
}
