/*
 * Copyright (c) 2018.
 */

$(document).ready(function() {
	$("#register").click(function() {
			var order ={
                		name: {
                			firstName: $("#name").val(),
                			middleName: null,
                			lastName: null,
                		},
                		email: $("#email").val(),
                		addresses: {
                			HomeAddress: {
                				street: null,
                				apartment: null,
                				city: null,
                				state: null,
                				zipCode: null,
                				phoneNumber: null,
                				country: null,
                				locationCoordinatesRest: {
                					longitude: null,
                					latitude: null,
                				}
                			},
            				WorkAddress: {
            					street: $("#profilepicture").val(),
            					apartment: null,
            					city: null,
            					state: null,
            					zipCode: null,
            					phoneNumber: null,
            					country: null,
            					locationCoordinatesRest: {
            						longitude: null,
            						latitude: null,
            					}
            				}	
                		},
                		favouriteCities: [$("#favcity").val()],
                		//profilePhoto: $("#profilepicture").val(),
                };
			var x = document.getElementById("email").value;
			var urlval = "http://weather-api.sulekha.rocks:8080/userprofile/update-user-profile/" + x +"/";
			urlval = urlval.trim();
			$.ajax({
                	type: 'PUT',
                	url: urlval,
                	data: JSON.stringify(order),
                	contentType: "application/json",
                	success: function(response){
                		alert('successfully updated user profile');
        				var myJSON = JSON.stringify(response);
        				//document.getElementById("fName").innerHTML = myJSON; 
        				localStorage.setItem('myObject', myJSON);
                		//console.log(response);
                	},
                	error: function(response){
                		alert('Unable to update profile at this time');
                		console.log(response);
                	}
                });
			
			/*var volcano= new FormData();
			
			var locationPhoto;
			var locationName;
			var city;
			var state;
			var zipCode;
			var phoneNumber;
			var country;
			var longitude;
			var latitude;
			var street;
			var email;
			
			volcano.append(locationPhoto,$("#profilepicture").val());
			volcano.append(locationName, null);
			volcano.append(city, null);
			volcano.append(state, null);
			volcano.append(zipCode, null);
			volcano.append(zipCode, null);
			volcano.append(phoneNumber, null);
			volcano.append(country, null);
			volcano.append(longitude, null);
			volcano.append(latitude, null);
			volcano.append(street, null);
			volcano.append(email,$("#email").val());
			
			var urlval = "http://weather-api.sulekha.rocks:8080/locations-photos/upload-location-photo";
			urlval = urlval.trim();
			$.ajax({
                	type: 'POST',
                	url: urlval,
                	contentType: false,
                	processData: false,
                	mimeType: "multipart/form-data",
                	data: volcano,
                	success: function(response){
                		alert('successfully uploaded location photo');
                		console.log(response);
                	},
                	error: function(response){
                		alert('Unable to upload location photo at this time');
                		console.log(response);
                	}
                });*/
});
});