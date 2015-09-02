package br.estudo.tw.exam.domain;

import com.google.common.base.MoreObjects;

/**
 * Created by torugo on 01/09/15.
 */
public class DayRate {
    private double rate;
    private CustomerTypeEnum type;
    private WeekDayEnum weekDay;

    public DayRate() {
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public WeekDayEnum getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDayEnum weekDay) {
        this.weekDay = weekDay;
    }

    public CustomerTypeEnum getType() {
        return type;
    }

    public void setType(CustomerTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(DayRate.class)
                .add("rate", rate)
                .add("type", type)
                .add("weekDay", weekDay)
                .toString();
    }
}
