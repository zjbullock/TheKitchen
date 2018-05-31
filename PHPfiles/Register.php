
<?php
    $con = mysqli_connect("localhost", "id3586299_zbullock", "kitchen", "id3586299_thekitchendb");
    
    if (mysqli_connect_errno($con)){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $firstname = $_POST["firstname"];
	$lastname = $_POST["lastname"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $email = $_POST["email"];

if(($firstname != NULL)&& ($lastname != NULL) && ($username !=NULL) && ($password!=NULL) && ($email!=NULL)){
    $statement = mysqli_prepare($con, "INSERT INTO user (firstname, lastname , username, password, email) VALUES (?, ?, ?, ?, ?)");
   mysqli_stmt_bind_param($statement, "sssss", $firstname, $lastname, $username, $password, $email);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
}

else{
    echo "Values cannot be NULL";
}
?>