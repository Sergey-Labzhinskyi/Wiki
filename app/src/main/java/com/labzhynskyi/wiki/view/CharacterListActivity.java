package com.labzhynskyi.wiki.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.labzhynskyi.wiki.R;
import com.labzhynskyi.wiki.model.CharacterData;
import com.labzhynskyi.wiki.presenter.CharacterListPresenter;

import java.util.List;

public class CharacterListActivity extends AppCompatActivity implements CharacterListFragment.CallbackOnClickList, ICharacterListActivity {

    private static final String TAG = "CharacterListActivity";
    private static final String ID = "ID";

    private List<CharacterData> mCharacters;
    private CharacterListPresenter mCharacterListPresenter;
    private Toolbar mToolbar;
    private ICharacterListFragment mICharacterListFragment;
    private  CharacterListFragment mCharacterListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mCharacterListPresenter = new CharacterListPresenter();
        getCharacterList();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCharacterListPresenter.detachView();
    }

    private void getCharacterList() {
        mCharacterListPresenter.attachView(this);
        mCharacterListPresenter.getCharacterList();
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
    public void updateUI(List<CharacterData> list) {
        mCharacters = list;
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        mCharacterListFragment = CharacterListFragment.newInstance(mCharacters);
        Log.d(TAG, "newInstance" + mCharacters.size());
        ft.add(R.id.fragment_container, mCharacterListFragment);
        ft.commit();
        Log.d(TAG, "updateUI" + mCharacters.size());
    }

    @Override
    public void startVideoActivity(int id) {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra(ID, id);
        startActivity(intent);
    }

    @Override
    public void updateUISort() {
        if (mCharacterListFragment instanceof CharacterListFragment){
            mICharacterListFragment = (ICharacterListFragment) mCharacterListFragment;
        }
        mICharacterListFragment.UpdateUISort();
    }
}
