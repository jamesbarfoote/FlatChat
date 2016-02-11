<?php
define('HOST','mysql.hostinger.co.uk');
define('USER','u997523806_user');
define('PASS','pupp33');
define('DB','u997523806_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);

   $GROUP_NAME = $_POST['GROUP_NAME'];
   $SHOPPINGLIST = $_POST['SHOPPINGLIST'];
   $CALENDAR = $_POST['CALENDAR'];
   $MONEY = $_POST['MONEY'];
   $TODO = $_POST['TODO'];
   $OWNER_ID = $_POST['OWNER_ID'];

   #$sql = "UPDATE Users ". "SET FlatGroup_name = 'adminG' ". "WHERE Email = 'jimmy2174@gmail.com'" ;
   $sql = "UPDATE FlatGroups ". "SET FlatGroup_name = $name ". "WHERE GROUP_NAME = $GROUP_NAME" ;
  if(mysqli_query($con,$sql)){
    echo 'successAGU';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
