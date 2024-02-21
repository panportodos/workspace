<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>가격</th>
				<th>사이즈</th>
			</tr>
		</thead>
		<tbody>

		<c:forEach var="dto" items="productList"> <!-- <%%>이걸 안쓰는방향으로 설계한다. 코어(c)에 다 있다 -->
			<c:if test="${dto.productId % 2 == 0 }">
			<tr>
				<td>${ dto.getProductId }</td> <!-- 게터가 없으면 쓸 수 가 없다 -->
				<td>${ dto.getProductName }</td>
				<td>${ dto.getProductPrice }</td>
				<td>${ dto.getProductSize }</td>
			</tr>
			</c:if>
			
			<c:choose>  <!-- if문을 여러개 쓰고 싶으면 choose문을 써야한다 -->
				<c:when test="">
				</c:when>
				<c:when test="">
				</c:when>
				<c:when test="">
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</c:forEach>	
		</tbody>
	</table>

</body>
</html>