package org.ladle.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ladle.dao.EditeSiteSecteurDao;

/**
 * Classe de gestion des ajouts et editions des Sites et des Secteur.
 *
 * @author Coyote
 */
@Stateless
public class EditeSiteSecteurHandler {

  @SuppressWarnings("unused")
  private static final Logger LOG = LogManager.getLogger(EditeSiteSecteurHandler.class);

  @EJB(name = "EditeSiteSecteurDaoImpl")
  private EditeSiteSecteurDao editeSiteSecteurDao;
}
