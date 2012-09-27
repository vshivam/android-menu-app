<?php
$con = mysql_connect("localhost", "menu", "abc");
mysql_select_db("menu",$con);


$user = $_POST['number'];

$q = mysql_query("INSERT INTO order name = '".$user. "'");
$e = mysql_fetch_array($q) or die(mysql_error());
echo $e['name'];
echo $e['qty'];
echo ("^");

mysql_close($con);
?>