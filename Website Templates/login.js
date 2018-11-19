$(document).ready(function() {
	$("#login").click(function() {
		var x = document.getElementById("email").value;
		var urlval = "http://weather-api.sulekha.rocks:8080/userprofile/get-user-profile/" + x +"/";
		jQuery.ajax({
            url: urlval,
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            success: function(resultData) {
				var myJSON = JSON.stringify(resultData);
				document.getElementById("fName").innerHTML = myJSON; 
				
				localStorage.setItem('myObject', myJSON);
				window.location = "userprofile.html";
            },
            error : function(jqXHR, textStatus, errorThrown) {
            alert('Email Id does not exist');
			
			},

        });
	});
});
