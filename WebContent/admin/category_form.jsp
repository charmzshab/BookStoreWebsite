<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create  Review</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${category == null}">
			Create New Category
		</c:if>
			<c:if test="${category != null}">
			Edit Category
		</c:if>
		</h2>


		<div align="center">
			<c:if test="${category != null}">
				<form action="update_category" method="post"
					onsubmit="validateFormInput()" />
				<input type="hidden" name="categoryId"
					value="${category.categoryId }">
			</c:if>

			<c:if test="${category == null}">
				<form action="create_category" method="post"
					onsubmit="validateFormInput()" />
			</c:if>

			<table class="form">
				<tr>
					<td align="right">Category Name:</td>
					<td align="left"><input type="text" id="name" name="name"
						value="${category.name}" size="20"></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
						<button onclick="javascript:history.go(-1);">Cancel</button> </tr>

			</table>
			</form>
		</div>


		<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function validateFormInput() {
		var fieldFullName = document.getElementById("name");
		if (fieldFullName.value.length == 0) {
			alert("Category Name  is required");
			fieldFullName.focus();
			return false;
		}
		return true;

	}
</script>
</html>