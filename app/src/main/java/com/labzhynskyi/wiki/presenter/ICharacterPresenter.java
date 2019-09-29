package com.labzhynskyi.wiki.presenter;

import com.labzhynskyi.wiki.view.ICharacterActivity;

public interface ICharacterPresenter {
     void attachView(ICharacterActivity icharacterActivity);
     void characterIsSelected(int id) ;
     void detachView();
}
