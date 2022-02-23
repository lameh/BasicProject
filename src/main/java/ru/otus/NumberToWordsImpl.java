package ru.otus;

import java.util.ArrayDeque;
import java.util.Iterator;

public class NumberToWordsImpl implements NumberToWords {

    private enum Ranges {UNITS, DECADES, HUNDREDS, THOUSANDS, MILLIONS, BILLIONS}

    private static ArrayDeque<Helper> helperStack;

    private static class Helper {
        char h, d, u;
        Ranges range;
    }

    public static String digits2Text(String number) {
//        if (number == null || number < 0.0) {
//            return null;
//        }
//        String stringFirstStage = number.toString();

        String[] arraySecondStage = convertStringToArray(number);

        return constructingResultString(arraySecondStage);
    }

    static String[] convertStringToArray(String string) {
        int capacity = string.length() - string.lastIndexOf('.');
        if (capacity > 3) {
            return null;
        }
        if (capacity == 2) {
            string += "0";
        }
        return string.split("\\.");
    }

    private static void buildingHelperEntity(String[] arraySecondStage) {
        helperStack = new ArrayDeque<Helper>();
        helperStack.push(new Helper());
        helperStack.peek().range = Ranges.UNITS;

        StringBuilder stringSegment = new StringBuilder(arraySecondStage[0]).reverse();

        for (int i = 0; i < stringSegment.length(); i++) {
            if (i > 0 && i % 3 == 0) {
                helperStack.push(new Helper());
            }
            Helper helper = NumberToWordsImpl.helperStack.peek();
            switch (i) {
                case 0:
                    helper.u = stringSegment.charAt(i);
                    break;
                case 3:
                    helper.range = Ranges.THOUSANDS;
                    helper.u = stringSegment.charAt(i);
                    break;
                case 6:
                    helper.range = Ranges.MILLIONS;
                    helper.u = stringSegment.charAt(i);
                    break;
                case 9:
                    helper.range = Ranges.BILLIONS;
                    helper.u = stringSegment.charAt(i);
                    break;
                case 2:
                case 5:
                case 8:
                    helper.h = stringSegment.charAt(i);
                    break;
                default:
                    helper.d = stringSegment.charAt(i);
            }
        }
//        Iterator<Helper> i = helperStack.iterator();
    }

    static String constructingResultString(String[] arraySecondStage) {
        buildingHelperEntity(arraySecondStage);

        StringBuilder result = new StringBuilder();
        while (!helperStack.isEmpty()) {
            Helper hlp = helperStack.pop();

            System.out.println(hlp.h);
            System.out.println(hlp.d);
            System.out.println(hlp.u);
            System.out.println(hlp.range);

            if (hlp.h > 0) {
                result.append(getHundreds(hlp.h));
                result.append(' ');
            }
            if (hlp.d > '0') {
                if (hlp.d > '1' || (hlp.d == '1' && hlp.u == '0')) {
                    result.append(getDecades(hlp.d));
                } else if (hlp.d > '0') {
                    result.append(getTeens(hlp.d));
                }
                result.append(' ');
            }
            if (hlp.u > '0' && hlp.d != '1') {
                result.append(getUnits(hlp.u, hlp.range == Ranges.THOUSANDS));
                result.append(' ');
            }

            switch (hlp.range) {
                case BILLIONS:
                    if (hlp.d == '1' || hlp.u == '0') {
                        result.append("миллиардов");
                    } else if (hlp.u > '4') {
                        result.append("миллиардов");
                    } else if (hlp.u > '1') {
                        result.append("миллиарда");
                    } else {
                        result.append("миллиард");
                    }
                    break;
                case MILLIONS:
                    if (hlp.d == '1' || hlp.u == '0') {
                        result.append("миллионов");
                    } else if (hlp.u > '4') {
                        result.append("миллионов");
                    } else if (hlp.u > '1') {
                        result.append("миллиона");
                    } else {
                        result.append("миллион");
                    }
                    break;
                case THOUSANDS:
                    if (hlp.d == '1' || hlp.u == '0') {
                        result.append("тысяч");
                    } else if (hlp.u > '4') {
                        result.append("тысяч");
                    } else if (hlp.u > '1') {
                        result.append("тысячи");
                    } else {
                        result.append("тысяча");
                    }
                    break;
                default:
                    getRubles(hlp, result);
            }
            result.append(' ');
        }

        result.append(arraySecondStage[1] + ' ');

        getPennies(arraySecondStage, result);

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

    private static void getRubles(Helper hlp, StringBuilder result) {
        if (hlp.d == '1' || hlp.u == '0' || hlp.u > '4') {
            result.append("рублей");
        } else if (hlp.u > '1') {
            result.append("рубля");
        } else {
            result.append("рубль");
        }
    }

    /*   private static void getDollars(Helper hlp, StringBuilder result) {
        if (hlp.d == '1' || hlp.u == '0' || hlp.u > '4') {
            result.append("долларов");
        } else if (hlp.u > '1') {
            result.append("доллара");
        } else {
            result.append("доллар");
        }
    } */

    private static void getPennies(String[] arraySecondStage, StringBuilder result) {
        switch (arraySecondStage[1].charAt(1)) {
            case '1':
                result.append(arraySecondStage[1].charAt(0) != '1' ? "копейка" : "копеек");
                break;
            case '2':
            case '3':
            case '4':
                result.append(arraySecondStage[1].charAt(0) != '1' ? "копейки" : "копеек");
                break;
            default:
                result.append("копеек");
        }
    }

    /* private static void getCents(String[] arraySecondStage, StringBuilder result) {
        switch (arraySecondStage[1].charAt(1)) {
            case '1':
                result.append(arraySecondStage[1].charAt(0) != '1' ? "цент" : "центов");
                break;
            case '2':
            case '3':
            case '4':
                result.append(arraySecondStage[1].charAt(0) != '1' ? "цента" : "центов");
                break;
            default:
                result.append("центов");
        }
    }*/
}
