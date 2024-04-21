<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/style.css" >
<div align="center">
	<div>
	<a href="${pageContext.request.contextPath}/admin/">
		<img src="../images/BookstoreLogo.png" />
		</a>
	</div>
	<div>
		Welcome , <c:out value="${sessionScope.useremail }"/> | <a href="logout">Logout</a> <br /> <br />
	</div>
	

	<div id="headermenu">
	
		<div class = "menu_item" >
			<a href="list_users">
			 <img src="../images/users.png" /><br/>Users
			</a>
		</div>

		<div class = "menu_item">
			<a href="list_category">
			 <img src="../images/category.png" /><br/>Categories
			</a>
		</div>
		
		<div class = "menu_item" >
			<a href="list_books"> 
			<img src="../images/bookstack.png" /><br/>Books
			</a>
		</div>
		
		<div  class = "menu_item">
			<a href="list_customer"> 
			<img src="../images/customer.png" /><br/>Customers
			</a>
		</div>
		
		<div  class = "menu_item">
			<a href="list_review"> 
			<img src="../images/review.png" /><br/>Reviews
			</a>
		</div>
		
		<div>
			<a href="list_order"> 
			<img src="../images/order.png" /><br/>Orders
			</a>
		</div>
		
	</div>
</div>