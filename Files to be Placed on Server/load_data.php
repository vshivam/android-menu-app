<meta http-equiv="refresh" content="5;url=load_data.php">
<?php
$con = mysql_connect("localhost","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("db1", $con);

$result = mysql_query("SELECT * FROM items");
echo "<b><u> Table Number 1 Order</u></b> ";
echo "<br />";
echo "<br />";
while($row = mysql_fetch_array($result))
  {
  echo $row['name'] . " " . $row['qty']." ".$row['price'];
  echo "<br />";
  }
  ?>
  <html>
  </br>
  <form name="input" action="deletetable1.php" method="get">
<input type="submit" value="Clear Table - 1 Entries" />
</form> 
</html>
<?php
echo "<br />";
echo "<br />";
echo "<br />";
echo "<b><u>Table Num 2 Order</u></b>";
echo "<br />";
echo "<br />";
$result = mysql_query("SELECT * FROM table2");

while($row = mysql_fetch_array($result))
  {
  echo $row['name'] . " " . $row['qty']." ".$row['price'];
  echo "<br />";
  }

?> 
<html>
<form name="input" action="deletetable2.php" method="get">	
<br>
<input type="submit" value="Clear Table - 2 Entries" />
</form> 
</html>

<?php
echo "</br>";
echo "<b><u>Total Order</b></u>";
mysql_query("Truncate forder");
$row1 = mysql_query("INSERT INTO forder(name, qty, price) SELECT name, qty, price FROM items UNION SELECT name, qty, price FROM table2");
$result = mysql_query("SELECT name, sum( qty ) As tot_qty , name FROM forder GROUP BY name");
while($row = mysql_fetch_array($result))
  {
  echo "<table>";
  echo "<tr>";
  echo "<td>".$row['tot_qty']."</td><td>".$row['name']."</td>";
  echo "</tr>";
  echo "</table>";
  echo "<br />";
  }
mysql_close($con);
