package com.dagna.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by user on 15-6-25.
 */
public class CrimeLab {
    private ArrayList<Crime> mCrimes;
    private Context mAppContext;
    private static CrimeLab ourInstance;

    public static CrimeLab getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new CrimeLab(context.getApplicationContext());
        }
        return ourInstance;
    }

    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0);
            mCrimes.add(c);
        }
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }
}
