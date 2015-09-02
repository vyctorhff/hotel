package br.estudo.tw.exam.domain;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by torugo on 01/09/15.
 */
public class CustomerRate {
    private List<DayRate> dayRates;

    public CustomerRate() {
        dayRates = new ArrayList<>();
    }

    public List<DayRate> getDayRates() {
        return dayRates;
    }

    public void setDayRates(List<DayRate> dayRates) {
        this.dayRates = dayRates;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(CustomerRate.class)
                .add("dayRates", Arrays.toString(dayRates.toArray()))
                .toString();
    }

}
