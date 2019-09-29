package com.labzhynskyi.wiki.presenter;

import com.labzhynskyi.wiki.model.CharacterList;
import com.labzhynskyi.wiki.view.ICharacterListActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface ICharacterListPresenter {
    void attachView(ICharacterListActivity videoListActivity);
    void detachView();
    void getCharacterList();
    void onClick(int position);
    void onClickButton();
    Observable<CharacterList> getCharacterObservable(int page);
    Observer<CharacterList> getCharacterObserver();
}
