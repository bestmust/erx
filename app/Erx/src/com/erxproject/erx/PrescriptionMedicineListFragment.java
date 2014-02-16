package com.erxproject.erx;

import java.util.ArrayList;

import org.json.JSONException;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Patient;
import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.PrescriptionMedicine;
import com.erxproject.erx.model.prescription.Symptom;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class PrescriptionMedicineListFragment extends ListFragment {
	private ArrayList<PrescriptionMedicine> prescriptionMedicine;
	private PrescriptionController prescriptionController;
	private Prescription prescription;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("medicine");
		Patient.get(getActivity());
		prescriptionController = new PrescriptionController(getActivity());
		prescription = Prescription.get(getActivity());

		try {
			prescriptionMedicine = prescriptionController
					.getPrescriptionMedicineList(prescription.getHistoryId());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prescription.setPrescriptionedicine(prescriptionMedicine);

		PrescriptionMedicineAdapter adapter = new PrescriptionMedicineAdapter(
				prescriptionMedicine);

		setListAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		((PrescriptionMedicineAdapter) getListAdapter()).notifyDataSetChanged();
	}

	private class PrescriptionMedicineAdapter extends
			ArrayAdapter<PrescriptionMedicine> {

		public PrescriptionMedicineAdapter(
				ArrayList<PrescriptionMedicine> prescriptionMedicine) {
			super(getActivity(), 0, prescriptionMedicine);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_medicine, null);
			}

			PrescriptionMedicine m = getItem(position);

			TextView medicineNameTextView = (TextView) convertView
					.findViewById(R.id.textViewMedicineName);
			CheckBox morning = (CheckBox) convertView
					.findViewById(R.id.checkBoxMorning);
			CheckBox afternoon = (CheckBox) convertView
					.findViewById(R.id.checkBoxAfternoon);

			CheckBox evening = (CheckBox) convertView
					.findViewById(R.id.checkBoxEvening);

			CheckBox night = (CheckBox) convertView
					.findViewById(R.id.checkBoxNight);

			medicineNameTextView.setText(m.getMedicineName());

			morning.setChecked(m.isMorning());
			afternoon.setChecked(m.isAfternoon());
			evening.setChecked(m.isEvening());
			night.setChecked(m.isNight());

			morning.setClickable(false);
			afternoon.setClickable(false);
			evening.setClickable(false);
			night.setClickable(false);

			return convertView;
		}

	}

}
