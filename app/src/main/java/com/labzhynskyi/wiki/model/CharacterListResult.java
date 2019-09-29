package com.labzhynskyi.wiki.model;

import java.io.Serializable;
import java.util.List;

public class CharacterListResult implements Serializable {

    private static List<CharacterData> sCharacterDataList;

    public static List<CharacterData> getCharacterDataList() {
        return sCharacterDataList;
    }

    public static void setCharacterDataList(List<CharacterData> characterDataList) {
        sCharacterDataList = characterDataList;
    }



}
