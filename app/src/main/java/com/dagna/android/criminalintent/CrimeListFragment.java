package com.dagna.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 15-6-25.
 */
public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.cirmes_title);
        mCrimes = CrimeLab.getInstance(getActivity()).getCrimes();
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crimelist, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        Crime c = ((CrimeAdapter) getListAdapter()).getItem(position);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(intent);
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), R.layout.list_item_crime, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewHolder;
            if (convertView == null) {
                view = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
                viewHolder = new ViewHolder();
                viewHolder.titleTextView = (TextView) view.findViewById(R.id.cirme_list_item_titletextview);
                viewHolder.dateTextView = (TextView) view.findViewById(R.id.crime_list_item_datetextview);
                viewHolder.solvedCheckBox = (CheckBox) view.findViewById(R.id.crime_list_item_solvedcheckbox);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Crime crime = getItem(position);
            viewHolder.titleTextView.setText(crime.getTitle());
            viewHolder.dateTextView.setText(DateFormat.getDateFormat(getActivity()).format(crime.getDate()));
            viewHolder.solvedCheckBox.setChecked(crime.isSolved());
            return view;
        }
    }

    private class ViewHolder {
        TextView titleTextView;
        TextView dateTextView;
        CheckBox solvedCheckBox;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
    }
}