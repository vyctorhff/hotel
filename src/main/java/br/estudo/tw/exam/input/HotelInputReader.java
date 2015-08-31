package br.estudo.tw.exam.input;

import br.estudo.tw.exam.domain.Customer;

import java.util.List;

/**
 * Created by n5dc on 29/04/2015.
 */
public interface HotelInputReader {

    String ENTRADA_INVALIDA =
            "input.invalid_input";

    String MSG_ERROR_CUSTOMER_TYPE =
            "input.invalid_customer_type";

    void read(String input) throws HotelInputException;

    Customer getCustomer();

}
