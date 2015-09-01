package br.estudo.tw.exam.tests;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

/**
 * Created by n5dc on 29/04/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-spring.xml"})
public class AbstractTests {

    protected final String FILE_INPUT_1 =
            "/inputs_files/example_input1.txt";

    protected final String FILE_INPUT_2 =
            "/inputs_files/example_input2.txt";

    protected final String FILE_INPUT_3 =
            "/inputs_files/example_input3.txt";

    protected final String FILE_CUSTOMER_REGULAR =
            "/inputs_files/example_input_regular.txt";

    protected final String FILE_CUSTOMER_REWARDS =
            "/inputs_files/example_input_reward.txt";

    protected static final String FILE_INPUT_WEEK_DAYS =
            "/inputs_files/example_input_week_days.txt";

    protected static final String FILE_INPUT_MONTHS =
            "/inputs_files/example_input_months.txt";

    protected String loadInputFromFile(String file) throws IOException {
        InputStream resourceAsStream = Class.class.getResourceAsStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));

        return bufferedReader.readLine();
    }
}
