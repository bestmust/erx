<?php
 
class DB_Functions_Patient {
 
    private $db;
 
    //put your code here
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }
 
    /**
     * Storing new user
     * returns user details
     */
    public function storePatient($name, $email, $password, $address, $telephone) {
       
        require_once 'DB_Functions.php';
        $dbFunctions = new DB_Functions();
        
        $resultP = mysql_query("INSERT INTO per_all_people_f(name, email, person_type, telephone ) VALUES('$name', '$email', 'P' , '$telephone');");
        
        
        // check for successful store
        if ($resultP) {
            // get user details 
            $personId = mysql_insert_id(); // last inserted id
            $result = mysql_query("SELECT * FROM per_all_people_f WHERE person_id = $personId");
        
            $uuid = uniqid('', true);
            $hash = $dbFunctions->hashSSHA($password);
            $encrypted_password = $hash["encrypted"]; // encrypted password
            $salt = $hash["salt"]; // salt
        
            $resultU = mysql_query("INSERT INTO users(unique_id, name, email, person_id, encrypted_password, salt, created_at) VALUES('$uuid', '$name', '$email', '$personId', '$encrypted_password', '$salt', NOW())");
            
            $resultPatient = mysql_query("INSERT INTO patient(person_id) VALUES('$personId')");
            
            $resultAddress = mysql_query("INSERT INTO address(house_no,person_id) VALUES('$address','$personId')");
            
            // return user details
                    
                    if($resultU&&$resultPatient&&$resultAddress) {
            return mysql_fetch_array($result);
                    }
                    else
                    {
                        return FALSE;
                    }
        } else {
            return false;
        }
    }
 
    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($email, $password) {
        $result = mysql_query("SELECT * FROM users WHERE email = '$email'") or die(mysql_error());
        // check for result 
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            $result = mysql_fetch_array($result);
            $salt = $result['salt'];
            $encrypted_password = $result['encrypted_password'];
            $hash = $this->checkhashSSHA($salt, $password);
            // check for password equality
            if ($encrypted_password == $hash) {
                // user authentication details are correct
                return $result;
            }
        } else {
            // user not found
            return false;
        }
    }
    
    
 /**
     * Get user by email and password
     */
    public function getPatientByEmail($email) {
        $result = mysql_query("SELECT * FROM users WHERE email = '$email'") or die(mysql_error());
        // check for result 
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            $result = mysql_fetch_array($result);
            
                return $result;
            
        } else {
            // user not found
            return false;
        }
    }
    /**
     * Check user is existed or not
     */
    public function isPatientExisted($email) {
        $result = mysql_query("SELECT email from per_all_people_f WHERE email = '$email' AND person_type = 'P'");
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            // user existed 
            return true;
        } else {
            // user not existed
            return false;
        }
    }
 
    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    public function hashSSHA($password) {
 
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }
 
    /**
     * Decrypting password
     * @param salt, password
     * returns hash string
     */
    public function checkhashSSHA($salt, $password) {
 
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
 
        return $hash;
    }
 
}
 
?>