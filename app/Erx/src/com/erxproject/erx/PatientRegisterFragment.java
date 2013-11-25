package com.erxproject.erx;

import org.json.JSONException;
import org.json.JSONObject;

import com.erxproject.erx.controller.DoctorController;
import com.erxproject.erx.controller.PatientController;
import com.erxproject.erx.library.DatabaseHandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PatientRegisterFragment extends Fragment {

	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	EditText inputAddress;
	EditText inputContact;
	TextView registerErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_patient_register, parent, false);

		// Importing all assets like buttons, text fields
		inputFullName = (EditText) v.findViewById(R.id.registerName);
		inputEmail = (EditText) v.findViewById(R.id.registerEmail);
		inputPassword = (EditText) v.findViewById(R.id.registerPassword);
		inputAddress = (EditText) v.findViewById(R.id.registerAddress);
		inputContact = (EditText) v.findViewById(R.id.registerContact);
		btnRegister = (Button) v.findViewById(R.id.btnRegister);
		btnLinkToLogin = (Button) v.findViewById(R.id.btnLinkToLoginScreen);
		registerErrorMsg = (TextView) v.findViewById(R.id.register_error);

		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String name = inputFullName.getText().toString();
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				String address = inputAddress.getText().toString();
				String contact = inputContact.getText().toString();
				PatientController patientController = new PatientController(
						getActivity().getApplicationContext());
				JSONObject json = patientController.registerPatient(name,
						email, password, address, contact);
				Context context = getActivity();

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						registerErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS);
						if (Integer.parseInt(res) == 1) {
							// Patient successfully registered

							// Close Registration Screen
							getActivity().finish();
						} else {
							// Error in registration
							registerErrorMsg.setText(json
									.getString(KEY_ERROR_MSG));
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		return v;
	}

}
