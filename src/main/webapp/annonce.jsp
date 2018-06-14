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
<table>
   <tr>
   	  <th>Id</th>
 	  <th>Titre</th>
 	  <th>Prix</th>
 	  <th>Resume</th>
 	  <th>Statut</th>
   </tr>
   <% 
       ArrayList<Annonce> attribut = new ArrayList<Annonce>(); 
	   attribut = (ArrayList<Annonce>) request.getAttribute("lesAnnonces");
	   
       for (Annonce annonce : attribut) {
            
    %>
	<tr>
		<td>
			<%=annonce.getId()%>
		</td>
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
			<%= annonce.getStatut()%>
		</td>
	</tr>
	<%}%>
  </table>
  
  <h3> Renseigner le numero de l'annonce pour la supprimer</h3>
  <form method="post" action="DeleteAnnonce">
  	<fieldset>
  		<label for="Numero de l'annonce">Pseudo <span class="requis">*</span></label>
    		<input type="text" name="id" value="" size="20" maxlength="60" />
    		<br/>
    		<input type="submit" value="Supprimer" class="sansLabel" />
    </fieldset>
   </form>
   <h3> Renseigner le nom de l'annonce afin de la modifier</h3>
  <form method="post" action="ModifierAnnonce">
  	<fieldset>
    		<input type="submit" value="Modifier" class="sansLabel" />
    </fieldset>
   </form>
      <h3> Ajouter une annonce</h3>
  <form method="post" action="AjoutAnnonce">
  	<fieldset>
  		<label for="id de l'annonce">id de l'annonce <span class="requis">*</span></label>
    		<input type="text" name="id" value="" size="20" maxlength="60" />
    		<br/>
    		
    		<label for="Titre de l annonce">Titre de l annonce <span class="requis">*</span></label>
    		<input type="text" name="titre" value="" size="20" maxlength="60" />
    		<br/>
    		
    		<label for="Prix">Prix <span class="requis">*</span></label>
    		<input type="text" name="prix" value="" size="20" maxlength="60" />
    		<br/>
    		
    		<label for="Resume">Resume <span class="requis">*</span></label>
    		<input type="text" name="resume" value="" size="20" maxlength="60" />
    		<br/>
    		
    		<input type="submit" value="ajouter" class="sansLabel" />
    </fieldset>
   </form>
   
   <h3> Modifier une annonce</h3>
  <form method="post" action="ModifierAnnonce">
  	<fieldset>
  		<label for="Id de l'annonce à modifier">Id de l'annonce à modifier <span class="requis">*</span></label>
    		<input type="text" name="id" value="" size="20" maxlength="60" />
    		<br/>
    		
  		<label for="Titre de l annonce">Titre de l annonce <span class="requis">*</span></label>
    		<input type="text" name="titre" value="" size="20" maxlength="60" />
    		<br/>
    		
    		<label for="Prix">Prix <span class="requis">*</span></label>
    		<input type="text" name="prix" value="" size="20" maxlength="60" />
    		<br/>
    		
    		<label for="Resume">Resume <span class="requis">*</span></label>
    		<input type="text" name="resume" value="" size="20" maxlength="60" />
    		<br/>
    		
    		<input type="submit" value="Modifier" class="sansLabel" />
    </fieldset>
   </form>
</body>
</html>