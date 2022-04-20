<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dineroHome</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
      .suwaStyle {
          font-size: 30px;
          font-family: Microsoft JhengHei;
          background-color: #FFDA29;

          text-align: center;
          border-radius: 40px 10px;
      }

      a {
          color: #263056;
          text-decoration: none;
      }

      .upperMargin{
        margin-top: 30px;
      }

      .col-md-auto{
        width: 15%;
        border-radius: 40px 10px;
      }
      
      input[type=submit] {
    	
    	background:#ccc; 
    	border:0 none;
    	background-color: transparent;
	}
	
	.warning{
		color:white;
		font-size: 30px;
		border-radius:  10px;
		background: #EA5252;
		text-align: center;
		font-family: Microsoft JhengHei;
		width : 3 vh;
		font-family: Microsoft JhengHei;
	}
  </style>

</head>
<body>
	<jsp:include page="dineroNavBar.jsp"></jsp:include>
	
  <div class="container">
    <div class="row justify-content-md-center">
    	
    	<div style="text-align: center;margin-top:20px;">
	   <span class=warning ">${requestScope.notLogIn} </span> </div>	
		
      <div class="col-md-auto list-group-item list-group-item-action list-group-item-dark suwaStyle upperMargin">
        <form action="<%=request.getContextPath() %>/HomeLoginController" method="post">
        	<input type="hidden" value="toUser" name="homeTowhere"/>
        	<input type="submit" value="User" />
        </form>
      </div>

    </div>
    <div class="row justify-content-md-center">

      <div class="col-md-auto list-group-item list-group-item-action list-group-item-dark suwaStyle upperMargin">
         <form action="<%=request.getContextPath() %>/HomeLoginController" method="post">
        	<input type="hidden" value="toProduct" name="homeTowhere"/>
        	<input type="submit" value="Product" />
        </form>
      </div>

    </div>
    <div class="row justify-content-md-center">

      <div class="col-md-auto  list-group-item list-group-item-action list-group-item-dark suwaStyle upperMargin">
        <form action="<%=request.getContextPath() %>/HomeLoginController" method="post">
        	<input type="hidden" value="toOrd" name="homeTowhere"/>
        	<input type="submit" value="Order" />
        </form>
      </div>

    </div>
    <div class="row justify-content-md-center">

      <div class="col-md-auto list-group-item list-group-item-action list-group-item-dark suwaStyle upperMargin">
         <form action="<%=request.getContextPath() %>/HomeLoginController" method="post">
        	<input type="hidden" value="toBulletin" name="homeTowhere"/>
        	<input type="submit" value="Bulletin" />
        </form>
      </div>

    </div>
    
     <div class="row justify-content-md-center">

      <div class="col-md-auto list-group-item list-group-item-action list-group-item-dark suwaStyle upperMargin">
        <form action="<%=request.getContextPath() %>/HomeLoginController" method="post">
        	<input type="hidden" value="toActivity" name="homeTowhere"/>
        	<input type="submit" value="Activity" />
        </form>
      </div>

    </div>
  </div>
</body>
	
</body>
</html>