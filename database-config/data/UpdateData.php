<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$id 		= $_POST['id'];
		$NoInduk    = $_POST['no_induk'];
		$Judul      = $_POST['judul'];
		$Pemilik    = $_POST['pemilik'];
		$Pembimbing = $_POST['pembimbing'];
		$Tempat     = $_POST['tempat_pkl'];
		$Tahun      = $_POST['tahun'];

		require_once('../Connection.php');

		$updateSQL = "UPDATE catat SET no_induk = '$NoInduk', judul = '$Judul', pemilik = '$Pemilik', pembimbing = '$Pembimbing', tempat_pkl = '$Tempat', tahun = '$Tahun' WHERE id = '$id'";

		if(mysqli_query($con,$updateSQL)){
			echo 'Berhasil Update Data';
		}else{
			echo 'Gagal Update Data';
		}

		mysqli_close($con);
	}
?>
