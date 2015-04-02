/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ve.reubicate.inmopublicity.utils;

import com.ve.reubicate.inmopublicity.model.Customer;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Luis
 */
public class ExlFileCreator {

    public void createExcel(List<Customer> customers, String message, String countryCode) {
        try {
            String filename = "Customers.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Customers");
            int count = 0;
            for (Customer customer : customers) {

                for (String phoneNumber : customer.getPhoneNumbers()) {
                    HSSFRow row = sheet.createRow(count);
                    row.createCell(0).setCellValue(phoneNumber);
                    row.createCell(1).setCellValue(phoneNumber);
                    row.createCell(2).setCellValue(customer.getEmail());
                    row.createCell(3).setCellValue(countryCode);
                    row.createCell(4).setCellValue(message);
                }
                count++;

            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
}
