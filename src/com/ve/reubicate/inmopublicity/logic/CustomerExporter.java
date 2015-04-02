/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ve.reubicate.inmopublicity.logic;

import com.ve.reubicate.inmopublicity.model.Customer;
import com.ve.reubicate.inmopublicity.utils.ExlFileCreator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis
 */
public class CustomerExporter {

    //Here we do all the process of getting the customers
    //from the different web pages and then we put them
    //all in a new collection to use it to create
    //the excel file with all the phonesnumbers and 
    //data related to the user 

    public void export() {
        List<Strategy> sources = new ArrayList();
        sources.add(new TuInmuebleStrategy());
        List<Customer> finalCustomers = new ArrayList();
        for (Strategy source : sources) {
            finalCustomers.addAll(source.retrieveCustomers());
        }

        ExlFileCreator excelFile = new ExlFileCreator();
        //second one is to put the messague in the excel file.
        excelFile.createExcel(finalCustomers, "http://www.reubicate.com.ve", "58");
    }
}
