$(document).ready(function() {
        $('#home').click(function() { 
            window.location.href="..";
        });
	$('#sub').click(function() {
            var s = $('#seller').val().trim();
            var i = $('#item').val().trim();
            var d = $('#description').val().trim();
            var p = $('#price').val().trim();
            if(!(s && i && d && p)){
                    $('#msg').text("Inputs not completed.");
                    $('#msg').css("color","red");
            } 
            else if(isNaN(p)){                    
                $('#msg').text("The price is incorect.");
                $('#msg').css("color","red");
            }        
           else{
		$.ajax({
			url : 'insertSell',
			data : {
				sellerName : s,
                                itemName : i, 
                                desc : d,
                                pricing: p
                                
			},
			success : function(responseText) {
				$('#msg').text(responseText);
                                
			}
		});
                $('#msg').css("color","green");
            }
	});
});

