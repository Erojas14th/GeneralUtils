package com.erojas.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlMessageUtils {

    private static Logger logger = LoggerFactory.getLogger(HtmlMessageUtils.class);


    private static String getMessageByRegex(String message, String regex) {
        List<Integer> indexes = getIndexRegex(message, regex);
        logger.info("=== regex: {} - {} ",  regex,indexes.size());
        StringBuffer buildMessage = new StringBuffer();
        int j = 0;
        int before = 0;

        if (!indexes.isEmpty()) {
            for (Integer e : indexes) {
                int next = e;
                j++;
                buildMessage.append(message.substring(before, next));
                if (j % 2 != 0) {
                    if (isCharacterAsteric(regex))
                        buildMessage.append(CharacterMessageEnum.CHARACTER_ASTERISC.mapHtmlCharacter(1));
                    if (isCharacterUnderscore(regex))
                        buildMessage.append(CharacterMessageEnum.CHARACTER_UNDERSCORE.mapHtmlCharacter(1));
                    if (isCharacterEne(regex))
                        buildMessage.append(CharacterMessageEnum.CHARACTER_ENE.mapHtmlCharacter(1));
                    if (isCharacterBreakSpace(regex))
                        buildMessage.append(CharacterMessageEnum.CHARACTER_BREAK_SPACE.mapHtmlCharacter(1));
                } else {
                    if (isCharacterAsteric(regex))
                        buildMessage.append(CharacterMessageEnum.CHARACTER_ASTERISC.mapHtmlCharacter(2));
                    if (isCharacterUnderscore(regex))
                        buildMessage.append(CharacterMessageEnum.CHARACTER_UNDERSCORE.mapHtmlCharacter(2));
                    if (isCharacterEne(regex))
                        buildMessage.append(CharacterMessageEnum.CHARACTER_ENE.mapHtmlCharacter(2));
                    if (isCharacterBreakSpace(regex))
                        buildMessage.append(CharacterMessageEnum.CHARACTER_BREAK_SPACE.mapHtmlCharacter(2));
                }
                before = next;
                before++;

                if (j == indexes.size()) {
                    buildMessage.append(message.substring(e + 1, message.length()));
                }
            }
            return buildMessage.toString();
        } else {
            return message;
        }

    }

    private static String getMessageByCompositeRegexBar(String message) {

        if (countRegexBarComposite(message)) {
            int size = getIndexBarCenter(message).size();

            for (int i = 0; i < size; i++) {

                List<String> list = subStringComposite(getIndexBarRight(message).get(0), getIndexBarCenter(message).get(0), getIndexBarLeft(message).get(0), message);

                StringBuilder buildMessage = new StringBuilder();
                buildMessage
                        .append(list.get(0))
                        .append(CharacterMessageEnum.CHARACTER_BAR_RIGHT.mapHtmlCharacter(1))
                        .append(CharacterMessageEnum.CHARACTER_BAR_CENTER.mapHtmlCharacter(0))
                        .append("\"")
                        .append(list.get(2))
                        .append("\"")
                        .append(">")
                        .append(list.get(1))
                        .append(CharacterMessageEnum.CHARACTER_BAR_LEFT.mapHtmlCharacter(2))
                        .append(list.get(3));

                message = buildMessage.toString();

            }
        }


        return message;
    }

    private static List<Integer> getIndexBarRight(String message) {
        List<Integer> indexesRight = new ArrayList<>();
        indexesRight = getIndexRegex(message, CharacterMessageEnum.CHARACTER_BAR_RIGHT.getCharacter());
        return indexesRight;
    }

    private static List<Integer> getIndexBarCenter(String message) {
        List<Integer> indexesRight = new ArrayList<>();
        indexesRight = getIndexRegex(message, CharacterMessageEnum.CHARACTER_BAR_CENTER.getCharacter());
        return indexesRight;
    }

    private static List<Integer> getIndexBarLeft(String message) {
        List<Integer> indexesRight = new ArrayList<>();
        indexesRight = getIndexRegex(message, CharacterMessageEnum.CHARACTER_BAR_LEFT.getCharacter());
        return indexesRight;
    }

    private static boolean countRegexBarComposite(String message) {

        if (getIndexBarRight(message).size() == getIndexBarCenter(message).size() && getIndexBarCenter(message).size() == getIndexBarLeft(message).size()) {
            return true;
        } else {
            return false;
        }

    }

    private static List<String> subStringComposite(int before, int center, int after, String message) {
        List<String> list = new ArrayList<>();
        list.add(message.substring(0, before));
        list.add(message.substring(before + 1, center));
        list.add(message.substring(center + 1, after));
        list.add(message.substring(after + 1, message.length()));
        return list;
    }


    private static List<Integer> getIndexSrcRight(String message) {
        List<Integer> indexesRight = new ArrayList<>();
        indexesRight = getIndexRegex(message, CharacterMessageEnum.CHARACTER_SRC_RIGHT.getCharacter());
        return indexesRight;
    }

    private static List<Integer> getIndexSrcCenter(String message) {
        List<Integer> indexesRight = new ArrayList<>();
        indexesRight = getIndexRegex(message, CharacterMessageEnum.CHARACTER_SRC_CENTER.getCharacter());
        return indexesRight;
    }

    private static List<Integer> getIndexSrcLeft(String message) {
        List<Integer> indexesRight = new ArrayList<>();
        indexesRight = getIndexRegex(message, CharacterMessageEnum.CHARACTER_SRC_LEFT.getCharacter());
        return indexesRight;
    }

    private static boolean countRegexSrcComposite(String message) {

        if (getIndexSrcRight(message).size() == getIndexSrcCenter(message).size() && getIndexSrcCenter(message).size() == getIndexSrcLeft(message).size()) {
            return true;
        } else {
            return false;
        }

    }

    private static String getMessageByCompositeRegexSrc(String message) {

        if (countRegexSrcComposite(message)) {
            int size = getIndexSrcCenter(message).size();

            for (int i = 0; i < size; i++) {

                List<String> list = subStringComposite(getIndexSrcRight(message).get(0), getIndexSrcCenter(message).get(0), getIndexSrcLeft(message).get(0), message);

                StringBuilder buildMessage = new StringBuilder();
                buildMessage
                        .append(list.get(0))
                        .append(CharacterMessageEnum.CHARACTER_SRC_RIGHT.mapHtmlCharacter(1))
                        .append(CharacterMessageEnum.CHARACTER_SRC_CENTER.mapHtmlCharacter(0))
                        .append("\"")
                        .append(list.get(2))
                        .append("\"")
                        .append(">")
                        .append("<img src=")
                        .append("\"")
                        .append(list.get(1))
                        .append("\"")
                        .append(">")
                        .append(CharacterMessageEnum.CHARACTER_SRC_LEFT.mapHtmlCharacter(2))
                        .append(list.get(3));

                message = buildMessage.toString();

            }
        }


        return message;
    }


    private static List<Integer> getIndexRegex(String sentence, String regex) {
        List<Integer> indexes = new ArrayList<>();
        try {

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sentence);

            while (matcher.find()) {
                indexes.add(matcher.start());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return indexes;
    }

    private static boolean isCharacterAsteric(String regex) {
        if (regex.equals(CharacterMessageEnum.CHARACTER_ASTERISC.getCharacter())) return true;
        else return false;
    }

    private static boolean isCharacterUnderscore(String regex) {
        if (regex.equals(CharacterMessageEnum.CHARACTER_UNDERSCORE.getCharacter())) return true;
        else return false;
    }

    private static boolean isCharacterEne(String regex) {
        if (regex.equals(CharacterMessageEnum.CHARACTER_ENE.getCharacter())) return true;
        else return false;
    }

    private static boolean isCharacterBreakSpace(String regex) {
        if (regex.equals(CharacterMessageEnum.CHARACTER_BREAK_SPACE.getCharacter())) return true;
        else return false;
    }


    public static String getMessage(String message) {
        String newMessage = HtmlMessageUtils.getMessageByRegex(message, CharacterMessageEnum.CHARACTER_ASTERISC.getCharacter());
        newMessage = HtmlMessageUtils.getMessageByRegex(newMessage, CharacterMessageEnum.CHARACTER_UNDERSCORE.getCharacter());
        newMessage = HtmlMessageUtils.getMessageByRegex(newMessage, CharacterMessageEnum.CHARACTER_ENE.getCharacter());
        newMessage = HtmlMessageUtils.getMessageByRegex(newMessage, CharacterMessageEnum.CHARACTER_BREAK_SPACE.getCharacter());
        newMessage = HtmlMessageUtils.getMessageByCompositeRegexBar(newMessage);
        newMessage = HtmlMessageUtils.getMessageByCompositeRegexSrc(newMessage);
        return newMessage;
    }
}
