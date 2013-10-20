package com.erxproject.erx;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DocPatientLogin extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.a_docdatientlogin);
	}
	
	
	
	 public void openPatientHomePage(View view) {
        Intent intent = new Intent(this, DocPatientHomePage.class);
        startActivity(intent);
        finish();
    }

}
