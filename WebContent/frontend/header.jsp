<div align="center">
	<div>
		<img alt="logo" src="images/BookstoreLogo.png">
	</div>
	<div>
		<input type="text" name="keyword" size="50" /> <input type="button"
			value="search" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
			href="Login">Sign In</a> | <a href="register">Register</a> | <a
			href="view_cart">Cart</a>
	</div>
	<div>

		<c:forEach var="category" items="${listCategory}" varStatus="status">
			<a href="view_category?id=${category.categoryId}"> 
				<font size="+1"><b><c:out value="${category.name}" /></b></font>
			</a>
			<c:if test="${not status.last}">
			&nbsp; | &nbsp;
			</c:if>

		</c:forEach>
	</div>
</div>