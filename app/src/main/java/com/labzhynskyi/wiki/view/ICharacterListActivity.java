package com.labzhynskyi.wiki.view;

import com.labzhynskyi.wiki.model.CharacterData;

import java.util.List;

public interface ICharacterListActivity {
    void updateUI(List<CharacterData> list, String date);
    void startCharacterActivity(int id);
    void updateUISort();
    boolean checkConnect();

}
