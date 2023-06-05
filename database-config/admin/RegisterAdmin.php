<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
    $username   = $_POST['username'];
    $password   = $_POST['password'];

    $insertSQL  = "INSERT INTO admin (username, password) VALUES ('$username','$password')";

    require_once('../Connection.php');

    if(mysqli_query($con, $insertSQL)){
        echo 'Halo, selamat datang' . $username;
    }
    else{
        echo 'Gagal saat menambahkan Data';
    }

    mysqli_close($con);
}