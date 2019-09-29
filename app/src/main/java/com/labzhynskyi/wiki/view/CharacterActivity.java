package com.labzhynskyi.wiki.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.labzhynskyi.wiki.R;
import com.labzhynskyi.wiki.model.CharacterData;
import com.labzhynskyi.wiki.presenter.CharacterPresenter;

public class CharacterActivity extends AppCompatActivity  implements ICharacterActivity{

    private static final String TAG = "CharacterActivity";
    private static final String ID = "ID";
    private int id;
    private CharacterPresenter mCharacterPresenter;
    private CharacterFragment mCharacterFragment;
    private CallbackCharacterSelected mCallbackCharacterSelected;
    private Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mCharacterPresenter = new CharacterPresenter();

        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mCharacterFragment = CharacterFragment.newInstance();
            ft.add(R.id.fragment_container, mCharacterFragment);
            ft.commit();
        }

        if (mCharacterFragment instanceof CharacterFragment){
            mCallbackCharacterSelected = (CallbackCharacterSelected) mCharacterFragment;
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoSelect();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCharacterPresenter.detachView();
    }

    @Override
    public void videoSelect(){
        id = getIntent().getIntExtra(ID, 0);
        mCharacterPresenter.attachView(this);
        mCharacterPresenter.characterIsSelected(id);
    }
    @Override
    public void setCharacterDetails(CharacterData characterData) {
        mCallbackCharacterSelected.setCharacterDetails(characterData);
    }

}
