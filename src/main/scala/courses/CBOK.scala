package courses

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}

enum Category(val css:String):
  case Essential extends Category("essential")
  case TechnologyResources extends Category("tech-res")
  case TechnologyBuilding extends Category("tech-build")
  case Management extends Category("management")

enum CBOK(val category: Category, val name: String):
  case Ethics extends CBOK(Category.Essential, "Ethics")
  case ProfExpectations extends CBOK(Category.Essential, "Professional expectations")
  case Teamwork extends CBOK(Category.Essential, "Teamwork concepts and issues")
  case Communication extends CBOK(Category.Essential, "Interpersonal communication")
  case Societal extends CBOK(Category.Essential, "Societal issues / legal issues / privacy")
  case Understanding extends CBOK(Category.Essential, "Understanding the ICT profession")
  case ProblemSolving extends CBOK(Category.Essential, "ICT problem solving")

  case Fundamentals extends CBOK(Category.TechnologyResources, "Hardware and software fundamentals")
  case Data extends CBOK(Category.TechnologyResources, "Data and information management")
  case Networking extends CBOK(Category.TechnologyResources, "Networking")

  case HumanFactors extends CBOK(Category.TechnologyBuilding, "Human factors")
  case Programming extends CBOK(Category.TechnologyBuilding, "Programming")
  case Systems extends CBOK(Category.TechnologyBuilding, "Systems development and acquisition")

  case Governance extends CBOK(Category.Management, "IT governance and organisational issues")
  case ProjectManagement extends CBOK(Category.Management, "IT project management")
  case ServiceManagement extends CBOK(Category.Management, "IT service management")
  case Cybersecurity extends CBOK(Category.Management, "Cybersecurity")

  // This lets us say Cybersecurity(3) to get the tuple (3, Cybersecurity)
  def apply(level:Int): (CBOK, Int) = (this, level)

/*
 * JavaScript API
 */

import CBOK.*
@JSExportTopLevel("ethics") def ethics(level:Int) = Ethics(level)
@JSExportTopLevel("expectations") def profExpect(level:Int) = ProfExpectations(level)
@JSExportTopLevel("teamwork") def teamwork(level:Int) = Teamwork(level)
@JSExportTopLevel("communication") def communication(level:Int) = Communication(level)
@JSExportTopLevel("societal") def societal(level:Int) = Societal(level)
@JSExportTopLevel("understanding") def understanding(level:Int) = Understanding(level)
@JSExportTopLevel("problemSolving") def problemSolving(level:Int) = ProblemSolving(level)

@JSExportTopLevel("fundamentals") def fundamentals(level:Int) = Fundamentals(level)
@JSExportTopLevel("data") def data(level:Int) = Data(level)
@JSExportTopLevel("networking") def networking(level:Int) = Networking(level)

@JSExportTopLevel("humanFactors") def humanFactors(level:Int) = HumanFactors(level)
@JSExportTopLevel("programming") def programming(level:Int) = Programming(level)
@JSExportTopLevel("systems") def systems(level:Int) = Systems(level)

@JSExportTopLevel("governance") def governance(level:Int) = Governance(level)
@JSExportTopLevel("projectManagement") def projectManagement(level:Int) = ProjectManagement(level)
@JSExportTopLevel("serviceManagement") def serviceManagement(level:Int) = ServiceManagement(level)
@JSExportTopLevel("cybersecurity") def cybersecurity(level:Int) = Cybersecurity(level)

