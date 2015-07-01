package com.dagna.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by user on 15-6-25.
 */
public class CrimeListActivity extends SinglefragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}