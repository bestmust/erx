<?php

class DB_Connect {

    // constructor
    function __construct() {
        
    }

    // destructor
    function __destruct() {
        // $this->close();
    }

    // Connecting to database
    public function connect() {
        require_once 'config.php';
        /*
          $mysql_host = "localhost";
          $mysql_database = "a8748905_android";
          $mysql_user = "root";
          $mysql_password = "tiger123";
         * 
         */
        $con = mysql_connect($mysql_host, $mysql_user, $mysql_password);
        // selecting database
        mysql_select_db($mysql_database);
        // return database handler
        return $con;
    }

    // Closing database connection
    public function close() {
        mysql_close();
    }

}

?>