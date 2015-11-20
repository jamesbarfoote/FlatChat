<?php
  define('HOST','mysql.hostinger.in');
  define('USER','u205845314_user');
  define('PASS','pupp33');
  define('DB','u205845314_db');
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