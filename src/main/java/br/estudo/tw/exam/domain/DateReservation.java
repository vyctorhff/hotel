package br.estudo.tw.exam.domain;

/**
 * Created by torugo on 30/08/15.
 */
public class DateReservation {
    private int day;
    private int year;
    private MothEnum moth;
    private WeekDayEnum weekDay;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MothEnum getMoth() {
        return moth;
    }

    public void setMoth(MothEnum moth) {
        this.moth = moth;
    }

    public WeekDayEnum getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDayEnum weekDay) {
        this.weekDay = weekDay;
    }
}
