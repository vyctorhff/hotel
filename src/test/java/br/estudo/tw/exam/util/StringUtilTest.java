package br.estudo.tw.exam.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by torugo on 31/08/15.
 */
public class StringUtilTest {

    @Test
    public void testRemoveParentheses() throws Exception {
        String input = "(abc)";
        String strModified = StringUtil.removeParentheses(input);

        assertNotNull(strModified);
        assertFalse(strModified.contains("("));
        assertFalse(strModified.contains(")"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveParenthesesWithNotParentheses() throws Exception {
        StringUtil.removeParentheses("abc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveParenthesesWithOneParentheses() throws Exception {
        StringUtil.removeParentheses("abc)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveParenthesesWithNullInput() throws Exception {
        StringUtil.removeParentheses(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveParenthesesWithBlackInput() throws Exception {
        StringUtil.removeParentheses(" ");
    }
}