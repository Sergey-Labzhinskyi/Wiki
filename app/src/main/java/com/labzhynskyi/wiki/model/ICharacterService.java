package com.labzhynskyi.wiki.model;



import io.reactivex.Observable;

import retrofit2.http.GET;

import retrofit2.http.Url;

public interface ICharacterService {



    @GET()

    Observable<CharacterList> getCharacter(@Url String page); //@Path("page") String page

   /* @GET("character/?page=2")
    Observable<CharacterList> getCharacter2();*/
}
