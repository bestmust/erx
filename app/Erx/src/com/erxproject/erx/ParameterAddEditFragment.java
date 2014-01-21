package com.erxproject.erx;

import java.util.ArrayList;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Parameter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ParameterAddEditFragment extends Fragment {

	PrescriptionController prescriptionController;
	Prescription prescription;
	EditText inputParameter;
	EditText inputValue;
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
		View v = inflater.inflate(R.layout.fragment_parameter_add_edit, parent,
				false);

		inputParameter = (EditText) v.findViewById(R.id.inputParameterType);
		inputValue = (EditText) v.findViewById(R.id.inputParameterValue);
		btnSave = (Button) v.findViewById(R.id.btnParameterSave);
		btnCancel = (Button) v.findViewById(R.id.btnParameterCancel);

		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int historyId = prescription.getHistoryId();
				int parameterId = prescriptionController.saveParameter(
						historyId, inputParameter.getText().toString(),
						inputValue.getText().toString());

				Parameter p = prescriptionController.getParameter(parameterId);

				Prescription prescription = Prescription.get(getActivity());
				ArrayList<Parameter> parameterList = prescription
						.getParameters();
				parameterList.add(p);
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
