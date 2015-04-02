/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ve.reubicate.inmopublicity.utils;

/**
 *
 * @author Luis
 */
public class PhoneSanitizer {

    public static String sanitize(String phoneNumber) {
        String sanitizedPhone = phoneNumber.replaceFirst("0", "")
                .replace("-", "")
                .replace(".", "")
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("+58", "");
                
        return sanitizedPhone;
    }
}
