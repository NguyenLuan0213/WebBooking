/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;

import com.web.pojo.Staff;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class StaffFormatter implements Formatter<Staff> {

    @Override
    public String print(Staff object, Locale locale) {
        return object.toString();
    }

    @Override
    public Staff parse(String text, Locale locale) throws ParseException {
        return new Staff(Integer.valueOf(text));
    }

}
