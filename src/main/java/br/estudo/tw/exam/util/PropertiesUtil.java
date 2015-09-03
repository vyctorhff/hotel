package br.estudo.tw.exam.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by torugo on 30/08/15.
 */
public class PropertiesUtil {

    private static final String BUSSINESS_PROP =
            "/bussiness-message.properties";

    private static final LogUtil LOGGER =
            LogUtil.getLogger(PropertiesUtil.class);

    private static Properties PROPERTIES;

    public static String getMessage(String key) {
        load();
        return PROPERTIES.getProperty(key);
    }

    private static void load() {
        if (Objects.isNull(PROPERTIES)) {
            PROPERTIES = getInstance(BUSSINESS_PROP);
        }
    }

    private static Properties getInstance(String propPath) {
        Properties properties = new Properties();
        try {
            properties.load(
                    Class.class.getResourceAsStream(propPath));

            LOGGER.info("properties load!");
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return properties;
    }
}
