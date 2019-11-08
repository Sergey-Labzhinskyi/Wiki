package com.labzhynskyi.wiki.presenter;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.labzhynskyi.wiki.model.CharacterData;
import com.labzhynskyi.wiki.model.CharacterListResult;
import com.labzhynskyi.wiki.model.DataBaseHelper;
import com.labzhynskyi.wiki.view.ICharacterListActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CharacterListPresenter implements ICharacterListPresenter {

    private static final String TAG = "CharacterListPresenter";


    private ICharacterListActivity mICharacterListActivity;

    private List<CharacterData> mCharacterList;
    private Disposable disposable;
    private DataBaseHelper mDataBaseHelper;
    private Date mDate;
    private SimpleDateFormat mSimpleDateFormat;
    private String mStringDate;


    @Override
    public void attachView(ICharacterListActivity iCharacterListActivity) {
        mICharacterListActivity = iCharacterListActivity;
        mCharacterList = new ArrayList<>();
        mDataBaseHelper = new DataBaseHelper();
    }

    @Override
    public void detachView() {
        mICharacterListActivity = null;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getCharacterList() {

        Log.d(TAG, "getCharacterList");
        getListCharacterNetwork()
                .subscribeOn(Schedulers.io()).retry()
                .ambWith(getListCharacterDB().subscribeOn(Schedulers.io()))
                .map(s -> mCharacterList = s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CharacterData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<CharacterData> characterDataList) {
                        Log.d(TAG, "onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                        CharacterListResult.setCharacterDataList(mCharacterList);
                        mICharacterListActivity.updateUI(mCharacterList, getDate());
                        disposable.dispose();
                    }
                });


    }

    @Override
    public void getCharacterListNetwork() {
        Log.d(TAG, "getCharacterListNetwork");
        getListCharacterNetwork()
                .map(s -> mCharacterList = s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CharacterData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "getCharacterListNetwork + onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<CharacterData> characterDataList) {
                        Log.d(TAG, "getCharacterListNetwork + onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "getCharacterListNetwork + onError" + e.toString());
                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "getCharacterListNetwork + onComplete");
                        CharacterListResult.setCharacterDataList(mCharacterList);
                        mICharacterListActivity.updateUI(mCharacterList, getDate());
                        // mICharacterListActivity.setDate();
                        disposable.dispose();
                    }
                });


    }
    @Override
    public void saveCharacters() {
        Log.d(TAG, "saveCharacters");
        mDataBaseHelper.saveCharacters(mCharacterList);
    }
    @Override
    public void updateCharacters() {
        Log.d(TAG, "updateCharacters");
        mDataBaseHelper.updateCharacters(mCharacterList);

    }

    @Override
    public Observable<List<CharacterData>> getListCharacterNetwork() {
        Log.d(TAG, "getListCharacterNetwork");
        return Observable.range(1, 25)
                .flatMap(page -> mDataBaseHelper.getCharactersNetwork(page))
                .flatMap(i -> Observable.just(i.getResults()))
                .flatMap(i -> Observable.fromIterable(i))
                .map(s -> {
                    mCharacterList.add(s);
                    return mCharacterList;
                });
    }


    @SuppressLint("CheckResult")
    @Override
    public Observable<List<CharacterData>> getListCharacterDB() {
        Log.d(TAG, "getListCharacterDB");
        return mDataBaseHelper.getCharactersDB();
    }


    @Override
    public void onClick(int position) {
        mICharacterListActivity.startCharacterActivity(position);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onClickButton() {
        Log.d(TAG, "onClickButton");
        Collections.sort(mCharacterList);
        mICharacterListActivity.updateUISort();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate() {
        Log.d(TAG, "getDate");
        mDate = Calendar.getInstance().getTime();
        mSimpleDateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        return mStringDate = mSimpleDateFormat.format(mDate);
    }


}


