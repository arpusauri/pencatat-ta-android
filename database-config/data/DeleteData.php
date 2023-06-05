<?php

    $id = $_GET['id'];

    require_once('../Connection.php');

    $deleteSQL = "DELETE FROM catat WHERE id='$id'";

    if(mysqli_query($con,$deleteSQL)){
    echo 'Berhasil Menghapus Data';
    }else{
    echo 'Gagal Menghapus Data';
    }

     mysqli_close($con);
 ?>
