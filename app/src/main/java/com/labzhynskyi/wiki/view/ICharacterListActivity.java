package com.labzhynskyi.wiki.view;

import com.labzhynskyi.wiki.model.CharacterData;

import java.util.List;

public interface ICharacterListActivity {
    void updateUI(List<CharacterData> list);
    void startVideoActivity(int id);
    void updateUISort();
}
