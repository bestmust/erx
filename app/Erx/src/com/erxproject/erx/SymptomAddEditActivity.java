package com.erxproject.erx;

import android.support.v4.app.Fragment;

public class SymptomAddEditActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new PrescriptionMedicineAddEditFragment();
	}

}
