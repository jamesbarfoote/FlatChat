  <?php
  define('HOST','mysql.hostinger.co.uk');
  define('USER','u997523806_user');
  define('PASS','pupp33');
  define('DB','u997523806_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);

   $email = $_POST['email'];
  $password = $_POST['password'];



$sql = "SELECT User_ID, email, pic, FlatGroup_Name FROM Users WHERE Email='".$email."' AND Password='".$password."'";
 #$sql = "SELECT extra FROM Person WHERE Email='jimmy2174@gmail.com' AND Password='password23'";
 $result = $con->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "successL," . $row["User_ID"].
		"," . $row["email"].
		"," . $row["pic"].
		"," . $row["FlatGroup_Name"]."<br>";
    }
} else {
    echo "0 results";
}
  mysqli_close($con);
?>
