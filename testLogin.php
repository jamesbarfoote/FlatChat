<?php
  define('HOST','mysql.hostinger.in');
  define('USER','u205845314_user');
  define('PASS','pupp33');
  define('DB','u205845314_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);
  
   $email = $_POST['email'];
  $password = $_POST['password'];
  
  

  $sql = "SELECT ID, email, pic, flatgroup FROM Users WHERE Email='jimmy2174@gmail.com' AND Password='password23'";
 $result = $con->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "successL," . $row["ID"].
		"," . $row["email"].
		"," . $row["pic"]
		"," . $row["flatgroup"];
    }
} else {
    echo "0 results";
}
  mysqli_close($con);
?>