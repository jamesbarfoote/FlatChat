<?php
define('HOST','mysql.hostinger.co.uk');
define('USER','u997523806_user');
define('PASS','pupp33');
define('DB','u997523806_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);

   $name = $_POST['name'];
  $password = $_POST['password'];



  $sql = "insert into FlatGroups (GROUP_NAME, password) values ('$name','$password')";
  if(mysqli_query($con,$sql)){
    echo 'successGR';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
