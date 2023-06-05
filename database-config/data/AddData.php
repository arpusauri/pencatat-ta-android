<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
    $NoInduk    = $_POST['no_induk'];
    $Judul      = $_POST['judul'];
    $Pemilik    = $_POST['pemilik'];
    $Pembimbing = $_POST['pembimbing'];
    $Tempat     = $_POST['tempat_pkl'];
    $Tahun   = $_POST['tahun'];

    $insertSQL  = "INSERT INTO catat (no_induk, judul, pemilik, pembimbing, tempat_pkl, tahun) VALUES ('$NoInduk','$Judul', '$Pemilik', '$Pembimbing', '$Tempat', '$Tahun')";

    require_once('../Connection.php');

    if(mysqli_query($con, $insertSQL)){
        echo 'Data Tugas Berhasil Ditambahkan!';
    }
    else{
        echo 'Gagal saat menambahkan Data Tugas Akhir';
    }

    mysqli_close($con);
}

?>