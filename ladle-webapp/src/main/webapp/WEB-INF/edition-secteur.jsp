<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<title>Edition de secteur</title>
<link rel="icon" href="favicon.ico">
<%@ include file="/WEB-INF/parts/meta.jsp" %>
</head>
<body>
  <%@ include file="/WEB-INF/parts/header.jsp" %>

  <div class="container ladle-bg-main">

    <h1>Edition du secteur :</h1>
    
    ${secteur.site.ville.nom} - <fmt:formatDate value="${secteur.dateLastMaj}" type="date" /> <br><br>
    
    <%-- Formulaire de modification du secteur  --%>
    <form method="post" action="edition-secteur">
    
      <%-- Stockage de l'ID du secteur --%>
      <input id="secteurID" name="secteurID" type="hidden" value="${secteur.secteurID}">
      
      <%-- Nom du secteur --%>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text" id="labelNomSecteur">Nom du secteur</span>
        </div>
        <input id="secteurNom" name="secteurNom" type="text" class="form-control" value="${secteur.nom}" 
        aria-label="Nom du secteur" aria-describedby="labelNomSecteur">
      </div>
      
      <%-- Descriptif du secteur --%>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text">Descriptif du secteur</span>
        </div>
        <textarea id="secteurDescriptif" name="secteurDescriptif" class="form-control" 
        aria-label="Zone de texte">${secteur.descriptif}</textarea>
      </div>
      
      <%-- Accès au secteur --%>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text">Accès au secteur</span>
        </div>
        <textarea id="secteurAcces" name="secteurAcces" class="form-control" 
        aria-label="Zone de texte">${secteur.acces}</textarea>
      </div>
      
      <%-- Plan du secteur --%>
      <img class="img-fluid my-3" src="data:image/jpg;base64,${secteur.plan}" 
      width="${secteurPlanWidth}" height="${secteurPlanHeight}"
      alt="Plan du secteur.">
      
      <%-- Table avec les voies du secteur --%>
      <table class="table">
          <caption>voies</caption>
          <thead class="thead-dark">
            <tr>
              <th id="labelNumVoie" scope="col">Numéro</th>
              <th id="labelCotationVoie" scope="col">Cotation</th>
              <th id="labelNomVoie" scope="col">Nom</th>
              <th id="labelHauteurVoie" scope="col">Hauteur (m)</th>
              <th id="labelDegaineVoie" scope="col">Dégaines</th>
              <th id="labelRemarqueVoie" scope="col">Remarques</th>
            </tr>
          </thead>
          <%-- Ajout de la variable d'itération --%>
          <c:set var="voieIteration" value="0" />
          
          <%-- Boucle sur toutes les voies du secteur --%>
          <c:forEach items="${secteur.voies}" var="voie">
          <c:set var="voieIteration" value="${voieIteration + 1}" />
          
            <%-- Ligne du tableau représentant une voie --%>
            
            <%-- ID de la voie en input caché --%>
            <input id="voieID${voieIteration}" name="voieID${voieIteration}" 
                   type="hidden" required value="${voie.voieID}">
            <tr>
              <%-- Numéro de la voie --%>
              <th scope="row">
                <input id="numVoie${voieIteration}" name="numVoie${voieIteration}" 
                  class="form-control" type="text" required value="${voie.numero}" 
                  required maxlength="6" pattern="[1-9][0-9]{0,2}(bis|ter)?"
                  oninvalid="this.setCustomValidity('Le numéro va de 1 à 999 et peut être suivit de bis ou ter. Ex: 42 ou 42bis')"
                  onchange="this.setCustomValidity('')"
                  aria-label="Numéro de la voie" aria-describedby="labelNumVoie">
              </th>
              <%-- Cotation de la voie --%>
              <td>
                <input id="cotationVoie${voieIteration}" name="cotationVoie${voieIteration}" 
                  class="form-control" type="text" value="${voie.cotation}" 
                  maxlength="3" pattern="[3-9]([abc]\+?)?"
                  oninvalid="this.setCustomValidity('La cotation va de 3 à 9 et peut être suivit de la lettre a, b ou c suivit ou non de +. Ex: 4 ou 4b+')"
                  onchange="this.setCustomValidity('')"
                  aria-label="Cotation de la voie" aria-describedby="labelCotationVoie">
              </td>
              <%-- Nom de la voie --%>
              <td>
                <input id="nomVoie${voieIteration}" name="nomVoie${voieIteration}" 
                  class="form-control" type="text" value="${voie.nom}" 
                  maxlength="45" aria-label="Nom de la voie" aria-describedby="labelNomVoie">
              </td>
              <%-- Hauteur de la voie --%>
              <td>
                <input id="hauteurVoie${voieIteration}" name="hauteurVoie${voieIteration}" 
                  class="form-control" type="number" value="${voie.hauteur}" 
                  maxlength="3" pattern="[1-9][0-9]{0,2}" min="1" max="999"
                  oninvalid="this.setCustomValidity('La hauteur en mètres va de 1 à 999. Ex: 42')"
                  onchange="this.setCustomValidity('')"
                  aria-label="Hauteur de la voie" aria-describedby="labelHauteurVoie">
              </td>
              <%-- Nombre de dégaines de la voie --%>
              <td>
                <input id="degaineVoie${voieIteration}" name="degaineVoie${voieIteration}" 
                  class="form-control" type="number" value="${voie.degaine}" 
                  maxlength="2" pattern="([0-9])|([1-9][0-9])" min="0" max="99"
                  oninvalid="this.setCustomValidity('Le nombre de dégaines va de 0 à 99. Ex: 12')"
                  onchange="this.setCustomValidity('')"
                  aria-label="Nombre de dégaines de la voie" aria-describedby="labelDegaineVoie">
              </td>
              <%-- Remarque sur la voie --%>
              <td>
                <textarea id="remarqueVoie${voieIteration}" name="remarqueVoie${voieIteration}" 
                  class="form-control" maxlength="255" 
                  aria-label="Remarques sur la voie" aria-describedby="labelRemarqueVoie"
                >${voie.remarque}</textarea>
              </td>
            </tr>
          </c:forEach>
        </table>
        
        <%-- Input hidden du nombre de voies --%>
        <input id="nombreDeVoies" name="nombreDeVoies" 
          type="hidden" required value="${voieIteration}">
        
        <button class="btn btn-outline-primary" type="submit" 
        name="submit-btn" value="submit">Valider</button>
      
      </form>
    
  </div>

  <%@ include file="/WEB-INF/parts/footer.jsp" %>

</body>
</html>