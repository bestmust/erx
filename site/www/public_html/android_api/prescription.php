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
    $object = $_POST['object'];

    // include db handler
    require_once 'include/DB_Functions_Prescription.php';
    $dbFunctionsPrescription = new DB_Functions_Prescription();

    // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);

    // check for tag type
    if ($tag == 'select') {
        if ($object == 'prescription_saved') {
            $doctorId = $_POST['doctor_id'];
            $dbFunctionsPrescription->getSavedPrescription();
        }
    } else if ($tag == 'insert') {
        
    } else if ($tag == 'update') {
        
    } else if ($tag == 'delete'){
        
    }
}
else {
    echo 'Access Denied!';
}
?>