<?php
$con = mysql_connect("localhost","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
//echo "1 record added";
mysql_select_db("db1", $con);
//echo "1 record added";
if(strcmp($_POST[deviceId], "ffffffff-9eaf-9658-ffff-ffff99d603a9")==0)
{
$sql="INSERT INTO items (name, qty, price)VALUES('$_POST[name]','$_POST[qty]', '$_POST[price]')";

if (!mysql_query($sql,$con))
  {
  die('Error: ' . mysql_error());
  }
}
else if(strcmp($_POST[deviceId],"ffffffff-9eaf-9658-85e5-e78e0033c587")==0)
 {
	$sql="INSERT INTO table2 (name, qty, price)VALUES('$_POST[name]','$_POST[qty]', '$_POST[price]')";
  //echo "1 record added";
if (!mysql_query($sql,$con))
  {
  die('Error: ' . mysql_error());
  }
}
mysql_close($con);
?> 