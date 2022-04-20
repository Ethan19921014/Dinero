    
$(document).ready(function(){

    
function check(){
        
    var username =$("#username").val();

    if(username.length >= 3)
    {
        $.ajax({
            url:"check.jsp",
            type:"post",
            data:"uname="+username,
            dataType:"text",
            success:function(data)
            {
                $("#available").html(data)
            } 
        });
    }
    else
    {
      $("#available").html(" ");
    }
    
}


   
    $("#username").blur(check);

   });    
  

