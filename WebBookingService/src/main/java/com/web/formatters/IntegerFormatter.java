/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;


/**
 *
 * @author Admin
 */
public class IntegerFormatter implements Formatter<Integer> {
    @Override
    public Integer parse(String text, Locale locale) throws ParseException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String print(Integer object, Locale locale) {
        return (object != null) ? object.toString() : "";
    }
}
