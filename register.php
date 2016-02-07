<?php
define('HOST','mysql.hostinger.co.uk');
define('USER','u997523806_user');
define('PASS','pupp33');
define('DB','u997523806_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);

   $email = $_POST['email'];
  $password = $_POST['password'];



  $sql = "insert into Users (email,password) values ('$email','$password')";
  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
