<?php
  define('HOST','mysql.hostinger.in');
  define('USER','u963422543_user');
  define('PASS','pupp33');
  define('DB','u963422543_db');
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
