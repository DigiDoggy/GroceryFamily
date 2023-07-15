package com.example.FinalProject.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Measurement {
    private String value;
    private String unit;


    public Measurement() {
    }


    public Measurement(String value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    //    public static ArrayList<String> getArrayFromProductName(String product) {
//        String regex = "\\d+(?:,\\d+)?(?:%-\\d+(?:,\\d+)?%)?|\\w+";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(product);
//
//        List<String> result = new ArrayList<>();
//
//        while (matcher.find()) {
//            result.add(matcher.group());
//        }
//
//        return (ArrayList<String>) result;
//    }


//    public static Measurement setValueUnit(ArrayList<String> arrayList) {
//        List<String> units = Arrays.asList("kg", "g", "l", "ml", "tk");
//        String unit = "";
//        String value = "";
//        Measurement measurement = new Measurement();
//        for (int i = 0; i < arrayList.size(); i++) {
//            if (units.contains(arrayList.get(i))) {
//                unit = arrayList.get(i);
//                if (i > 0) {
//                    value = arrayList.get(i - 1);
//                }
//                measurement.setUnit(unit);
//                measurement.setValue(value);
//            }
//        }
//        return measurement;
//    }

    public static ArrayList<String> getArrayFromProductName(String product) {
        String regex = "\\d+(?:,\\d+)?(?:%-\\d+(?:,\\d+)?%)?|\\w+|\\d+\\w+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(product);

        ArrayList<String> result = new ArrayList<>();

        while (matcher.find()) {
            String group = matcher.group();
            if (Character.isDigit(group.charAt(0)) && Character.isLetter((group.charAt(group.length() - 1)))) {
                String[] split = group.split("(?<=\\d)(?=\\D)");
                result.addAll(Arrays.asList(split));
            } else {
                result.add(group);
            }
        }

        return result;
    }

    public static Measurement setValueUnit(ArrayList<String> arrayList) {
        List<String> units = Arrays.asList("kg", "g", "l", "ml", "tk");
        String unit = "";
        String value = "";
        Measurement measurement = new Measurement();
        for (int i = 0; i < arrayList.size(); i++) {
            if (units.contains(arrayList.get(i))) {
                unit = arrayList.get(i);
                if (i > 0 && isNumeric(arrayList.get(i - 1))) {
                    value = arrayList.get(i - 1);
                }
                measurement.setUnit(unit);
                measurement.setValue(value);
            }
        }
        return measurement;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}


