package mappings

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import scala.scalajs.js.JSConverters._


enum SWEBOK(val name: String) extends GridCategory {
  case Requirements extends SWEBOK("Software Requirements")
  case Design extends SWEBOK("Software Design")
  case Construction extends SWEBOK("Software Construction")
  case Testing extends SWEBOK("Software Testing")
  case Maintenance extends SWEBOK("Software Maintenance")
  case ConfigManagement extends SWEBOK("Software Configuration Management")
  case EngManagement extends SWEBOK("Software Engineering Management")
  case Process extends SWEBOK("Software Engineering Process")
  case ModelsAndMethods extends SWEBOK("Software Engineering Models and Methods")
  case Quality extends SWEBOK("Software Quality")
  case ProfPractice extends SWEBOK("Software Engineering Professional Practice")
  case Economics extends SWEBOK("Software Engineering Economics")
  case CompFoundations extends SWEBOK("Computing Foundations")
  case MathFoundations extends SWEBOK("Mathematical Foundations")
  case EngFoundations extends SWEBOK("Engineering Foundations")

  def css = "swebok" 
}

/*
 * JavaScript API
 */

@JSExportTopLevel("swebok")
val swebokjs = (for e <- SWEBOK.values yield e.productPrefix -> e).toMap.toJSDictionary

