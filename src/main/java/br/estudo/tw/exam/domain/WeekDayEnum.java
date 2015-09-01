package br.estudo.tw.exam.domain;

/**
 * Created by torugo on 30/08/15.
 */
public enum WeekDayEnum {
    MONDAY("mon"),
    TUESDAY("tues"),
    WENDNESDAY("wen"),
    THURSDAY("thurs"),
    SATURDAY("sat"),
    SUNDAY("sun");

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
