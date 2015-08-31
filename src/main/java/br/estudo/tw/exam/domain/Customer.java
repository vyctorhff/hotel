package br.estudo.tw.exam.domain;

import java.util.List;

/**
 * Created by torugo on 29/04/2015.
 */
public class Customer {

    private CustomerTypeEnum type;
    private List<DateReservation> reservations;

    public CustomerTypeEnum getType() {
        return type;
    }

    public void setType(CustomerTypeEnum type) {
        this.type = type;
    }

    public List<DateReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<DateReservation> reservations) {
        this.reservations = reservations;
    }
}
