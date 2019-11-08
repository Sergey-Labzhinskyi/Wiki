package com.labzhynskyi.wiki.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.labzhynskyi.wiki.R;
import com.labzhynskyi.wiki.model.CharacterData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CharacterListFragment extends Fragment implements ICharacterListFragment {

    private static final String TAG = "CharacterListFragment";
    private static final String ARG_PERMISSION = "CharacterData";
    private static final String DATE = "Date";
    private RecyclerView mRecyclerView;
    private CharacterAdapter mCharacterAdapter;
    private CallbackOnClickList mCallbackOnClickList;
    private List<CharacterData> mCharacters;
    private Button mButtonSort;
    private TextView mTextViewDate;
    private View mView;
    private String mStringDate;


    public static CharacterListFragment newInstance(List<CharacterData> characterList, String date) {
        final CharacterListFragment fragment = new CharacterListFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_PERMISSION, (Serializable) characterList);
        arguments.putString(DATE, date);
        fragment.setArguments(arguments);
        return fragment;
    }

    interface CallbackOnClickList {
        void onClicked(int position);
        void onClickedButtonSort();
    }





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mCallbackOnClickList = (CallbackOnClickList) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement Callback");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null && getArguments().containsKey(ARG_PERMISSION)) {
            mCharacters = new ArrayList<>();
            mCharacters = (List<CharacterData>) getArguments().getSerializable(ARG_PERMISSION);
            mStringDate = getArguments().getString(DATE);
            Log.d(TAG, "onCreate"  + mCharacters.size());
        } else {
            throw new IllegalArgumentException("Must be created through newInstance(...)");
        }
    }


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        mView = inflater.inflate(R.layout.fragment_character_list, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.character_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTextViewDate = (TextView) mView.findViewById(R.id.date);
        mTextViewDate.setText("Last update: " + mStringDate);
        mButtonSort = (Button) mView.findViewById(R.id.sort_button);
        mButtonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackOnClickList.onClickedButtonSort();
            }
        });

        updateUI();

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        updateUI();
    }

    public void updateUI(){
        Log.d(TAG, "updateUI");
        mCharacterAdapter = new CharacterAdapter(mCharacters);
        mRecyclerView.setAdapter(mCharacterAdapter);
    }
//implements ICharacterListFragment
    @Override
    public void UpdateUISort() {
        updateUI();
    }

   /* @Override
    public void setDate(String date) {

        mTextViewDate.setText(date);
    }*/

    private class CharacterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTextViewTitle;
        private TextView mTextViewId;
        private CharacterData mCharacter;
        private int mPosition;

        public CharacterHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_character, parent, false));
            itemView.setOnClickListener((View.OnClickListener) this);

            mTextViewTitle = itemView.findViewById(R.id.character_title);
           // mTextViewId = itemView.findViewById(R.id.character_id);
        }
        // bind CharacterHolder with ViedoItem
        @SuppressLint("SetTextI18n")
        public void bind(CharacterData character, int position){
            mPosition = position;
            mCharacter = character;
            mTextViewTitle.setText(mCharacter.getName());  // ПОДУМАТЬ!!!!
          //  mTextViewId.setText(mCharacter.getId() + ". ");  // ПОДУМАТЬ!!!!
        }

        @Override
        public void onClick(View v) {
            mCallbackOnClickList.onClicked(mPosition);


        }
    }


    private class CharacterAdapter extends RecyclerView.Adapter{


        private List<CharacterData> mCharacterList;

        public CharacterAdapter(List<CharacterData> characterList) {
            mCharacterList = characterList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CharacterHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           // Log.d(TAG, String.valueOf(mCharacterList.size()));
            CharacterData character = mCharacterList.get(position);
            ((CharacterHolder) holder).bind(character, position);

        }

        @Override
        public int getItemCount() {
            return mCharacterList.size();



        }
    }
}
