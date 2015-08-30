package br.estudo.tw.exam.input.imp;

import br.estudo.tw.exam.domain.Customer;
import br.estudo.tw.exam.domain.CustomerTypeEnum;
import br.estudo.tw.exam.input.HotelInputException;
import br.estudo.tw.exam.input.HotelInputReader;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by n5dc on 29/04/2015.
 */
@Component
public class BasicInputReader implements HotelInputReader {

    private String customer;
    private List<String> dates;

    @Override
    public void read(String input) throws HotelInputException {
        if (StringUtils.isEmpty(input) || StringUtils.isBlank(input)) {
            throw new HotelInputException(ENTRADA_INVALIDA);
        }

        readCustomer(input);
        readDates(input);
    }

    private void readCustomer(String input) throws HotelInputException {
        for (CustomerTypeEnum customerTypeEnum : CustomerTypeEnum.values()) {
            String description = customerTypeEnum.getDesction().toLowerCase();

            if (input.toLowerCase().contains(description)) {
                customer = customerTypeEnum.getDesction();
            }
        }

        if (Objects.isNull(customer)) {
            throw new HotelInputException(MSG_ERROR_CUSTOMER_TYPE);
        }
    }

    private void readDates(String input) {
    }

    @Override
    public String getCustomerType() {
        return customer;
    }

    @Override
    public List<String> getDates() {
        return dates;
    }
}
