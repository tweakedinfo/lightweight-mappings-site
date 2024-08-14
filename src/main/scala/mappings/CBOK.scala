package mappings.cbok

import mappings.*

import scala.scalajs.js
import js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import js.JSConverters.*
import scala.collection.mutable

object old extends LevelledGrid { 
  
  enum Category(val css:String):
    case Essential extends Category("essential")
    case TechnologyResources extends Category("tech-res")
    case TechnologyBuilding extends Category("tech-build")
    case Management extends Category("management")

  enum CBOK(val category: Category, val name: String) extends GridCategory:
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

    def jsName = productPrefix

    def css = category.css

  val categories = (CBOK.values).toSeq

}


object v3_2 extends Grid {

  enum Professional(val name:String) extends GridCategory {
    case Ethics extends Professional("Professional ICT Ethics")
    case Impacts extends Professional("Impacts of ICT")
    case Collaboration extends Professional("Working Individually and in Teams")
    case Communication extends Professional("Professional Communication")
    case Practitioner extends Professional("The Professional ICT Practitioner")    

    val css = "cbok-professional"

    def jsName = productPrefix
  }

  enum Core(val name:String) extends GridCategory {
    case Fundamentals extends Core("ICT Fundamentals")
    case Infrastructure extends Core("ICT Infrastructure")
    case Data extends Core("Information & Data Science and Engineering")
    case Computing extends Core("Computational Science and Engineering")
    case Cybersecurity extends Core("Cyber Security")
    case Projects extends Core("ICT Projects")
    case Governance extends Core("ICT Management & Governance")

    def jsName = productPrefix

    val css = "cbok-core"
  }

  val categories = (Professional.values ++ Core.values).toSeq




}


/*
 * JavaScript API
 */

@JSExportTopLevel("cbok")
@JSExportAll
object CBOKjs {

  
  val old = (for e <- cbok.old.categories yield e.jsName -> e).toMap.toJSDictionary

  def v3_2 = cbok.v3_2
  

}

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
    LevelledGridComponent(cbok.old, c, c.structure),  <("hr"),
    Common.markdown(
      s"""
        |#### How to edit this table 
        |
        |In `units.js`, this table is driven from the contents of the `mappings` array of each unit.
        |e.g.
        |
        |```js
        |{
        |  code: "THI123",
        |  name: "Thingummy Design and Construction",
        |  prereq: [ choose(1, "COSC210", "COSC220") ],
        |  mappings: [ 
        |     cbok.old.Teamwork.level(2), cbok.old.Communication.level(3),
        |     swebok.Design, swebok.Construction,
        |     idverify.ProctoredExam 
        |  ],
        |  tags: ["Advanced"],
        |},
        |
        |```
        |
        |Functions for the columns (defined in `src/main/scala/courses/CBOK.scala`) are:
        |""".stripMargin
    ),
    <.ul(
      for e <- cbok.v3_2.categories yield <.li(<("code")(s"cbok.${e.name}.level(i)"), " ", <("i")(e.name))
    ),
    Common.markdown("""
        |
        | Each function takes an int parameter for its level. E.g. `cbok.Ethics.level(3)`
        |        |
        |#### How to limit units shown for each course
        |
        |Within `courses.js`, call `limitCourseGridEntries`. This takes an array of degrees, a grid column, and an array of unit codes. e.g.
        |
        |```js
        |limitCourseGridEntries([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.old.Ethics, ["COSC110", "COSC310", "COSC320"])
        |```
        |
    """.stripMargin)
  )




