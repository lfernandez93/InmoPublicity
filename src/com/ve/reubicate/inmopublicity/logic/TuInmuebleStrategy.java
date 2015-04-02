/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ve.reubicate.inmopublicity.logic;

import com.ve.reubicate.inmopublicity.config.InitialConfiguration;
import com.ve.reubicate.inmopublicity.model.Customer;
import com.ve.reubicate.inmopublicity.utils.PhoneSanitizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Luis
 */
public class TuInmuebleStrategy implements Strategy {

    private String url = "http://listado.tuinmueble.com.ve/*#D[A:*]";
    private int counter;
    private List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> retrieveCustomers() {

        try {
            Document doc = Jsoup.connect(url).get();
            Element pageResults = doc.getElementById("searchResults");//("searchResults");
            List<Element> elementsArticle = pageResults.getElementsByClass("article");
            for (Element elementArticle : elementsArticle) {
                List<Element> linkToEachProperty = elementArticle.getElementsByAttribute("href");
                String propertyLink = linkToEachProperty.get(0).attr("href") + "?noIndex=true&showPhones=true";
                Document propertyPage = Jsoup.connect(propertyLink).get();
                Customer customer = new Customer();
                customer.setEmail("N/A");
                customer.setFirstName("N/A");
                customer.setLastName("N/A");
                List<String> thePhones = retrievePhones(
                        propertyPage.getElementsByClass("seller-details-box")
                        .text());
                customer.setPhoneNumbers(thePhones);
                customer.setFromUrl(propertyLink);
                customers.add(customer);
            }
            counter++;
            if (counter <= InitialConfiguration.MAX_PAGE) {
                url = doc.getElementsByClass("last-child").get(0)
                        .getElementsByTag("a").get(0).attr("href");
                retrieveCustomers();
            }

        } catch (IOException ex) {
            retrieveCustomers();
            Logger.getLogger(TuInmuebleStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    private List<String> retrievePhones(String phones) {
        String[] probablyPhones = phones.split("\\|");
        List<String> thePhones = Arrays.asList(probablyPhones);
        return thePhones.stream()
                .map(p -> PhoneSanitizer.sanitize(p))
                .filter(m -> m.length() == 10)
                .filter(m -> !m.contains("\\/"))
                .filter(m -> m.startsWith("4")).collect(Collectors.toList());
    }

}
