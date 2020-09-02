package com.erojas.message;

public enum CharacterMessageEnum {
    CHARACTER_ASTERISC("[*]"),
    CHARACTER_UNDERSCORE("[_]"),
    CHARACTER_ENE("[~]"),
    CHARACTER_BREAK_SPACE("[+]"),
    CHARACTER_BAR_RIGHT("[\\[]"),
    CHARACTER_BAR_LEFT("[\\]]"),
    CHARACTER_BAR_CENTER("[\\|]"),
    HTML_ASTERISC_INIT("<b>"),
    HTML_ASTERISC_END("</b>"),
    HTML_UNDERSCORE_INIT("<i>"),
    HTML_UNDERSCORE_END("</i>"),
    HTML_ENE_INIT("<del>"),
    HTML_ENE_END("</del>"),
    HTML_BREAK_SPACE_INIT("<br>"),
    HTML_BREAK_SPACE_END("<br>"),
    HTML_BAR_INIT("<a "),
    HTML_BAR_CENTER("href="),
    HTML_BAR_END("</a>");


    private String character;

    CharacterMessageEnum(String character) {
        this.character = character;
    }

    public String mapHtmlCharacter(int index) {

        if (character.equals(CHARACTER_ASTERISC.character) && isImpar(index)) return HTML_ASTERISC_INIT.character;
        if (character.equals(CHARACTER_ASTERISC.character) && isPar(index)) return HTML_ASTERISC_END.character;
        if (character.equals(CHARACTER_UNDERSCORE.character) && isImpar(index)) return HTML_UNDERSCORE_INIT.character;
        if (character.equals(CHARACTER_UNDERSCORE.character) && isPar(index)) return HTML_UNDERSCORE_END.character;
        if (character.equals(CHARACTER_ENE.character) && isImpar(index)) return HTML_ENE_INIT.character;
        if (character.equals(CHARACTER_ENE.character) && isPar(index)) return HTML_ENE_END.character;
        if (character.equals(CHARACTER_BREAK_SPACE.character) && isImpar(index)) return HTML_BREAK_SPACE_INIT.character;
        if (character.equals(CHARACTER_BREAK_SPACE.character) && isPar(index)) return HTML_BREAK_SPACE_END.character;
        if (character.equals(CHARACTER_BAR_RIGHT.character) && isImpar(index)) return HTML_BAR_INIT.character;
        if (character.equals(CHARACTER_BAR_CENTER.character) && isCenter(index)) return HTML_BAR_CENTER.character;
        if (character.equals(CHARACTER_BAR_LEFT.character) && isPar(index)) return HTML_BAR_END.character;
        return "";
    }

    public boolean isCenter(int number){
        if(number == 0) return true;
        else return false;
    }

    public boolean isPar(int number) {

        if (number == 1) return false;
        if (number % 2 == 0) return true;
        else return false;
    }

    public boolean isImpar(int number) {
        return !isPar(number);
    }

    public String getCharacter() {
        return character;
    }

}
