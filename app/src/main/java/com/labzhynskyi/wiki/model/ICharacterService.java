package com.labzhynskyi.wiki.model;

import com.labzhynskyi.wiki.presenter.CharacterListPresenter;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ICharacterService {

    @GET()
    Observable<CharacterList> getCharacter1(@Url String page); 
}
