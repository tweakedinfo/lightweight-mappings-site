package courses

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import scala.scalajs.js.JSConverters._

/**
 * ACM Computing Competencies for Undergraduate Data Science Curricula
 */ 
enum CCDSC(val name:String) extends GridCategory:
  case AP extends CCDSC("Analytics and Presentation")
  case AI extends CCDSC("Artificial Intelligence")
  case BDS extends CCDSC("Big Data Systems")
  case CCF extends CCDSC("Computing and Computer Fundamentals")
  case DG extends CCDSC("Data Acquisition, Management, and Governance")
  case DM extends CCDSC("Data Mining")
  case DP extends CCDSC("Data Privacy, Security, Integity, and Analysis for Security")
  case ML extends CCDSC("Machine Learning")
  case PR extends CCDSC("Professionalism")
  case PDA extends CCDSC("Professionalism, Data Structures, and Algorithms")
  case SDM extends CCDSC("Software Development and Maintenance")

  def css = "ccdsc"

@JSExportTopLevel("ccdsc")
val ccdscjs = (for e <- CCDSC.values yield e.productPrefix -> e).toMap.toJSDictionary


enum EdisonDSBOK(val name:String, val css:String) extends GridCategory:
  case SMDA extends EdisonDSBOK("SMDA Statistical Methods for Data Analysis", "edison dsda")
  case ML extends EdisonDSBOK("ML Machine Learning", "edison dsda")
  case DM extends EdisonDSBOK("DM Data Mining", "edison dsda")
  case TDM extends EdisonDSBOK("TDM Text Data Mining", "edison dsda")
  case PA extends EdisonDSBOK("PA Predictive Analytics", "edison dsda")
  case MODSIM extends EdisonDSBOK("MODSIM Modelling, Simulation, Optimisation", "edison dsda")
  case BDI extends EdisonDSBOK("BDI Big Data Infrastructure & Tech", "edison dseng")
  case DSIAPP extends EdisonDSBOK("DSIAPP Infrastructure & Platforms", "edison dseng")
  case CCT extends EdisonDSBOK("CCT Cloud Computing Technologies", "edison dseng")
  case SEC extends EdisonDSBOK("SEC Data and Applications Security", "edison dseng")
  case BDSE extends EdisonDSBOK("BDSE Big Data Systems Org & Eng", "edison dseng")
  case IS extends EdisonDSBOK("IS Information Systems", "edison dseng")
  case DMORG extends EdisonDSBOK("DMORG Data Management & Organisation", "edison dsdm")
  case DMS extends EdisonDSBOK("DMS Data Management Systems", "edison dsdm")
  case EDM extends EdisonDSBOK("EDMI Data Mgmt & Enterprise Data Infra", "edison dsdm")
  case DGOV extends EdisonDSBOK("DGOV Data Governance", "edison dsdm")
  case BDSTOR extends EdisonDSBOK("BDSTOR Big Data Storage", "edison dsdm")
  case DLIB extends EdisonDSBOK("DLIB Digital Libraries & Archives", "edison dsdm")
  case RM extends EdisonDSBOK("RM Research Methods", "edison dsrmpm")
  case PM extends EdisonDSBOK("PM Project Management", "edison dsrmpm")
  case BAF extends EdisonDSBOK("BAF Business Analytics Foundation", "edison dsrmpm")
  case BAEM extends EdisonDSBOK("BAEM Business Analytics Org & Enterprise Mgmt", "edison dsrmpm")

  
@JSExportTopLevel("edison")
val edisonjs = (for e <- EdisonDSBOK.values yield e.productPrefix -> e).toMap.toJSDictionary
