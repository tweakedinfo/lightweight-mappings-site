package mappings

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
  case PDA extends CCDSC("Programming, Data Structures, and Algorithms")
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
  case DSAPPD extends EdisonDSBOK("DSAPPD Data Science Applications Design", "edison dseng")
  case IS extends EdisonDSBOK("IS Information Systems", "edison dseng")
  case DMORG extends EdisonDSBOK("DMORG Data Management & Organisation", "edison dsdm")
  case DMS extends EdisonDSBOK("DMS Data Management Systems", "edison dsdm")
  case EDMI extends EdisonDSBOK("EDMI Data Mgmt & Enterprise Data Infra", "edison dsdm")
  case DGOV extends EdisonDSBOK("DGOV Data Governance", "edison dsdm")
  case BDSTOR extends EdisonDSBOK("BDSTOR Big Data Storage", "edison dsdm")
  case DLIB extends EdisonDSBOK("DLIB Digital Libraries & Archives", "edison dsdm")
  case RM extends EdisonDSBOK("RM Research Methods", "edison dsrmpm")
  case PM extends EdisonDSBOK("PM Project Management", "edison dsrmpm")
  case BAF extends EdisonDSBOK("BAF Business Analytics Foundation", "edison dsrmpm")
  case BAEM extends EdisonDSBOK("BAEM Business Analytics Org & Enterprise Mgmt", "edison dsrmpm")

  
@JSExportTopLevel("edison")
val edisonjs = (for e <- EdisonDSBOK.values yield e.productPrefix -> e).toMap.toJSDictionary

import com.wbillingsley.veautiful.html.{<, ^}
import ui.*
import courses.*

def dsbokPage(c:Course) = <.div(
    <.h1("Data Science bodies of knoweledge"),
    <.h2(c.name),
  (for url <- handbookUrl yield
    <.p(
      <.a(^.href := url(c.code), "Link to handbook entry")
    )
  ),
    Common.markdown(
      """
        |This page maps units in the course against ACM and Edison bodies of knowledge for data science.
        |Note however, that as the criteria ask for 1 EFTSL of content drawn from these bodies of knowledge,
        |rather than complete coverage of all bodies of knowledge, not every column may be covered.
        | 
        |### ACM Computing Competencies for Undergraduate Data Science Curricula (CCDS)
        |
        |""".stripMargin
    ),
    booleanCategoryGrid[CCDSC](c.structure, CCDSC.values.toSeq) {
      (s, cat) => s.dsbok.contains(cat)
    },
    Common.markdown(
      """
        |### Edison Data Science Body of Knowledge
        | 
        |The table below maps units to Knowledge Areas (KAs) in the Edison Data Science Body of Knowledge.
        |KAs within the same Knowledge Area Group (KAG) are given similar colouring. 
        |""".stripMargin
    ),
    booleanCategoryGrid[EdisonDSBOK](c.structure, EdisonDSBOK.values.toSeq) {
      (s, cat) => s.dsbok.contains(cat)
    }, <("hr"),
    Common.markdown(
      s"""
        |#### How to edit these tables 
        |
        |In `units.js`, these tables are driven from the contents of the `dsbok` array of each unit.
        |e.g.
        |
        |```js
        |{
        |  code: "FOO123",
        |  name: "Foo and Thingummy Analysis",
        |  prereq: [ choose(1, "COSC210", "COSC220") ],
        |  cbok: [ teamwork(2), communication(3) ],
        |  dsbok: [ ccdsc.AP, ccdsc.ML, edison.ML ],
        |  sfia: [ "DATS" ],
        |  tags: ["Advanced"],
        |  other: [ idverify.ProctoredExam ]
        |},
        |```
        |
        |Values for the columns (defined in `src/main/scala/courses/DSBOK.scala`) are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- CCDSC.values yield <.li(<("code")("ccsdsc." + e.productPrefix), " ", <("i")(e.name)),
      for e <- EdisonDSBOK.values yield <.li(<("code")("edison." + e.productPrefix), " ", <("i")(e.name)),
    )
  )

