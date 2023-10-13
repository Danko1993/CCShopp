package com.codecool.shop.controller;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

@Getter @Setter
public class FormValidator {
    public Map returnDetailsMap(HashMap<String, String> orderDetails){
        Map<String, String> detailsMap = new HashMap<>();
        Map.ofEntries(
                entry("Name", "name"),
                entry("E-mail", "email"),
                entry("City", "city"),
                entry("Address", "address"),
                entry("State", "state"),
                entry("Zip code", "zip"),
                entry("Card name", "cardname"),
                entry("Card number", "cardnumber"),
                entry("Expiration month", "expmonth"),
                entry("Expiration year", "expyear"),
                entry("CVV", "cvv")
        ).forEach((label, key) -> detailsMap.put(label, orderDetails.get(key)));
        return detailsMap;
    }
    public Map isValid(HashMap<String, String> orderDetails) {
        Map<String, String> detailsMap = returnDetailsMap(orderDetails);
        Map errorCodes = new HashMap();

        Set<String> cantContainNumbers = Set.of("Name","E-mail","City","State","Name on card");
        Set<String> cantContainLetters = Set.of("Zip code","Card number","Expiration month","Expiration year","CVV");
        for (Map.Entry<String, String> entry : detailsMap.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();
            if(fieldValue.equals("") || fieldValue.equals(null))
                errorCodes.put(fieldValue, fieldName + " cannot be empty");
            if(cantContainNumbers.contains(fieldName) && fieldValue.matches(".*\\d.*"))
                errorCodes.put(fieldValue, fieldName + " should not contain numbers");
            if(cantContainLetters.contains(fieldName) && fieldValue.matches(".*[a-zA-Z].*"))
                errorCodes.put(fieldValue, fieldName + " should not contain letters");

            if(fieldName.equals("E-mail") ) {
                if(fieldValue.contains("@")) {
                    String[] emailComponents = fieldValue.split("[@.]");
                    if(emailComponents.length < 2)
                        errorCodes.put(fieldValue, fieldName + " must contain domain name, e.g. \"example@gmail.com\"");
                } else errorCodes.put(fieldValue, fieldName + " must contain @ sign");
            }
            if(fieldName.equals("Zip code")){
                if(fieldValue.contains("-")){
                    String[] zipCodeComponents = fieldValue.split("-");
                    if(zipCodeComponents[0].length()!=2 || zipCodeComponents[1].length()!=3)
                        errorCodes.put(fieldValue, fieldName + " must be of proper format e.g. \"12-345\"");
                }
            }
            if(fieldName.equals("Card number")){
                int cardNumberLength = fieldValue.replaceAll("-","").length();
                if(!(cardNumberLength >= 16 && cardNumberLength <= 19))
                    errorCodes.put(fieldValue, fieldName + " must be between 16 and 19 digits");
            }
            if(fieldName.equals("Expiration month")){
                if(!(Integer.parseInt(fieldValue) >= 1 && Integer.parseInt(fieldValue) <= 12))
                    errorCodes.put(fieldValue, fieldName + " must be between 1 and 12");
            }
            if(fieldName.equals("Expiration year")){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
                LocalDateTime now = LocalDateTime.now();
                String dateYear = dtf.format(now);
                if(Integer.parseInt(fieldValue) < Integer.parseInt(dateYear))
                    errorCodes.put(fieldValue, fieldName + " must not be smaller than current year");
            }
            if(fieldName.equals("CVV")){
                if(!(fieldValue.length() == 3 || fieldValue.length() == 4))
                    errorCodes.put(fieldValue, fieldName + " must be between 3 or 4 characters");
            }


        }
        return errorCodes;
    }
}
