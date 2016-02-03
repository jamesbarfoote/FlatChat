<?php
  define('HOST','mysql.hostinger.in');
  define('USER','u963422543_user');
  define('PASS','pupp33');
  define('DB','u963422543_db');
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
