<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/player.css">
<link rel="stylesheet" href="./css/menu.css">
<title>Player</title>
</head>
<body>
    <div>
        <jsp:include page="menu.jsp" />
    </div>
    <br />
    <div align="center" class="container">
        <p class="title"><b>Player</b></p>
        <form action="player" method="post">
            <table>
                <tr>
                    <td colspan="3">
                        <input class="id_input_data" type="number" min="0" step="1" id="id" name="id" placeholder="Id"
                        value="<c:out value='${player.id}' />">
                    </td>
                    <td>
                        <input type="submit" id="button" name="button" value="Search">
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input class="input_data" type="text" id="name" name="name" placeholder="Name"
                        value="<c:out value='${player.name}' />"> 
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="text" id="birthDate" name="birthDate" placeholder="Birth Date" onfocus="(this.type='date')" onblur="(this.type='text')"
                        value="<c:out value='${player.birthDate}' />">
                    </td>
                    <td>
                        <input class="input_data" type="number" id="height" name="height" placeholder="Height" step="0.01"
                        value="<c:out value='${player.height}' />">
                    </td>
                    <td>
                        <input class="input_data" type="number" id="weight" name="weight" placeholder="Weight" step="0.01"
                        value="<c:out value='${player.weight}' />">
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <select class="input_data" id="team" name="team">
                            <option value="">Select a Team</option>
                            <!-- Add your team options here -->
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <input type="submit" id="button" value="Add Player" name="button">
                        <input type="submit" id="button" value="Update Player" name="button">
                        <input type="submit" id="button" value="Delete Player" name="button">
                        <input type="submit" id="button" value="List Players" name="button">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center" class="feedback">
    	<c:if test="${not empty error}">
        	<h2><b><c:out value="${error}" /></b></h2>
    	</c:if>    
    	<c:if test="${not empty output}">
        	<h3><b><c:out value="${output}" /></b></h3>        
    	</c:if>    
	</div>
    <br />
    <c:if test="${not empty players}">
        <table class="table_round">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Birth Date</th>
                    <th>Height</th>
                    <th>Weight</th>
                    <th>Team</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${players}">
                    <tr>
                        <td><c:out value="${p.id}" /></td>
                        <td><c:out value="${p.name}" /></td>
                        <td><c:out value="${p.birthDate}" /></td>
                        <td><c:out value="${p.height}" /></td>
                        <td><c:out value="${p.weight}" /></td>
                        <td><c:out value="${p.team}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if> 
</body>
</html>