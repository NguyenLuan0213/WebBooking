/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;

import com.web.pojo.Coachstripcoachseat;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class CSCSFormatter implements Formatter<Coachstripcoachseat> {

    @Override
    public String print(Coachstripcoachseat object, Locale locale) {
        return object.toString();
    }

    @Override
    public Coachstripcoachseat parse(String text, Locale locale) throws ParseException {
        return new Coachstripcoachseat(Integer.valueOf(text));
    }

}
