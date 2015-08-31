package br.estudo.tw.exam.domain;

/**
 * Created by torugo on 30/08/15.
 */
public enum WeekDayEnum {
    MONDAY("mon");

    private final String day;

    WeekDayEnum(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public static WeekDayEnum getWeekDayByString(String strReservation) {
        for (WeekDayEnum weekDay : WeekDayEnum.values()) {
            if (weekDay.getDay().equalsIgnoreCase(strReservation)) {
                return weekDay;
            }
        }
        return null;
    }
}
