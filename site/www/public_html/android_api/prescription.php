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
         
    } else if ($tag == 'check_unsaved_prescription') {
        
        $patientId = $_POST['patient_id'];
        $doctorId = $_POST['doctor_id'];
        
        $unsavedPrescription = $dbFunctionsPrescription->checkUnsavedPrescription($patientId,$doctorId);
        
        if($unsavedPrescription != false) {
            
            $response["success"] = 1;
            $response["unsaved_prescription"] = $unsavedPrescription;
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in retrieving unsaved prescription.";
            echo json_encode($response);
        }
        
    } else if ($tag == 'create_new_prescription') {
        $patientId = $_POST['patient_id'];
        $doctorId = $_POST['doctor_id'];
        $personId = $_POST['person_id'];
        
        $newUnsavedPrescription = $dbFunctionsPrescription->createNewPrescription($patientId,$personId, $doctorId);
        
        if($newUnsavedPrescription != false) {
            
            $response["success"] = 1;
            $response["new_prescription"] = $newUnsavedPrescription;
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in creating new prescription.";
            echo json_encode($response);
        }
    } else if ($tag == 'save_prescription') {
        $historyId = $_POST['history_id'];
        
        $saved = $dbFunctionsPrescription->savePrescriptoin($historyId);
        
        if($saved != false) {
            
            $response["success"] = 1;
            $response["saved"] = "true";
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in saving prescription.";
            echo json_encode($response);
        }
        
    } else if ($tag == 'get_symptoms_list') {
        $historyId = $_POST["history_id"];
        $symptoms = $dbFunctionsPrescription->getSymptoms($historyId);
        
        if($symptoms!= false) {
            $response["success"] = 1;
            $response["symptoms"] = $symptoms;
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in getting symptoms.";
            echo json_encode($response);
        }
    } else if ($tag == 'save_symptom') {
        $historyId = $_POST["history_id"];
        $symptom = $_POST["symptom"];
        $symptomId = $dbFunctionsPrescription->saveSymptom($historyId,$symptom);
        
        if($symptomId!= false) {
            $response["success"] = 1;
            $response["symptom_id"] = $symptomId;
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in saving symptom.";
            echo json_encode($response);
        }
    } else if ($tag == 'get_symptom_from_id') {
        $symptomId = $_POST["symptom_id"];
        $symptom = $dbFunctionsPrescription->getSymptom($symptomId);
        
        if($symptom!= false) {
            $response["success"] = 1;
            $response["symptom"] = $symptom;
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in getting the symptom.";
            echo json_encode($response);
        }
    } else if ($tag == 'get_parameters_list') {
        $historyId = $_POST["history_id"];
        $parameters = $dbFunctionsPrescription->getParameters($historyId);
        
        if($parameters!= false) {
            $response["success"] = 1;
            $response["parameters"] = $parameters;
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in getting parameters.";
            echo json_encode($response);
        }
    } else if ($tag == 'save_parameter') {
        $historyId = $_POST["history_id"];
        $parameterType = $_POST["parameter_type"];
        $value = $_POST["value"];
        $parameterId = $dbFunctionsPrescription->saveParameter($historyId,$parameterType,$value);
        
        if($parameterId!= false) {
            $response["success"] = 1;
            $response["parameter_id"] = $parameterId;
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in saving parameter.";
            echo json_encode($response);
        }
    } else if ($tag == 'get_parameter_from_id') {
        $parameterId = $_POST["parameter_id"];
        $parameter = $dbFunctionsPrescription->getParameter($parameterId);
        
        if($parameter!= false) {
            $response["success"] = 1;
            $response["parameter"] = $parameter;
            echo json_encode($response);
        }else {
            $response["error"] = 1;
            $response["error_msg"] = "Error in getting the parameter.";
            echo json_encode($response);
        }
    }
    else {
        print "Access Denied";
    }
} else {
    echo 'Access Denied!';
}
?>