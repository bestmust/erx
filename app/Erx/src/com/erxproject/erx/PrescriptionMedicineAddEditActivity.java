package com.erxproject.erx;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class PrescriptionMedicineAddEditActivity extends SingleFragmentActivity {
	
	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new PrescriptionMedicineAddEditFragment();
	}

}