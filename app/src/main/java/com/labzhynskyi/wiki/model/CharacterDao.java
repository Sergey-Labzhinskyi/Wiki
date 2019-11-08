package com.labzhynskyi.wiki.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
@Dao
public interface CharacterDao {

    @Query("SELECT * FROM characterData")
    Single<List<CharacterData>> getAllCharacterData();

    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List<CharacterData> list);




    @Update
    Completable update(List<CharacterData> list);

}
