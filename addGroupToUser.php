<?php
define('HOST','mysql.hostinger.co.uk');
define('USER','u997523806_user');
define('PASS','pupp33');
define('DB','u997523806_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);

   $name = $_POST['name'];
   $email = $_POST['email'];


   #$sql = "UPDATE Users ". "SET FlatGroup_name = 'adminG' ". "WHERE Email = 'jimmy2174@gmail.com'" ;
   $sql = "UPDATE Users ". "SET FlatGroup_name = $name ". "WHERE Email = $email" ;
  if(mysqli_query($con,$sql)){
    echo 'successAGU';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
