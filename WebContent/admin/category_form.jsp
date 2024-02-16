<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Category - Evergreen Bookstore Administration</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />


	<diV align="center">
		<h2>
			<c:if test="${category != null}">
			Edit Category
			</c:if>
			<c:if test="${category == null}">
			Create New Category
			</c:if>
		</h2>
	</diV>

	<diV align="center">
		<c:if test="${category != null}">
			<form id="form-1" action="update_category" method="post"
				onsubmit="return validateFormInput() ">
				<input type="hidden" name="categoryId" value=${category.categoryId}>
		</c:if>
		<c:if test="${category == null}">
			<form id="form-1" action="create_category" method="post"
				onsubmit="return validateFormInput() ">
		</c:if>
		<form id="form-1" action="create_category" method="post"
			onsubmit="return validateFormInput() ">
			<table id="table-1">
				<tbody>
					<tr>
						<td align="right">Name:</td>
						<td><input type="text" name="categoryName" id="category"
							size="20" value=${category.name}></td>
					</tr>
					<tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Save:"> <input type="button" value="Cancel"
							onClick="javascript:history.go(-1);"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</diV>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	
	function validateFormInput() {
		const categoryField = document.getElementById("category");

		if (categoryField.value.length == 0) {
			alert("Category name is required!!");
			categoryField.focus();
			return false;
		}

	}
</script>
</html>