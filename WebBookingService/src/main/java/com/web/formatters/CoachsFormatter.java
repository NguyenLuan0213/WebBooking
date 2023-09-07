/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;

import com.web.pojo.Coachs;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class CoachsFormatter implements Formatter<Coachs> {

    @Override
    public String print(Coachs object, Locale locale) {
        return object.toString();
    }

    @Override
    public Coachs parse(String text, Locale locale) throws ParseException {
        return new Coachs(Integer.valueOf(text));
    }

}
