/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;

import com.web.pojo.Coachstrips;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class CoachStripsFormatter implements Formatter<Coachstrips> {

    @Override
    public String print(Coachstrips object, Locale locale) {
        return object.toString();
    }

    @Override
    public Coachstrips parse(String text, Locale locale) throws ParseException {
        return new Coachstrips(Integer.valueOf(text));
    }

}
