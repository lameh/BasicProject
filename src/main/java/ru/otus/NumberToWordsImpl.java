package ru.otus;

import java.util.ArrayDeque;

public class NumberToWordsImpl implements NumberToWords {

    private enum Ranges {UNITS, DECADES, HUNDREDS, THOUSANDS, MILLIONS, BILLIONS}

    private static ArrayDeque<Helper> helperStack;

    private static class Helper {
        char h, d, u;
        Ranges range;
    }

    public static String digits2Text(String number) {

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
    }

    static String constructingResultString(String[] arraySecondStage) {
        buildingHelperEntity(arraySecondStage);

        StringBuilder result = new StringBuilder();
        while (!helperStack.isEmpty()) {
            Helper hlp = helperStack.pop();

            if (hlp.h > '0') {
                result.append(getHundreds(hlp.h));
                result.append(' ');
            }
            /*if (hlp.h == 0) {
                continue;
            }*/
            if (hlp.d > '0') {
                if (hlp.d > '1' || (hlp.d == '1' && hlp.u == '0')) {
                    result.append(getDecades(hlp.d));
                } else if (hlp.d > '0') {
                    result.append(getTeens(hlp.u));
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
                        result.append("????????????????????");
                    } else if (hlp.u > '4') {
                        result.append("????????????????????");
                    } else if (hlp.u > '1') {
                        result.append("??????????????????");
                    } else {
                        result.append("????????????????");
                    }
                    result.append(' ');
                    break;
                case MILLIONS:
                    if (hlp.h == '0' && hlp.u == '0' && hlp.d == '0')
                        break;
                    if (hlp.d == '1' || hlp.u == '0') {
                        result.append("??????????????????");
                    } else if (hlp.u > '4') {
                        result.append("??????????????????");
                    } else if (hlp.u > '1') {
                        result.append("????????????????");
                    } else {
                        result.append("??????????????");
                    }
                    result.append(' ');
                    break;
                case THOUSANDS:
                    if (hlp.h == '0' && hlp.u == '0' && hlp.d == '0')
                        break;
                    if (hlp.d == '1' || hlp.u == '0') {
                        result.append("??????????");
                    } else if (hlp.u > '4') {
                        result.append("??????????");
                    } else if (hlp.u > '1') {
                        result.append("????????????");
                    } else {
                        result.append("????????????");
                    }
                    result.append(' ');
                    break;
                default:
                    getRubles(hlp, result);
                    result.append(' ');
            }
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
                return "??????";
            case '2':
                return "????????????";
            case '3':
                return "????????????";
            case '4':
                return "??????????????????";
            case '5':
                return "??????????????";
            case '6':
                return "????????????????";
            case '7':
                return "??????????????";
            case '8':
                return "??????????????????";
            case '9':
                return "??????????????????";
            default:
                return "";
        }
    }

    private static String getDecades(char dig) {
        switch (dig) {
            case '1':
                return "????????????";
            case '2':
                return "????????????????";
            case '3':
                return "????????????????";
            case '4':
                return "??????????";
            case '5':
                return "??????????????????";
            case '6':
                return "????????????????????";
            case '7':
                return "??????????????????";
            case '8':
                return "??????????????????????";
            case '9':
                return "??????????????????";
            default:
                return null;
        }
    }

    private static String getUnits(char dig, boolean female) {
        switch (dig) {
            case '1':
                return female ? "????????" : "????????";
            case '2':
                return female ? "??????" : "??????";
            case '3':
                return "??????";
            case '4':
                return "????????????";
            case '5':
                return "????????";
            case '6':
                return "??????????";
            case '7':
                return "????????";
            case '8':
                return "????????????";
            case '9':
                return "????????????";
            default:
                return null;
        }
    }

    private static String getTeens(char dig) {
        String s = "";
        switch (dig) {
            case '1':
                s = "????????";
                break;
            case '2':
                s = "??????";
                break;
            case '3':
                s = "??????";
                break;
            case '4':
                s = "??????????";
                break;
            case '5':
                s = "??????";
                break;
            case '6':
                s = "????????";
                break;
            case '7':
                s = "??????";
                break;
            case '8':
                s = "??????????";
                break;
            case '9':
                s = "??????????";
                break;
        }
        return s + "??????????????";
    }

    private static void getRubles(Helper hlp, StringBuilder result) {
        if (hlp.d == '1' || hlp.u == '0' || hlp.u > '4') {
            result.append("????????????");
        } else if (hlp.u > '1') {
            result.append("??????????");
        } else {
            result.append("??????????");
        }
    }

    /*   private static void getDollars(Helper hlp, StringBuilder result) {
        if (hlp.d == '1' || hlp.u == '0' || hlp.u > '4') {
            result.append("????????????????");
        } else if (hlp.u > '1') {
            result.append("??????????????");
        } else {
            result.append("????????????");
        }
    } */

    private static void getPennies(String[] arraySecondStage, StringBuilder result) {
        switch (arraySecondStage[1].charAt(1)) {
            case '1':
                result.append(arraySecondStage[1].charAt(0) != '1' ? "??????????????" : "????????????");
                break;
            case '2':
            case '3':
            case '4':
                result.append(arraySecondStage[1].charAt(0) != '1' ? "??????????????" : "????????????");
                break;
            default:
                result.append("????????????");
        }
    }

    /* private static void getCents(String[] arraySecondStage, StringBuilder result) {
        switch (arraySecondStage[1].charAt(1)) {
            case '1':
                result.append(arraySecondStage[1].charAt(0) != '1' ? "????????" : "????????????");
                break;
            case '2':
            case '3':
            case '4':
                result.append(arraySecondStage[1].charAt(0) != '1' ? "??????????" : "????????????");
                break;
            default:
                result.append("????????????");
        }
    }*/
}
