package com.labzhynskyi.wiki.model;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class DataBaseHelper {

    private static final String TAG = "DataBaseHelper";
    private AppDataBase mAppDataBase;
    private CharacterDao mCharacterDao;

    public DataBaseHelper() {
        mAppDataBase = App.getInstance().getDatabase();
        mCharacterDao = mAppDataBase.mCharacterDao();
    }

    public Observable<List<CharacterData>> getCharactersDB() {
        return mCharacterDao.getAllCharacterData()
                .toObservable();


    }

    public void saveCharacters(List<CharacterData> list) {
        mCharacterDao.insert(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    public void updateCharacters(List<CharacterData> list) {
        mCharacterDao.update(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public Observable<CharacterList> getCharactersNetwork(int page) {
        return CharacterApi.getRetrofit().create(ICharacterService.class)
                .getCharacter("character/?page=" + page)
                .doOnNext(s -> Log.d(TAG, String.valueOf(s.getResults().size())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
