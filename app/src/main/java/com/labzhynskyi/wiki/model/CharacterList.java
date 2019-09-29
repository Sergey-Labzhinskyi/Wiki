package com.labzhynskyi.wiki.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CharacterList implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("pages")
    @Expose
    private int pages;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("prev")
    @Expose
    private String prev;
    @SerializedName("results")
    @Expose
    private List<CharacterData> results;

    public List<CharacterData> getResults() {
        return results;
    }

    public void setResults(List<CharacterData> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }




    private static final String TAG = "CharacterList";
    public static List<CharacterData> sCharacters;


  /*  public void addCharacter(CharacterData character){

        if (sCharacters == null){
            sCharacters = new ArrayList<>();
            sCharacters.add(character);
        }else {
            sCharacters.add(character);
        }
        Log.d(TAG, String.valueOf(sCharacters.size()));
    }

    public List<CharacterData> createCharacter(){
        sCharacters = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
             sCharacters.add(new CharacterData());
        }
       return sCharacters;
    }
*/

}
