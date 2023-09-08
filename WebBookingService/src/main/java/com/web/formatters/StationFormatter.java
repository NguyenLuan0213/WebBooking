/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;

import com.web.pojo.Stations;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class StationFormatter implements Formatter<Stations> {

    @Override
    public String print(Stations object, Locale locale) {
        return object.toString();
    }

    @Override
    public Stations parse(String text, Locale locale) throws ParseException {
        return new Stations(Integer.valueOf(text));
    }

}
