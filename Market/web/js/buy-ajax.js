   var timp = "0";
   function mere(){
            $.ajax({
                url : 'buyPrintListings',
                data: { tm: timp},
                success : function(responseText) {
                    if(responseText != "0") $('#princ').html(responseText);
                    timp = "1";
                }
        });
    }
    $(document).ready(function() {
        mere();
        setInterval(mere,1000);
        $("body").on("click", ".aff", function(){
            var p = $(this).parent().siblings();
             $.ajax({
                url : 'buyPrintListings',
                data: { 
                    tm: "2",
                    seller: p.eq(1).text(),
                    item: p.eq(0).text()
                },
                success : function(responseText) {
                    $('#plm').text(responseText+ " "+p.eq(0).text()+" sold by "+p.eq(1).text());
                }
            });
        });
        $('#home').click(function() { 
            window.location.href="..";
        });  
    });



