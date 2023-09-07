/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;

import com.web.pojo.Garage;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class GarageFormatter implements Formatter<Garage>{

    @Override
    public String print(Garage object, Locale locale) {
        return object.toString();
    }

    @Override
    public Garage parse(String text, Locale locale) throws ParseException {
        return new Garage(Integer.valueOf(text));
    }
    
}
