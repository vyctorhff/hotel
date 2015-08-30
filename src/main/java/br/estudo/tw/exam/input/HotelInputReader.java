package br.estudo.tw.exam.input;

import java.util.List;

/**
 * Created by n5dc on 29/04/2015.
 */
public interface HotelInputReader {

    void read(String input) throws HotelInputException;

    String getCustomerType();

    List<String> getDates();

}
