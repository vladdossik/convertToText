package com.example.myapplication;

import java.util.Stack;

public class IntoText {

    private enum Ranges {UNITS, DECADES, HUNDREDS, THOUSANDS, MILLIONS, BILLIONS}

    ;

    private static Stack<ThreeChar> threeChars;

    private static class ThreeChar {
        char h, d, u;
        Ranges range;
    }

    public static String digitsToText(int d) {
        if (d < 0)
            return null;
        String s = Integer.toString(d);
        threeChars = new Stack<ThreeChar>();
        threeChars.push(new ThreeChar());
        threeChars.peek().range = Ranges.UNITS;
        StringBuilder sb = new StringBuilder(s).reverse();
        for (int i = 0; i < sb.length(); i++) {
            if (i > 0 && (i % 3) == 0) {
                threeChars.push(new ThreeChar());
            }
            ThreeChar threeChar = threeChars.peek();
            switch (i) {
                case 0:
                    threeChar.u = sb.charAt(i);
                    break;
                case 3:
                    threeChar.range = Ranges.THOUSANDS;
                    threeChar.u = sb.charAt(i);
                    break;
                case 6:
                    threeChar.range = Ranges.MILLIONS;
                    threeChar.u = sb.charAt(i);
                    break;
                case 9:
                    threeChar.range = Ranges.BILLIONS;
                    threeChar.u = sb.charAt(i);
                    break;
                case 2:
                case 5:
                case 8:
                    threeChar.h = sb.charAt(i);
                    break;
                default:
                    threeChar.d = sb.charAt(i);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!threeChars.isEmpty()) {
            ThreeChar temp = threeChars.pop();
            if (temp.h == '0' && temp.d == '0' && temp.u == '0') {
                continue;
            }
            if (temp.h > '0') {
                result.append(getHundreds(temp.h));
                result.append(' ');
            }
            if (temp.d > '0') {
                if (temp.d > '1' || (temp.d == '1' && temp.u == '0'))
                    result.append(getDecades(temp.d));
                else if (temp.d > '0') result.append(getTeens(temp.u));
                result.append(' ');
            }
            if (temp.u > '0' && temp.d != '1') {
                result.append(getUnits(temp.u, temp.range == Ranges.THOUSANDS));
                result.append(' ');
            }
            switch (temp.range) {
                case BILLIONS:
                    if (temp.d == '1' || temp.u == '0') result.append("миллиардов");
                    else if (temp.u > '4') result.append("миллиардов");
                    else if (temp.u > '1') result.append("миллиарда");
                    else result.append("миллиард");
                    break;
                case MILLIONS:
                    if (temp.d == '1' || temp.u == '0') result.append("миллионов");
                    else if (temp.u > '4') result.append("миллионов");
                    else if (temp.u > '1') result.append("миллиона");
                    else result.append("миллион");
                    break;
                case THOUSANDS:
                    if (temp.d == '1' || temp.u == '0') result.append("тысяч");
                    else if (temp.u > '4') result.append("тысяч");
                    else if (temp.u > '1') result.append("тысячи");
                    else result.append("тысяча");
                    break;
                default:
                    break;
            }
            result.append(' ');
        }
        char first = Character.toUpperCase(result.charAt(0));
        result.setCharAt(0, first);
        return result.toString();
    }

    private static String getHundreds(char dig) {
        switch (dig) {
            case '1':
                return "сто";
            case '2':
                return "двести";
            case '3':
                return "триста";
            case '4':
                return "четыреста";
            case '5':
                return "пятьсот";
            case '6':
                return "шестьсот";
            case '7':
                return "семьсот";
            case '8':
                return "восемьсот";
            case '9':
                return "девятьсот";
            default:
                return null;
        }
    }

    private static String getDecades(char dig) {
        switch (dig) {
            case '1':
                return "десять";
            case '2':
                return "двадцать";
            case '3':
                return "тридцать";
            case '4':
                return "сорок";
            case '5':
                return "пятьдесят";
            case '6':
                return "шестьдесят";
            case '7':
                return "семьдесят";
            case '8':
                return "восемьдесят";
            case '9':
                return "девяносто";
            default:
                return null;
        }
    }

    private static String getUnits(char dig, boolean female) {
        switch (dig) {
            case '1':
                return female ? "одна" : "один";
            case '2':
                return female ? "две" : "два";
            case '3':
                return "три";
            case '4':
                return "четыре";
            case '5':
                return "пять";
            case '6':
                return "шесть";
            case '7':
                return "семь";
            case '8':
                return "восемь";
            case '9':
                return "девять";
            default:
                return null;
        }
    }

    private static String getTeens(char dig) {
        String s = "";
        switch (dig) {
            case '1':
                s = "один";
                break;
            case '2':
                s = "две";
                break;
            case '3':
                s = "три";
                break;
            case '4':
                s = "четыр";
                break;
            case '5':
                s = "пят";
                break;
            case '6':
                s = "шест";
                break;
            case '7':
                s = "сем";
                break;
            case '8':
                s = "восем";
                break;
            case '9':
                s = "девят";
                break;
        }
        return s + "надцать";
    }
}