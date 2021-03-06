<?php 
/**
 * Endpoint: /kame-house-groot/api/v1/test/test.php
 * 
 * Test php functionality
 * 
 * @author nbrest
 */
  main();
?>

<?php

  function main() {
    init();
  
    // print server info. ***** DON'T LEAVE THIS UNCOMMENTED *****
    // Gives a lot of info of the server
    echo "<h1>print server info</h1>";
    //print_r($_SERVER);
  
    // print all headers
    echo "<h1>print all headers</h1>";
    foreach (getallheaders() as $name => $value) {
      echo "$name: $value<br>";
    }
  
    // Test function defined in the same test.php file
    testFunction();
  
    // Functions in examples.php
    isLinuxHostExample();
  }

  function init() {
    require_once("../../../api/v1/commons/global.php");
    require_once("../../../api/v1/commons/examples/examples.php");
  }
  
  /** 
   * I can define functions here as well and call them in the API endpoint 
   */
  function testFunction() {
    echo "<br><br>testFunction: mada mada dane:<br><br>";
  }
?>
