<?php
    $con = mysqli_connect("localhost", "id3586299_zbullock", "kitchen", "id3586299_thekitchendb");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $firstname, $lastname, $username, $password, $email);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
          $response["firstname"] = $firstname;
			$response["lastname"] = $lastname;
           	$response["username"]=$username;
           	$response["password"]=$password;
            $response["email"] = $email;
		
        }
    
    echo json_encode($response);
?>