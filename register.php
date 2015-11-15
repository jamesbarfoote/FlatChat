<?php
  define('HOST','mysql.hostinger.in');
  define('USER','u205845314_user');
  define('PASS','pupp33');
  define('DB','u205845314_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);
  
   $name = $_POST['name'];
  $address = $_POST['address'];
  
  

  $sql = "insert into Persons (name,address) values ('$name','$address')";
  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>