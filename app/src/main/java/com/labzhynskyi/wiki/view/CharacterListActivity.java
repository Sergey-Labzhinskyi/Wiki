package com.labzhynskyi.wiki.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.labzhynskyi.wiki.R;
import com.labzhynskyi.wiki.model.CharacterData;
import com.labzhynskyi.wiki.model.DataBaseHelper;
import com.labzhynskyi.wiki.presenter.CharacterListPresenter;

import java.util.List;

public class CharacterListActivity extends AppCompatActivity implements CharacterListFragment.CallbackOnClickList, ICharacterListActivity {

    private static final String TAG = "CharacterListActivity";
    private static final String ID = "ID";
    private static int count = 0;

    private List<CharacterData> mCharacters;
    private CharacterListPresenter mCharacterListPresenter;
    private Toolbar mToolbar;
    private ICharacterListFragment mICharacterListFragment;
    private CharacterListFragment mCharacterListFragment;
    private String mStringDate = "-";

    private boolean isSavedDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mCharacterListPresenter = new CharacterListPresenter();
        mCharacterListPresenter.attachView(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if (checkConnect() && count == 0) {
            ToastUtil.showLong("Есть подключения к интернету", this);
            mCharacterListPresenter.getCharacterListNetwork();
            count = 1;
            isSavedDB = true;
        }else if (checkConnect()){
            ToastUtil.showLong("Есть подключения к интернету", this);
            mCharacterListPresenter.getCharacterListNetwork();
          //  getCharacterList();
        }
        else {
            ToastUtil.showLong("Нет подключения к интернету", this);
            getCharacterList();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isSavedDB) {
            mCharacterListPresenter.saveCharacters();
            isSavedDB = false;
        }else {
            mCharacterListPresenter.updateCharacters();
        }
        mCharacterListPresenter.detachView();
    }

    private void getCharacterList() {

        mCharacterListPresenter.getCharacterList();
    }
    @Override
    public boolean checkConnect() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    //implements CharacterListFragment.CallbackOnClickList
    @Override
    public void onClicked(int position) {
        mCharacterListPresenter.onClick(position);
    }


    @Override
    public void onClickedButtonSort() {
        mCharacterListPresenter.onClickButton();
    }

    //implements ICharacterListActivity
    @Override
    public void updateUI(List<CharacterData> list, String date) {
        mCharacters = list;
        if (mCharacters.size() != 0){
            mStringDate = date;
        }
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        mCharacterListFragment = CharacterListFragment.newInstance(mCharacters, mStringDate);
        Log.d(TAG, "newInstance" + mCharacters.size());
        ft.add(R.id.fragment_container, mCharacterListFragment);
        ft.commit();
        Log.d(TAG, "updateUI" + mCharacters.size());
    }

    @Override
    public void startCharacterActivity(int id) {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra(ID, id);
        startActivity(intent);
    }

    @Override
    public void updateUISort() {
        if (mCharacterListFragment instanceof CharacterListFragment) {
            mICharacterListFragment = (ICharacterListFragment) mCharacterListFragment;
        }
        mICharacterListFragment.UpdateUISort();
    }



}
