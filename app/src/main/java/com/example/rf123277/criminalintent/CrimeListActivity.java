package com.example.rf123277.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by RF123277 on 08.05.2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
