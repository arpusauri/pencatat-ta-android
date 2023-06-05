<?php 
 
	require_once('../Connection.php');

	if(isset($_GET['id'])) {
		$id = $_GET['id'];
	}
	$result = array();
	$query = mysqli_query($con, "SELECT * FROM catat ORDER BY id ASC");

	while ($row = mysqli_fetch_assoc($query)){
		$result[] = $row;
	}
	
	echo json_encode(array('result'=>$result));
  
 ?>