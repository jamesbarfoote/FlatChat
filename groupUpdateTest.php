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

   $t1 = 'a';
   $t2 = 'avo~pear~banana';
   $t3 = 'noevent';
   $t4 = '900~2';
   $t5 = 'getwashing';
   $t6 = '1';

   #$sql = "UPDATE FlatGroups ". "SET SHOPPINGLIST = 'milk~yogert~pasta',  CALENDAR = 'event~anotherevent', MONEY = '9000~8000', TODO = 'nothing', OWNER_ID = '1'". "WHERE GROUP_NAME = 'a'" ;
   $sql = "UPDATE FlatGroups ". "SET SHOPPINGLIST = '$t2',  CALENDAR = '$t3', MONEY = '$t4', TODO = '$t5', OWNER_ID = '$t6'". "WHERE GROUP_NAME = '$t1'" ;

  if(mysqli_query($con,$sql)){
    echo 'successGU';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
?>
