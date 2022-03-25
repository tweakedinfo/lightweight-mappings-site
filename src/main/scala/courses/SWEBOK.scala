package courses

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import scala.collection.immutable.Stream.Cons

enum SWEBOK(val name: String):
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
  case MathFoundations extends SWEBOK("Nathematical Foundations")
  case EngFoundations extends SWEBOK("Engineering Foundations")
  

/*
 * JavaScript API
 */

@JSExportTopLevel("swebok")
@JSExportAll 
object swebokk {
  val Requirements = SWEBOK.Requirements
  val Design = SWEBOK.Design
  val Construction = SWEBOK.Construction
  val Testing = SWEBOK.Testing
  val Maintenance = SWEBOK.Maintenance
  val ConfigManagement = SWEBOK.ConfigManagement
  val EngManagement = SWEBOK.EngManagement
  val Process = SWEBOK.Process
  val ModelsAndMethods = SWEBOK.ModelsAndMethods
  val Quality = SWEBOK.Quality
  val ProfPractice = SWEBOK.ProfPractice
  val Economics = SWEBOK.Economics
  val CompFoundations = SWEBOK.CompFoundations
  val MathFoundations = SWEBOK.MathFoundations
  val EngFoundations = SWEBOK.EngFoundations
}
