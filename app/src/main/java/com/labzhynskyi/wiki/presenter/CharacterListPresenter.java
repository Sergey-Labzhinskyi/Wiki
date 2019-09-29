package com.labzhynskyi.wiki.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.labzhynskyi.wiki.model.CharacterData;
import com.labzhynskyi.wiki.model.CharacterApi;
import com.labzhynskyi.wiki.model.CharacterList;
import com.labzhynskyi.wiki.model.CharacterListResult;
import com.labzhynskyi.wiki.model.ICharacterService;
import com.labzhynskyi.wiki.view.ICharacterListActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CharacterListPresenter implements ICharacterListPresenter {

    private static final String TAG = "CharacterListPresenter";


    private ICharacterListActivity mICharacterListActivity;
    private List<CharacterData> mCharactersData;
    private CharacterApi mCharacterApi;
    private List<CharacterData> mCharacterList;
    private Disposable disposable;


    @Override
    public void attachView(ICharacterListActivity iCharacterListActivity) {
        mICharacterListActivity = iCharacterListActivity;
    }

    @Override
    public void detachView() {
    mICharacterListActivity = null;
    }

    @Override
   public Observable<CharacterList> getCharacterObservable(int page) {

        return mCharacterApi.getRetrofit3().create(ICharacterService.class)
                .getCharacter1("character/?page=" + page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observer<CharacterList> getCharacterObserver() {
        mCharacterList = new  ArrayList();
        return new Observer<CharacterList>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(CharacterList characterList) {

                mCharactersData = new ArrayList<>();
                mCharactersData = characterList.getResults();

                for (int i = 0; i < mCharactersData.size(); i++) {
                    mCharacterList.add(mCharactersData.get(i));
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                if (mCharacterList.size() == 493) {
                    CharacterListResult.setCharacterDataList(mCharacterList);
                    mICharacterListActivity.updateUI(mCharacterList);
                    disposable.dispose();
                }

            }
        };
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCharacterList() {
        for (int i = 1; i <= 25; i++) {
            getCharacterObservable(i).subscribeWith(getCharacterObserver());
        }

    }


    @Override
    public void onClick(int position) {
        mICharacterListActivity.startVideoActivity(position);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onClickButton() {
        Log.d(TAG, "onClickButton");
        Collections.sort(mCharacterList);
        getCharacterObserver();
        mICharacterListActivity.updateUISort();
    }
}


