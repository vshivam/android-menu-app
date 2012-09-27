<?php
$con = mysql_connect("localhost","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("db1", $con);

$result = mysql_query("Truncate items");
echo " Order For Table 1 Deleted ";
header('Location:load_data.php');
mysql_close($con);
?> 
