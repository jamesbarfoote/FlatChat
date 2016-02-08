<?php
define('HOST','mysql.hostinger.co.uk');
define('USER','u997523806_user');
define('PASS','pupp33');
define('DB','u997523806_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);

   $FlatGroup_name = $_POST['FlatGroup_name'];
   $email = $_POST['email'];

$t1 = $FlatGroup_name;
$t2 = $email;
   $sql = "UPDATE Users
           SET FlatGroup_name= '$t1'
           WHERE email= '$t2'";
   #$sql = "UPDATE Users ". "SET FlatGroup_name WHERE Email values ('$email','$FlatGroup_name')";
  # $sql = "UPDATE Users ". "SET FlatGroup_name = $name ". "WHERE Email = $email" ;
  if(mysqli_query($con,$sql)){
    echo 'successAGU';
  }
  else{
    echo $FlatGroup_name;
  }
  mysqli_close($con);
?>
