package org.ladle.webapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ladle.beans.jpa.Departement;
import org.ladle.beans.jpa.Ville;
import org.ladle.service.RechercheSiteSecteurHandler;

/**
 * Servlet implementation class RechercheSiteSecteur
 */
@WebServlet("/recherche-site-secteur")
public class RechercheSiteSecteur extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LogManager.getLogger(RechercheSiteSecteur.class);

  @EJB(name = "RechercheSiteSecteurHandler")
  private RechercheSiteSecteurHandler rechercheSiteSecteurHandler;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    LOG.debug("Servlet [RechercheSiteSecteur] -> doGet()");

    request.setAttribute("regions", rechercheSiteSecteurHandler.getAllRegions());
    request.setAttribute("departements", rechercheSiteSecteurHandler.getAllDepartements());

    getServletContext().getRequestDispatcher("/WEB-INF/recherche-site-secteur.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    LOG.debug("Servlet [RechercheSiteSecteur] -> doPost()");

    // Création du nom des constantes pour renvoit des infos
    // déjà remplit du form vers la jsp
    final String SELECTED_REGION = "selectedRegion";
    final String SELECTED_DEPT = "selectedDepartement";
    final String INPUTED_CP = "inputedCodePostal";
    final String SELECTED_VILLE = "selectedVille";

    // Récupération des données du post
    String selectedRegion = request.getParameter("inputGroupSelectRegion");
    String selectedDepartement = request.getParameter("inputGroupSelectDepartement");
    String inputedCodePostal = request.getParameter("inputCodePostal");
    String selectedVille = request.getParameter("inputGroupSelectVille");

    // Variable de détection des changements sur le formulaire
    String formChangeOn = request.getParameter("formChangeOn");
    LOG.debug("getParam formChangeOn : {}", formChangeOn);

    // Génération de la liste de sélection des Régions
    request.setAttribute("regions", rechercheSiteSecteurHandler.getAllRegions());

    // Si changement de Région
    switch (formChangeOn) {
      case "region":

        // Renvoit de la Région sélectionnée à la jsp
        request.setAttribute(SELECTED_REGION, selectedRegion);

        // Gestion du cas de sélection : toutes les regions
        if ("all".equals(selectedRegion)) {
          request.setAttribute("departements", rechercheSiteSecteurHandler.getAllDepartements());
          request.setAttribute(SELECTED_DEPT, selectedDepartement);

          // Gestion du cas de sélection : une seule région
        } else {
          List<Departement> departements = rechercheSiteSecteurHandler.getDepartementsByRegionCode(selectedRegion);
          request.setAttribute("departements", departements);

          // Si le Dept sélectionné est contenu dans la liste de résultats
          if (departements.stream().anyMatch(d -> d.getDepartementCode().equals(selectedDepartement))) {
            request.setAttribute(SELECTED_DEPT, selectedDepartement);
            // Sinon reinitialise à 'all' la sélection du departement
          } else {
            request.setAttribute(SELECTED_DEPT, "all");
          }
        }
        break;

      // Si changement de Département
      case "departement":

        request.setAttribute(SELECTED_REGION, selectedRegion);
        request.setAttribute(SELECTED_DEPT, selectedDepartement);

        // Si la Région sélectionné est "tous" recharge la liste complète
        if ("all".equals(selectedRegion)) {
          request.setAttribute("departements", rechercheSiteSecteurHandler.getAllDepartements());
          // Sinon récupère les departements de la region spécifique
        } else {
          request.setAttribute("departements", rechercheSiteSecteurHandler.getDepartementsByRegionCode(selectedRegion));
        }
        break;

      // Si changement de code-postal
      case "code-postal":

        // Récupère la liste des villes avec ce CP
        List<Ville> villes = rechercheSiteSecteurHandler.getVillesByCP(inputedCodePostal);

        // Si le CP est valide et contient des villes
        if (!villes.isEmpty()) {

          // Si le critère région était "all"
          if ("all".equals(selectedRegion)) {

            // Resélectionne le critère région "all"
            request.setAttribute(SELECTED_REGION, "all");

            // Récupère la liste complète des départements
            request.setAttribute("departements", rechercheSiteSecteurHandler.getAllDepartements());

          } else {
            // Récupère le codeRegion de la 1ere ville de la liste
            String regionCode = villes.get(0).getDepartement().getRegion().getRegionCode();

            // Sélectionne la région correspondant au CP
            request.setAttribute(SELECTED_REGION, regionCode);

            // Récupère la liste des départements de la région
            request.setAttribute("departements", rechercheSiteSecteurHandler.getDepartementsByRegionCode(regionCode));
          }
          // Sélectionne le département correspondant au CP
          request.setAttribute(SELECTED_DEPT, villes.get(0).getDepartement().getDepartementCode());

          // Réaffiche le code postal
          request.setAttribute(INPUTED_CP, inputedCodePostal);

          // Si le CP est invalide
        } else {

          // Resélectionne la région et le département précédent
          request.setAttribute(SELECTED_REGION, selectedRegion);
          request.setAttribute(SELECTED_DEPT, selectedDepartement);

          // Si la région sélectionné est "all"
          // recharge la liste complète des départements
          if ("all".equals(selectedRegion)) {
            request.setAttribute("departements", rechercheSiteSecteurHandler.getAllDepartements());

            // Sinon récupère les departements de la region spécifique
          } else {
            request.setAttribute("departements",
                rechercheSiteSecteurHandler.getDepartementsByRegionCode(selectedRegion));
          }
        }

        // Renvoit à la jsp la liste des villes
        request.setAttribute("villes", villes);

        break;

      // Si pas de changement c'est un post du formulaire complet
      default:
        // TODO
    }

    getServletContext().getRequestDispatcher("/WEB-INF/recherche-site-secteur.jsp").forward(request, response);
  }

}
