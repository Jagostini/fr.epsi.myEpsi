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
<title>Insert title here</title>
</head>
<body>
 <h1> Annonce disponible </h1>
 <table>
   <tr>
 	  <th>Titre</th>
 	  <th>Prix</th>
 	  <th>Resume</th>
 	  <th>Vendeur</th>
 	  <th>Status</th>
 	  <th>Date de modification</th>
 	  <th>Annonce cree le </th>
   </tr>
   <% 
       ArrayList<Annonce> attribut = new ArrayList<Annonce>(); 
	   attribut = (ArrayList<Annonce>) request.getAttribute("lesAnnonces");
	   
       for (Annonce annonce : attribut) {
            
    %>
	<tr>
		<td>
			<%=annonce.getTitre()%>
		</td>
		<td>
			<%=annonce.getPrix()%>
		</td>
		<td>
			<%= annonce.getDescription()%>
		</td>
		<td>
			<%= annonce.getVendeur().getId() %>
		</td>
		<td>
			<%= annonce.getStatut()%>
		</td>
		<td>
			<%=annonce.getModification()%>
		</td>
		<td>
			<%= annonce.getCreation() %>
		</td>
	</tr>
	<%}%>
  </table>
  
  <form method="post" action="MesAnnonces">
      <fieldset>
         <input type="submit" value="Mes annonces" class="sansLabel" />
      </fieldset>
   </form>
	
<!-- <%// Utilisateur utilisateur = (Utilisateur) session.getAttribute(Constantes.PARAM_UTILISATEURS); %>
<% //utilisateur.getId() %> -->
</body>
</html>