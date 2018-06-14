<%@page import="fr.epsi.myEpsi.beans.Annonce"%>
<%@page import="fr.epsi.myEpsi.beans.Utilisateur"%>
<%@page import="fr.epsi.myEpsi.Constantes"%>
<%@page import="java.util.ArrayList"  %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>response</title>
</head>
<body>
	<% 
		String attribut;
	    attribut = (String) request.getAttribute("lesAnnonces");       
    %>
    Votre operation à <%=attribut%>

</body>
</html>