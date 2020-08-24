package org.ladle.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ladle.beans.jpa.Region;
import org.ladle.dao.RechercheSiteSecteurDao;

/**
 * Classe de gestion de recherche des sites et des secteurs.
 *
 * @author Coyote
 */
@Stateful
public class RechercheSiteSecteurHandler {

  private static final Logger LOG = LogManager.getLogger(RechercheSiteSecteurHandler.class);

  @EJB(name = "RechercheSiteSecteurDaoImpl")
  private RechercheSiteSecteurDao rechercheSiteSecteurDao;

  public List<Region> getAllRegions() {
    return rechercheSiteSecteurDao.getAllRegions();
  }

}
