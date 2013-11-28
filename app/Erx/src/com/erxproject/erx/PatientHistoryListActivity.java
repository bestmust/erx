package com.erxproject.erx;

import android.support.v4.app.Fragment;

public class PatientHistoryListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new PatientHistoryListFragment();
	}

}
