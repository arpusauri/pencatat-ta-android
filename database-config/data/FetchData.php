<?php
	$id = $_GET['id'];

	require_once('../Connection.php');

	$getSQL = "SELECT * FROM catat WHERE id='$id'";

	$r = mysqli_query($con,$getSQL);

	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id"=>$row['id'],
			"no_induk"=>$row['no_induk'],
			"judul"=>$row['judul'],
			"pemilik"=>$row['pemilik'],
			"pembimbing"=>$row['pembimbing'],
			"tempat_pkl"=>$row['tempat_pkl'],
			"tahun"=>$row['tahun'],
		));

	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>
