  <?php
  define('HOST','mysql.hostinger.in');
  define('USER','u205845314_user');
  define('PASS','pupp33');
  define('DB','u205845314_db');
  $con = mysqli_connect(HOST,USER,PASS,DB);
  
   $name = $_POST['GROUP_NAME'];
  $password = $_POST['password'];
  
  

$sql = "SELECT GROUP_ID, SHOPPINGLIST, CALENDAR, MONEY, TODO, OWNER_ID FROM FlatGroups WHERE GROUP_NAME='".$name."' AND Password='".$password."'";
 #$sql = "SELECT extra FROM Person WHERE Email='jimmy2174@gmail.com' AND Password='password23'";
 $result = $con->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "successL," . $row["GROUP_ID"]. 
		"," . $row["SHOPPINGLIST"].
		"," . $row["CALENDAR"].
		"," . $row["TODO"].
		"," . $row["OWNER_ID"].
		"," . $row["MONEY"]."<br>";
    }
} else {
    echo "0 results";
}
  mysqli_close($con);
?>