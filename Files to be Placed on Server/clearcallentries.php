<?php
$con = mysql_connect("localhost","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("db1", $con);

$result = mysql_query("TRUNCATE waiterCall");
mysql_close($con);
header('Location:table_call.php');



?> 