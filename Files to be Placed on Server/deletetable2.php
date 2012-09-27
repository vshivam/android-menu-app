<?php
$con = mysql_connect("localhost","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("db1", $con);

$result = mysql_query("Truncate table2");
echo " Order for Table 2 Deleted";

header('Location:load_data.php');


mysql_close($con);
?> 