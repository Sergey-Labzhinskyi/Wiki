package com.labzhynskyi.wiki.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CharacterData implements Serializable, Comparable<CharacterData>{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("species")
    @Expose
    private String species;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("origin")
    @Expose
    private Origin mOrigin;
    @SerializedName("location")
    @Expose
    private Location mLocation;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("episode")
    @Expose
    private List<String> episode;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("created")
    @Expose
    private String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Origin getOrigin() {
        return mOrigin;
    }

    public void setOrigin(Origin origin) {
        mOrigin = origin;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List getEpisode() {
        return episode;
    }

    public void setEpisode(List episode) {
        this.episode = episode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public static CharacterData newInstance(){
        return new CharacterData();
    }


    @Override
    public int compareTo(CharacterData o) {
        return this.name.compareTo(o.name);
    }

    public class Origin implements Serializable{

         @SerializedName("name")
         @Expose
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

  public  class Location implements Serializable{


      @SerializedName("name")
      @Expose
      private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
