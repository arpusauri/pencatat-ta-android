<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

    if(!empty($_POST['username']) && !empty($_POST['password'])){
        $current = true;
    }
    else{
        $current = false;
    }

    if($current){

        $username   = $_POST['username'];
        $password   = $_POST['password'];
    
        require_once('../Connection.php');
    
        $checkSQL  = "SELECT * FROM admin WHERE username = '$username' AND password = ". "'" . $_POST['password'] . "'";
        $savedata = mysqli_query($con, $checkSQL);
    
    
        if(mysqli_num_rows($savedata) > 0){
            echo "Proceed";
        }
        else{
            echo 'Login Gagal';
        }
    
        mysqli_close($con);

    }

}