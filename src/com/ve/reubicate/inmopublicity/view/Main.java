/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ve.reubicate.inmopublicity.view;

import com.ve.reubicate.inmopublicity.logic.CustomerExporter;

/**
 *
 * @author Luis
 */
public class Main {

    public static void main(String[] args) {
        CustomerExporter customExp = new CustomerExporter();
        customExp.export();
    }
}
