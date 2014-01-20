<?php

class DB_Functions_Prescription {

    private $db;

    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->mysqli = $this->db->connect();
    }

    // destructor
    function __destruct() {
        
    }

    public function getPatientHistory($patientId, $doctorId) {

        $result = mysqli_query($this->mysqli, "SELECT * from patient_history where patient_id = $patientId and saved = 'Y' order by history_id;");
        $rows = array();
        while ($r = mysqli_fetch_assoc($result)) {
            $rows[] = $r;
        }
        return $rows;
    }

    public function checkUnsavedPrescription($patientId, $doctorId) {

        $result = mysqli_query($this->mysqli, "SELECT * from patient_history where patient_id = $patientId and doctor_id = $doctorId and saved = 'N';");
        $no_of_rows = mysqli_num_rows($result);
        if ($no_of_rows > 0) {
            $result = mysqli_fetch_array($result);
            return $result;
        } else {
            return false;
        }
    }

    public function createNewPrescription($patientId, $personId, $doctorId) {

        $result = mysqli_query($this->mysqli, "insert into patient_history (patient_id,person_id,doctor_id,saved) VALUES($patientId,$personId,$doctorId,'N');");

        if ($result == true) {
            $historyId = mysqli_insert_id($this->mysqli);
            $result = mysqli_query($this->mysqli, "SELECT * from patient_history where history_id = $historyId;");
            $result = mysqli_fetch_array($result);
            return $result;
        } else {
            return false;
        }
    }

    public function savePrescriptoin($historyId) {

        $result = mysqli_query($this->mysqli, "update patient_history set saved = 'Y' where history_id = $historyId;");
        return $result;
    }

    public function getSymptoms($historyId) {
        $result = mysqli_query($this->mysqli, "SELECT * from symptoms where history_id = $historyId;");
        $rows = array();
        while ($r = mysqli_fetch_assoc($result)) {
            $rows[] = $r;
        }
        return $rows;
    }

    public function saveSymptom($historyId, $symptom) {
        $result = mysqli_query($this->mysqli, "insert into symptoms (history_id,symptom) values($historyId,'$symptom');");
        if ($result == true) {
            $symptomId = mysqli_insert_id($this->mysqli);
            return $symptomId;
        } else {
            return false;
        }
    }
    
    public function getSymptom($symptomId) {
        $result = mysqli_query($this->mysqli, "select * from symptoms where symptom_id = $symptomId;");
        $result = mysqli_fetch_array($result);
        return $result;
    }
    
    public function getParameters($historyId) {
        $result = mysqli_query($this->mysqli, "SELECT * from parameters where history_id = $historyId;");
        $rows = array();
        while ($r = mysqli_fetch_assoc($result)) {
            $rows[] = $r;
        }
        return $rows;
    }
    
    public function saveParameter($historyId, $parameterType, $value) {
        $result = mysqli_query($this->mysqli, "insert into parameters (history_id,parameter_type,value) values($historyId,'$parameterType','$value');");
        if ($result == true) {
            $parameterId = mysqli_insert_id($this->mysqli);
            return $parameterId;
        } else {
            return false;
        }
    }
    
    public function getParameter($parameterId) {
        $result = mysqli_query($this->mysqli, "select * from parameters where parameter_id = $parameterId;");
        $result = mysqli_fetch_array($result);
        return $result;
    }
}

?>