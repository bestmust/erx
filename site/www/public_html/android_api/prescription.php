<?php

//error_reporting(E_ALL ^ (E_NOTICE | E_WARNING));
/**
 * File to handle all API requests
 * Accepts GET and POST
 * 
 * Each request will be identified by TAG
 * Response will be JSON data

  /**
 * check for POST request 
 */
if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];

    // include db handler
    require_once 'include/DB_Functions_Prescription.php';
    $dbFunctionsPrescription = new DB_Functions_Prescription();

    // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);

    // check for tag type
    if ($tag == 'get_patient_history') {
        
        $patientId = $_POST['patient_id'];
        $doctorId = $_POST['doctor_id'];
        
        $patientHistoryList = $dbFunctionsPrescription->getPatientHistory($patientId,$doctorId);
        
        if($patientHistoryList != false) {
            
            $response["success"] = 1;
            $response["patient_history"] = $patientHistoryList;
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in retrieving patient history.";
            echo json_encode($response);
        }
         
    } else {
        print "Access Denied";
    }
} else {
    echo 'Access Denied!';
}
?>