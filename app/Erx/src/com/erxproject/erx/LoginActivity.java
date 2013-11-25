package com.erxproject.erx;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.erxproject.erx.controller.DoctorController;
import com.erxproject.erx.library.DatabaseHandler;
import com.erxproject.erx.library.NetStatus;

public class LoginActivity extends Activity {
	protected static final String EXTRA_MESSAGE = "";
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	private static String KEY_PERSON_ID = "person_id";
	private static String KEY_DOCTOR_ID = "doctor_id";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		// Importing all assets like buttons, text fields
		inputEmail = (EditText) findViewById(R.id.loginEmail);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				DoctorController doctorController = new DoctorController(
						getApplicationContext());
				Context context = getApplicationContext();
				JSONObject json;

				if (!NetStatus.getInstance(context).isOnline(context)) {

					Toast t = Toast.makeText(context,
							"Please connect to Internet.", Toast.LENGTH_SHORT);
					t.show();
					Log.v("Home", "You are not online!!!!");
					return;
				}

				json = doctorController.loginUser(email, password);

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						loginErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS);
						if (Integer.parseInt(res) == 1) {
							// user successfully logged in
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(
									getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");

							// Clear all previous data in database
							doctorController
									.logoutUser(getApplicationContext());
							db.addUser(json_user.getString(KEY_NAME),
									json_user.getString(KEY_EMAIL),
									json.getString(KEY_UID),
									json_user.getString(KEY_CREATED_AT),
									json_user.getString(KEY_PERSON_ID),
									json_user.getString(KEY_DOCTOR_ID));

							// Launch Dashboard Screen
							Intent dashboard = new Intent(
									getApplicationContext(),
									DoctorHomeActivity.class);

							// Close all views before launching Dashboard
							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							dashboard.putExtra(EXTRA_MESSAGE,
									json_user.getString(KEY_NAME));
							startActivity(dashboard);

							// Close Login Screen
							finish();
						} else {
							// Error in login
							loginErrorMsg.setText(json.getString(KEY_ERROR_MSG));
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						DoctorRegisterActivity.class);
				startActivity(i);
			}
		});
	}
}