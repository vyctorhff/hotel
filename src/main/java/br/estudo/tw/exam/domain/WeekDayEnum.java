package br.estudo.tw.exam.domain;

/**
 * Created by torugo on 30/08/15.
 */
public enum WeekDayEnum {
    MONDAY("mon"),
    TUESDAY("tues"),
    WEDNESDAY("wed"),
    THURSDAY("thur"),
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

    public boolean isWeekEnd() {
        return WEEKEND.equals(this)
                || SATURDAY.equals(this)
                || SUNDAY.equals(this);
    }

    public boolean isWeekDay() {
        return !isWeekEnd();
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
