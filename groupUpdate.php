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

   $t1 = $GROUP_NAME;
   $t2 = $SHOPPINGLIST;
   $t3 = $CALENDAR;
   $t4 = $MONEY;
   $t5 = $TODO;
   $t6 = $OWNER_ID;

  # $sql = "UPDATE FlatGroups ". "SET SHOPPINGLIST = $SHOPPINGLIST,  CALENDAR = $CALENDAR, MONEY = $MONEY, TODO = $TODO, OWNER_ID = $OWNER_ID". "WHERE GROUP_NAME = $GROUP_NAME" ;
  $sql = "UPDATE FlatGroups ". "SET SHOPPINGLIST = '$t2',  CALENDAR = '$t3', MONEY = '$t4', TODO = '$t5', OWNER_ID = '$t6'". "WHERE GROUP_NAME = '$t1'" ;

  if(mysqli_query($con,$sql)){
    echo 'successGU';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
