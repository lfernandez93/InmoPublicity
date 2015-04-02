/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ve.reubicate.inmopublicity.logic;

import com.ve.reubicate.inmopublicity.model.Customer;
import java.util.List;

/**
 *
 * @author Luis
 */
public interface Strategy {
    public List<Customer> retrieveCustomers();
}
