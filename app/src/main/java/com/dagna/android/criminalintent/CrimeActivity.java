package com.dagna.android.criminalintent;

import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by user on 15-6-24.
 */
public class CrimeActivity extends SinglefragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

}