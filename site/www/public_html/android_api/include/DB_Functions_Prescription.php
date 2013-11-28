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

        $result = mysqli_query($this->mysqli, "SELECT * from patient_history where patient_id = $patientId and doctor_id = $doctorId and saved = 'Y';");
        $rows = array();
        while ($r = mysqli_fetch_assoc($result)) {
            $rows[] = $r;
        }
        
        return $rows;
    }

}

?>