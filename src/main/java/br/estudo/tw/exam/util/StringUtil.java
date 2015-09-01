package br.estudo.tw.exam.util;

import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by torugo on 31/08/15.
 */
public class StringUtil {
    public static String removeParentheses(String string) {
        if (Objects.isNull(string)
                || StringUtils.isEmpty(string)
                || StringUtils.isBlank(string)) {
            throw new IllegalArgumentException("string invalid");
        }

        if (!string.contains("(")
                || !string.contains(")")) {
            throw new IllegalArgumentException("string has no parentheses");
        }

        return string.replace("(", "").replace(")", "");
    }

    public static String getStringByExpression(String expression, String input) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            int indexStart = matcher.start();
            int indexEnd   = matcher.end();

            return input.substring(indexStart, indexEnd);
        }
        return null;
    }
}
