/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;

import com.web.pojo.Transporttruck;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class TransFormatter implements Formatter<Transporttruck> {

    @Override
    public String print(Transporttruck object, Locale locale) {
        return object.toString();
    }

    @Override
    public Transporttruck parse(String text, Locale locale) throws ParseException {
        return new Transporttruck(Integer.valueOf(text));
    }

}
