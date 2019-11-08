package com.labzhynskyi.wiki.presenter;

import com.labzhynskyi.wiki.model.CharacterData;
import com.labzhynskyi.wiki.view.ICharacterListActivity;

import java.util.List;

import io.reactivex.Observable;


public interface ICharacterListPresenter {
    void attachView(ICharacterListActivity videoListActivity);
    void detachView();
    void getCharacterList();
    void onClick(int position);
    void onClickButton();
    void getCharacterListNetwork();
    void saveCharacters();
    void updateCharacters();
    Observable<List<CharacterData>> getListCharacterNetwork();
    Observable<List<CharacterData>> getListCharacterDB();

}
