package mappings

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import scala.scalajs.js.JSConverters.*

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
  @JSExport("level")
  def apply(level:Int): (CBOK, Int) = (this, level)

/*
 * JavaScript API
 */

@JSExportTopLevel("cbok")
val cbokjs = (for e <- CBOK.values yield {
  e.productPrefix -> e
}).toMap.toJSDictionary


@JSExportAll
object cbokk {
  val ethics = CBOK.Ethics
  val expectations = CBOK.ProfExpectations
  val teamwork = CBOK.Teamwork
  val communication = CBOK.Communication
  val societal = CBOK.Societal
  val understanding = CBOK.Understanding
  val problemSolving = CBOK.ProblemSolving
  val fundamentals = CBOK.Fundamentals
  val data = CBOK.Data
  val networking = CBOK.Networking
  val humanFactors = CBOK.HumanFactors
  val programming = CBOK.Programming
  val systems = CBOK.Systems
  val governance = CBOK.Governance
  val projectManagement = CBOK.ProjectManagement
  val serviceManagement = CBOK.ServiceManagement
  val cybersecurity = CBOK.Cybersecurity
}


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



import com.wbillingsley.veautiful.html.{<, ^}
import ui.*
import courses.*



def cbokPage(c:Course) = <.div(
    <.h1(c.name),
  (for url <- handbookUrl yield
    <.p(
      <.a(^.href := url(c.code), "Link to handbook entry")
    )
  ),
    Common.markdown(
      """ The table below is generated from the data files for each unit within the course structure.
        | A toggle is provided to toggle between:
        |
        | * showing only the selected top 3 core units for each course (to show coverage of CBoK in core units) 
        | * showing where that CBoK item appears in all units (to show scaffolding, or in-depth knowledge in advanced units, such as within majors)
        |
        | Where there is a choice between units, the number to select is shown next to the rows, and the rows are faded.
        | These have been included as we sometimes allow choice between two units offering a skill such as teamwork.
        | (e.g. in the BCompSc, how teamwork is scaffolded from COSC101 and COSC102 in first year, rather than only showing
        | its mandatory presence in units in later years).
        |
        | Levels shown in the grid are as per ACS template documents. 
        | 1 = Introductory (Blooms levels 1 & 2); 2 = Intermediate (Blooms level 3); 3 = Advanced (Blooms level 4 & 5)
        |""".stripMargin
    ),
    CBOKGridComponent(c, c.structure),  <("hr"),
    Common.markdown(
      s"""
        |#### How to edit this table 
        |
        |In `units.js`, this table is driven from the contents of the `cbok` array of each unit.
        |e.g.
        |
        |```js
        |{
        |  code: "FOO310",
        |  name: "Foo Management",
        |  prereq: [ choose(1, "COSC210", "COSC220") ],
        |  cbok: [ cbok.Governance.level(3), cbok.ProjectManagement.level(3) ],
        |  dsbok: [],
        |  sfia: [ "PROG" ],
        |  tags: ["Advanced"],
        |  other: [ idverify.ProctoredExam, idverify.GroupWork, idverify.TurnItIn, idverify.Video ]
        |},
        |```
        |
        |Functions for the columns (defined in `src/main/scala/courses/CBOK.scala`) are:
        |""".stripMargin
    ),
    <.ul(
      for e <- CBOK.values yield <.li(<("code")(s"cbok.${e.productPrefix}.level(i)"), " ", <("i")(e.name))
    ),
    Common.markdown("""
        |
        | Each function takes an int parameter for its level. E.g. `cbok.Ethics.level(3)`
        |
        | For convenience, these functions are also defined at the global level as:
        | 
        |`ethics`, `expectations`, `teamwork`, `communication`, `societal`, `understanding`, `problemSolving`,
        |`fundamentals`, `data`, `networking`, `humanFactors`, `programming`, `systems`,
        |`governance`, `projectManagement`, `serviceManagement`, `cybersecurity`
        |
        |So they can simply be called, e.g. `ethics(2)`
        |
        |#### How to limit units shown for each course
        |
        |Within `courses.js`, call `limitCBOK`. This takes an array of degrees, a CBOK column, and an array of unit codes. e.g.
        |
        |```js
        |limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Ethics, ["COSC110", "COSC310", "COSC320"])
        |```
        |
    """.stripMargin)
  )




