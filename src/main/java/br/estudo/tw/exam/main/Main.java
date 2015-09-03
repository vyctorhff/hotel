package br.estudo.tw.exam.main;

import br.estudo.tw.exam.HotelException;
import br.estudo.tw.exam.domain.Customer;
import br.estudo.tw.exam.domain.Hotel;
import br.estudo.tw.exam.input.CustomerAdapter;
import br.estudo.tw.exam.input.HotelInputReader;
import br.estudo.tw.exam.service.HotelSelectService;
import br.estudo.tw.exam.util.LogUtil;
import br.estudo.tw.exam.util.PropertiesUtil;
import br.estudo.tw.exam.util.SpringApplication;
import com.google.common.base.Joiner;

import java.util.Objects;

/**
 * Created by torugo on 02/09/15.
 */
public class Main {
    private static final LogUtil LOGGER =
            LogUtil.getLogger(Main.class);

    private static final String INPUT_PARAMETER_NOT_SET =
            "main.parameter_not_set";

    public static void main(String args[]) {
        if (Objects.isNull(args) || args.length == 0) {
            info(INPUT_PARAMETER_NOT_SET);
            return;
        }

        String input = Joiner.on("").join(args);

        LOGGER.info("Input informed");
        LOGGER.info(input);

        HotelInputReader hotelInputReader = SpringApplication.getBean(HotelInputReader.class);
        HotelSelectService hotelSelectService = SpringApplication.getBean(HotelSelectService.class);

        try {
            hotelInputReader.read(input);
            CustomerAdapter adapter = new CustomerAdapter(hotelInputReader);
            adapter.doAdapter();

            Customer customer = adapter.getCustomer();
            Hotel hotel = hotelSelectService.selectCheapest(customer);

            LOGGER.info(hotel.getName());

        } catch (HotelException e) {
            error(e);

        } catch (Exception e) {
            error(e);
        }
    }

    private static void info(String string) {
        String msg = getMessage(string);

        if (Objects.isNull(msg)) {
            LOGGER.info(string);
        } else {
            LOGGER.info(msg);
        }
    }

    private static void error(Exception e) {
        String msg = getMessage(e.getMessage());

        if (Objects.isNull(msg)) {
            LOGGER.error(e);
        } else {
            LOGGER.error(msg);
        }
    }

    private static String getMessage(String string) {
        try {
            return PropertiesUtil.getMessage(string);
        } catch (Exception e) {
            return null;
        }
    }
}
