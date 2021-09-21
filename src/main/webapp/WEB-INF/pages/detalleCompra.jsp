<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

</head>
<body>

   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
   
   <fmt:setLocale value="en_US" scope="session"/>

   <div class="page-title">Order Info</div>

   <div class="cliente-info-container">
       <h3>Customer Information:</h3>
       <ul>
           <li>Name: ${detalleCompraInfo.clienteName}</li>
           <li>Email: ${detalleCompraInfo.clienteEmail}</li>
           <li>Phone: ${detalleCompraInfo.clientePhone}</li>
           <li>Address: ${detalleCompraInfo.clienteAddress}</li>
       </ul>
       <h3>Order Summary:</h3>
       <ul>
           <li>Total:
           <span class="total">
           <fmt:formatNumber value="${detalleCompraInfo.cantidad}" type="currency"/>
           </span></li>
       </ul>
   </div>
   
   <br/>
   
   <table border="1" style="width:100%">
       <tr>
           <th>Product Code</th>
           <th>Product Name</th>
           <th>Quantity</th>
           <th>Price</th>
           <th>Amount</th>
       </tr>
       <c:forEach items="${detalleCompraInfo.detalle}" var="detalleCompraInfo">
           <tr>
               <td>${detalleCompraInfo.productoCidbarras}</td>
               <td>${detalleCompraInfo.productNombre}</td>
               <td>${detalleCompraInfo.cantidad}</td>
               <td>
                <fmt:formatNumber value="${detalleCompraInfo.precio}" type="currency"/>
               </td>
               <td>
                <fmt:formatNumber value="${detalleCompraInfo.cantidad}" type="currency"/>
               </td>  
           </tr>
       </c:forEach>
   </table>
   <c:if test="${paginationResult.totalPages > 1}">
       <div class="page-navigator">
          <c:forEach items="${paginationResult.navigationPages}" var = "page">
              <c:if test="${page != -1 }">
                <a href="orderList?page=${page}" class="nav-item">${page}</a>
              </c:if>
              <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
              </c:if>
          </c:forEach>
           
       </div>
   </c:if>




   <jsp:include page="_footer.jsp" />

</body>
</html>