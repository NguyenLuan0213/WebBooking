/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;

import com.web.pojo.Garage;
import com.web.pojo.Orderships;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class OrderShipsFormatter implements Formatter<Orderships> {

    @Override
    public String print(Orderships object, Locale locale) {
        return object.toString();
    }

    @Override
    public Orderships parse(String text, Locale locale) throws ParseException {
        return new Orderships(Integer.valueOf(text));
    }

}
