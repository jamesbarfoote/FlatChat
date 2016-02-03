  <?php
  define('HOST','mysql.hostinger.in');
  define('USER','u963422543_user');
  define('PASS','pupp33');
  define('DB','u963422543_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);

   $email = $_POST['email'];
  $password = $_POST['password'];



$sql = "SELECT ID FROM Users WHERE Email='".$email."' AND Password='".$password."'";
 #$sql = "SELECT extra FROM Person WHERE Email='jimmy2174@gmail.com' AND Password='password23'";
 $result = $con->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
        echo "Login Successful";
} else {
    echo "0 results";
}
  mysqli_close($con);
?>
