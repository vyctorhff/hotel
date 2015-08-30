package br.estudo.tw.exam.domain;

/**
 * Created by torugo on 29/04/2015.
 */
public enum CustomerTypeEnum {
    REGULAR("Regular"), REWARDS("Rewards");

    private String name;

    CustomerTypeEnum(String name) {
        this.name = name;
    }

    public String getDesction() {
        return name;
    }

}
