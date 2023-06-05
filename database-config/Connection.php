<?php

    $server   = "localhost";
    $user     = "root";
    $password = "";
    $database = "android_ta";

    $con = mysqli_connect($server, $user, $password, $database);
    if (mysqli_connect_errno()) {
        echo "Gagal terhubung ke MySQL: " . mysqli_connect_error();
    }

?>