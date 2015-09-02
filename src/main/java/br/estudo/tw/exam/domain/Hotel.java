package br.estudo.tw.exam.domain;

import com.google.common.base.MoreObjects;

/**
 * Created by torugo on 01/09/15.
 */
public class Hotel {
    private int rating;
    private String name;
    private CustomerRate customerRate;

    public Hotel() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerRate getCustomerRate() {
        return customerRate;
    }

    public void setCustomerRate(CustomerRate customerRate) {
        this.customerRate = customerRate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Hotel.class)
                .add("name", name)
                .add("rating", rating)
                .add("customerRate", customerRate)
                .toString();
    }
}
