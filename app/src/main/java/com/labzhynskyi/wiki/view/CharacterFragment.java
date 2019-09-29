package com.labzhynskyi.wiki.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.labzhynskyi.wiki.R;
import com.labzhynskyi.wiki.model.CharacterData;
import com.squareup.picasso.Picasso;


public class CharacterFragment extends Fragment implements CallbackCharacterSelected{


    private static final String TAG = "CharacterFragment";
    private ImageView mImageView;
    private TextView mIdTextView;
    private TextView mNameTextView;
    private TextView mStatusTextView;
    private TextView mSpeciesTextView;
    private TextView mTypeTextView;
    private TextView mGenderTextView;
    private TextView mOriginTextView;
    private TextView mLocationTextView;
    private TextView mCreatedTextView;


    public static CharacterFragment newInstance() {
        final CharacterFragment fragment = new CharacterFragment();
        final Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View mView = inflater.inflate(R.layout.fragment_character_item, container, false);

        mImageView = (ImageView) mView.findViewById(R.id.imageView);
        mIdTextView = (TextView) mView.findViewById(R.id.char_id);
        mNameTextView = (TextView) mView.findViewById(R.id.character_name);
        mStatusTextView = (TextView) mView.findViewById(R.id.status);
        mTypeTextView= (TextView) mView.findViewById(R.id.type);
        mGenderTextView = (TextView) mView.findViewById(R.id.gender);
        mOriginTextView = (TextView) mView.findViewById(R.id.origin);
        mLocationTextView = (TextView) mView.findViewById(R.id.location);
        mCreatedTextView = (TextView) mView.findViewById(R.id.created);
        mSpeciesTextView = (TextView) mView.findViewById(R.id.species);

        return mView;
    }

    @Override
    public void setCharacterDetails(CharacterData characterData) {

        Picasso.get()
                .load(characterData.getImage())
                .into(mImageView);

        mIdTextView.setText(Integer.toString(characterData.getId()));
        mNameTextView.setText(characterData.getName());
        mStatusTextView.setText(characterData.getStatus());
        mTypeTextView.setText(characterData.getType());
        mGenderTextView.setText(characterData.getGender());
        mOriginTextView.setText(characterData.getOrigin().getName());
        mLocationTextView.setText(characterData.getLocation().getName());
        mCreatedTextView.setText(characterData.getCreated());
        mSpeciesTextView.setText(characterData.getSpecies());
    }
}
