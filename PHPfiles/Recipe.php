
<?php
    $con = mysqli_connect("localhost", "id3586299_zbullock", "kitchen", "id3586299_thekitchendb");
    
    if (mysqli_connect_errno($con)){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $recipename = $_POST["recipename"];
	$servings = $_POST["servings"];
    $cooktime = $_POST["cooktime"];
    $ingredients = $_POST["ingredients"];
    $preparation = $_POST["preparation"];

if(($recipename != NULL)&& ($servings != NULL) && ($cooktime !=NULL) && ($ingredients!=NULL) && ($preparation!=NULL)){
    $statement = mysqli_prepare($con, "INSERT INTO recipe (recipename, servings, cooktime, ingredients, preparation) VALUES (?, ?, ?, ?, ?)");
   mysqli_stmt_bind_param($statement, "siiss", $recipename, $servings, $cooktime, $ingredients, $preparation);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
}

else{
    echo "Values cannot be NULL";
}
?>