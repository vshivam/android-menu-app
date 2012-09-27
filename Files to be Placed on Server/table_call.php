<meta http-equiv="refresh" content="5;url=table_call.php">
<?php
$con = mysql_connect("localhost","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("db1", $con);

$result = mysql_query("Select * From waiterCall");
while($row = mysql_fetch_array($result))
  {
  echo $row['tablecall']." at". $row['callTime'];	
  echo "<br />";
  }
  mysql_close($con);
  ?>
<html>
  </br>
  <form name="input" action="clearcallentries.php" method="get">
<input type="submit" value="Clear Waiter Call Entries" />
</form> 
</html>

 