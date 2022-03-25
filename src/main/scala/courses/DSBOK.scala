package courses

/**
 * ACM Computing Competencies for Undergraduate Data Science Curricula
 */ 
enum CCDSC(val name:String):
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
