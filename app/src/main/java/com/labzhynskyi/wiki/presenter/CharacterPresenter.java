package com.labzhynskyi.wiki.presenter;


import com.labzhynskyi.wiki.model.CharacterData;
import com.labzhynskyi.wiki.model.CharacterListResult;
import com.labzhynskyi.wiki.view.CharacterActivity;
import com.labzhynskyi.wiki.view.ICharacterActivity;


public class CharacterPresenter implements ICharacterPresenter{

    private static final String TAG = "CharacterPresenter";
    private ICharacterActivity mICharacterActivity;
    private CharacterData mCharacterData;
@Override
    public void attachView(ICharacterActivity icharacterActivity) {
    mICharacterActivity = icharacterActivity;
    }
    @Override
    public void characterIsSelected(int id) {
        mCharacterData = new CharacterData();
        mCharacterData =  CharacterListResult.getCharacterDataList().get(id);
        mICharacterActivity.setCharacterDetails(mCharacterData);
    }

    @Override
    public void detachView() {
        mICharacterActivity = null;
    }
}
