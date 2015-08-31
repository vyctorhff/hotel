package br.estudo.tw.exam.domain;

/**
 * Created by torugo on 30/08/15.
 */
public enum MonthEnum {
    JANUARY("Jan"),
    FEBRUARY("Feb"),
    MARCH("Mar"),
    APRIL("Apr"),
    MAY("May"),
    JUNE("Jun"),
    JULY("Jul"),
    AUGUST("Aug"),
    SEPTEMBER("Sep"),
    OCTOBER("Oct"),
    NOVEMBER("Nov"),
    DECEMBER("Dec");

    private String month;

    MonthEnum(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public static MonthEnum getMothByString(String strMoth) {
        for (MonthEnum month : MonthEnum.values()) {
            if (month.getMonth().equalsIgnoreCase(strMoth)) {
                return month;
            }
        }
        return null;
    }
}
