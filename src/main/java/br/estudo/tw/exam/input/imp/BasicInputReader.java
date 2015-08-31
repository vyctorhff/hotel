package br.estudo.tw.exam.input.imp;

import br.estudo.tw.exam.domain.*;
import br.estudo.tw.exam.input.HotelInputException;
import br.estudo.tw.exam.input.HotelInputReader;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by n5dc on 29/04/2015.
 */
@Component
public class BasicInputReader implements HotelInputReader {

    private static final String RESERVATION_EXPRESSION =
            "((\\d{1}|\\d{2})\\w{3}\\d{4}(\\(\\w{3}\\)|\\(\\w{4}\\)))";

    private String customerType;
    private List<String> dates;

    @Override
    public void read(String input) throws HotelInputException {
        clean();

        if (StringUtils.isEmpty(input) || StringUtils.isBlank(input)) {
            throw new HotelInputException(ENTRADA_INVALIDA);
        }

        readCustomer(input);
        readDates(input);
    }

    private void readCustomer(String input) throws HotelInputException {
        String splis[] = input.split(":");
        customerType = splis[0];
    }

    private void readDates(String input) {
        Pattern pattern = Pattern.compile(RESERVATION_EXPRESSION);
        Matcher matcher = pattern.matcher(input);

        String strReservation;
        dates = new ArrayList<String>();

        while (matcher.find()) {
            int indexStart = matcher.start();
            int indexEnd   = matcher.end();

            strReservation = input.substring(indexStart, indexEnd);
            dates.add(strReservation);
        }
    }

    @Override
    public String getCustomerType() {
        return customerType;
    }

    @Override
    public List<String> getDates() {
        return dates;
    }

    @Override
    public void clean() {
        customerType = null;
        dates = null;
    }
}
