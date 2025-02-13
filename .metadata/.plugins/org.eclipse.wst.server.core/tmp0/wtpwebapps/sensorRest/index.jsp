<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width-device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie-edge">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<style>
</style>

</head>


<body>
	<h2>Fire Alarm Sensor Details</h2>

	<table class="table table-stripped">

		<!-- Table Header is set -->
		<tr>
			<th>Sensor ID</th>
			<th>Status</th>
			<th>Floor Number</th>
			<th>Room Number</th>
			<th>Smoke Level</th>
			<th>CO2 Level</th>
		</tr>

		<!-- Table Body is set with an id as data -->
		<tbody id="data">

		</tbody>

	</table>

	<!-- Start of script tag -->
	<script>
    
    //The main function
    function startFetchingData() {
    	
    	//This function sets a time which re calls the same function again
    	setInterval(function () {
    		
    		//Fetch the response received from the path which returns the data
    		 //in JSON format -->
    		fetch("http://localhost:8080/sensorRest/webapi/sensors").then(
    	    		function(res) {
    	    			// getting the JSON type data from the response and passing
    	    			 //it onto a function -->
    	    			res.json().then(
    	    				function(data) {
    	    					//To ensure that the data is getting retrieved -->
    	    					console.log(data);
    	    					// If data is not empty -->
    	    					if(data.length > 0) {
    	    						// creating a temporary variable for holding tags and data that
    	    						 //is going to be overwritten-->
    	    						var temp = "";

    	    						// Accessing the data one by one and passing the content
    	    						 //into an object as u -->
    	    						data.forEach(function(u) {
    	    							
    	    							// checking if the smoke level or co2 level is above 5
    	    							 //if yes all the text color in that section would have
    	    							 //a style of red color -->
    	    							if((u.sLevel > 5) || (u.cLevel > 5)) {
    	    								
    	    								temp += "<tr>";
    	        							temp += '<td style="color:red">' + u.id + "</td>";
    	        							
    	        							temp += '<td style="color:red">' + u.status + "</td>";
    	        							
    	        							temp += '<td style="color:red">' + u.floorNum + "</td>";
    	        							temp += '<td style="color:red">' + u.roomNum + "</td>";
    	        							temp += '<td style="color:red">' + u.sLevel + "</td>";
    	        							temp += '<td style="color:red">' + u.cLevel + "</td></tr>";
    	    							}
    	    							else {
    	   
    	    								// if there is no need of changing colors, it is set normally
    	    								 //as a row without any beautification -->
    	    								temp += "<tr>";
    	        							temp += "<td>" + u.id + "</td>";
    	        							
    	        							temp += "<td>" + u.status + "</td>";
    	        							
    	        							temp += "<td>" + u.floorNum + "</td>";
    	        							temp += "<td>" + u.roomNum + "</td>";
    	        							temp += "<td>" + u.sLevel + "</td>";
    	        							temp += "<td>" + u.cLevel + "</td></tr>";
    	    							}
    	    							
    	    						});
    	    						//----- close for loop
    	    						
    	    						 // the data element is overwritten with the codes in temp variable -->
    	    						document.getElementById("data").innerHTML = temp;
    	    					}
    	    				}		
    	    			)
    	    		}
    	    )
    	    console.log('-----------15 seconds----------');
    	},15000);
    	
    }
    
    //when the DOM is loaded, the main method is called
    document.addEventListener('DOMContentLoaded', function () {
    	startFetchingData();
    });
    
    // End of script tag -->
    </script>
</body>
</html>
