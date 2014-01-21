package com.erxproject.erx;

import java.util.ArrayList;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Disease;
import com.erxproject.erx.model.prescription.Parameter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DiseaseDiagnosedAddEditFragment extends Fragment {

	PrescriptionController prescriptionController;
	Prescription prescription;
	EditText inputDisease;
	Button btnSave;
	Button btnCancel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prescriptionController = new PrescriptionController(getActivity());
		prescription = Prescription.get(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_diseasediagnosed_add_edit,
				parent, false);

		inputDisease = (EditText) v.findViewById(R.id.inputDisease);
		btnSave = (Button) v.findViewById(R.id.btnDiseaseSave);
		btnCancel = (Button) v.findViewById(R.id.btnDiseaseCancel);

		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int historyId = prescription.getHistoryId();
				int diseaseId = prescriptionController.saveDiseaseDiagnosed(
						historyId, inputDisease.getText().toString());

				Disease d = prescriptionController.getDiseaseFromId(diseaseId);

				Prescription prescription = Prescription.get(getActivity());
				ArrayList<Disease> diseaseList = prescription.getDisease();
				diseaseList.add(d);
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

}
