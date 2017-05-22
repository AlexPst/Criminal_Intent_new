package com.example.rf123277.criminalintent;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;


/**
 * Created by RF123277 on 08.05.2017.
 */


    public class CrimeFragment extends android.support.v4.app.Fragment {
        public static final String EXTRA_CRIME_ID = "criminalIntent.CRIME_ID";

        Crime mCrime;
        EditText mTitleField;
        Button mDateButton;
        CheckBox mSolvedCheckBox;

        public static CrimeFragment newInstance(UUID crimeId){
            Bundle args = new Bundle();
            args.putSerializable(EXTRA_CRIME_ID, crimeId);
            CrimeFragment fragment = new CrimeFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle arguments = getArguments();
            if (arguments != null) {
                UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
                mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_crime, parent, false);

            mTitleField = (EditText)v.findViewById(R.id.edit_crime_title);
            mTitleField.setText(mCrime.getTitle());
            mTitleField.addTextChangedListener (editTextWatcher);



            mDateButton = (Button)v.findViewById(R.id.crime_date);
            mDateButton.setText(mCrime.getDate().toString());
            mDateButton.setEnabled(false);

            mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
            mSolvedCheckBox.setChecked(mCrime.isSolved());
            mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // set the crime's solved property
                    mCrime.setSolved(isChecked);
                }
            });

            return v;
        }

    private final TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence c, int start, int count, int after) {
            mCrime.setTitle(c.toString());
        }

        @Override
        public void onTextChanged(CharSequence c, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable c) {

        }
    } ;



    }


