package com.erxproject.erx;

import java.util.ArrayList;

import org.json.JSONException;

import com.erxproject.erx.controller.MedicineController;
import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.Medicine;
import com.erxproject.erx.model.prescription.PrescriptionMedicine;
import com.erxproject.erx.model.prescription.Symptom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class PrescriptionMedicineAddEditFragment extends Fragment {

	PrescriptionController prescriptionController;
	MedicineController medicineController;
	Medicine tempMedicine;
	Prescription prescription;
	ArrayList<Medicine> medicines;
	ArrayList<String> medicineNames;
	AutoCompleteTextView inputMedicine;
	Button btnSave;
	Button btnCancel;
	CheckBox morning;
	CheckBox afternoon;
	CheckBox evening;
	CheckBox night;
	private OnClickListener listener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prescriptionController = new PrescriptionController(getActivity());
		medicineController = new MedicineController(getActivity());
		prescription = Prescription.get(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.fragment_prescription_medicine_add_edit,
						parent, false);

		inputMedicine = (AutoCompleteTextView) v
				.findViewById(R.id.inputMedicineName);
		btnSave = (Button) v.findViewById(R.id.btnPrescriptionMedicineSave);
		btnCancel = (Button) v.findViewById(R.id.btnPrescriptionMedicineCancel);
		morning = (CheckBox) v.findViewById(R.id.checkBoxSetMorning);
		afternoon = (CheckBox) v.findViewById(R.id.checkBoxSetAfternoon);
		evening = (CheckBox) v.findViewById(R.id.checkBoxSetEvening);
		night = (CheckBox) v.findViewById(R.id.checkBoxSetNight);

		try {
			medicines = medicineController.getMedicineAllList();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		medicineNames = new ArrayList<String>();
		int length = medicines.size();

		for (int index = 0; index < length; index++) {
			medicineNames.add(medicines.get(index).getMedicineName());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this.getActivity(),
				android.R.layout.simple_dropdown_item_1line, medicineNames);
		inputMedicine.setAdapter(adapter);

		inputMedicine.setOnClickListener(this.listener);

		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String name = inputMedicine.getText().toString();

				int index = medicineNames.indexOf(name);

				tempMedicine = medicines.get(index);

				int historyId = prescription.getHistoryId();
				int medicineDataId = prescriptionController
						.savePrescriptionMedicine(historyId,
								tempMedicine.getMedicineId(),
								morning.isChecked(), afternoon.isChecked(),
								evening.isChecked(), night.isChecked());

				PrescriptionMedicine m = prescriptionController
						.getPrescriptionMedicineFromId(medicineDataId);

				Prescription prescription = Prescription.get(getActivity());
				ArrayList<PrescriptionMedicine> medicineList = prescription
						.getPrescriptionMedicine();
				medicineList.add(m);
				getActivity().finish();
			}
		});

		btnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});

		return v;
	}

	public class Listener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
				int position, long id) {
			tempMedicine = medicines.get(position);

		}
	}
}
