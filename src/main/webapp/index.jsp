<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js%22%3E"></script> -->
	<link rel="stylesheet" type="text/css" href="style.css"/>
	<script src="webSocketClient.js"></script>
	<script src="script.js"></script>
</head>
<body background-color:gra>
	<h1>Website For KNX</h1>

	<p>This website will be used for the KNX project</p>

	<hr>
	<br>
	<br>
	
	<div class="center">
	<img src="lightbulb.png" id="L1" alt="light" width="90" height="90">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img id="L2" src="lightbulb.png" alt="light" width="90" height="90">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img id="L3" src="lightbulb.png" alt="light" width="90" height="90">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img id="L4" src="lightbulb.png" alt="light" width="90" height="90">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<br>
	
	<div class="center">
	<!-- <form action="knx" method="post"> -->
		<button name="instruction" class="button0" value="led1" onclick="changeImage('L1')">LED1</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button name="instruction" class="button0" value="led2" onclick="changeImage('L2')">LED2</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button name="instruction" class="button0" value="led3" onclick="changeImage('L3')">LED3</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button name="instruction" class="button0" value="led4" onclick="changeImage('L4')">LED4</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<!-- </form> -->
	</div>
	
	<br>
	<br>
	<hr>
	<br>
	<br>
	
	<h1>Scénarios de démonstration</h1>
	
	<br>
	<br>
	
	<div class="center">
		<!-- <form action="knx" method="post"> -->
			<button name="instruction" class="button1" id="buttonStartChenillard1" value="startChenillard1" onmouseup="startChenillard(1)">Start Chenillard 1</button>
			<button name="instruction" class="button1" id="buttonStartChenillard2" value="startChenillard2" onmouseup="startChenillard(2)">Start Chenillard 2</button>
	  		<button name="instruction" class="button1" id="buttonStopChenillard" value="stopChenillard" onmouseup="stopChenillard()">Stop Chenillard </button>
	  	<!-- </form> -->
	
	<label class="text" for="pet-select">Choose a speed:</label>
	
	<select class="custom-select" name="speed" id="speed-select">
	    <option value="">--Please choose an option--</option>
	    <option value="1">1</option>
	    <option value="2">2</option>
	    <option value="3">3</option>
	    <option value="4">4</option>
	    <option value="5">5</option>
	</select>
	
	</div>
</body>
</html>