package mappings

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import scala.scalajs.js.JSConverters._

import courses.*

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

import com.wbillingsley.veautiful.html.{<, ^}
import ui.*

def swebokPage(c:Course) = <.div(
    <.h1(c.name),
  (for url <- handbookUrl yield
    <.p(
      <.a(^.href := url(c.code), "Link to handbook entry")
    )
  ),
    Common.markdown(
      """ The table below shows top-level SWEBOK categories that are relevant to each unit in the course
        |
        |""".stripMargin
    ),
    booleanCategoryGrid[SWEBOK](c.structure, SWEBOK.values.toSeq) {
      (s, cat) => s.swebok.contains(cat)
    }, <("hr"),
        Common.markdown(
      s"""
        |#### How to edit these tables 
        |
        |In `units.js`, these tables are driven from the contents of the `swebok` array of each unit.
        |e.g.
        |
        |```js
        |{
        |  code: "THI123",
        |  name: "Thingummy Design and Construction",
        |  prereq: [ choose(1, "COSC210", "COSC220") ],
        |  cbok: [ teamwork(2), communication(3) ],
        |  swebok: [ swebok.Design, swebok.Construction ],
        |  sfia: [ "PROG" ],
        |  tags: ["Advanced"],
        |  other: [ idverify.ProctoredExam ]
        |},
        |```
        |
        |Values for the columns (defined in `src/main/scala/courses/SWEBOK.scala`) are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- SWEBOK.values yield <.li(<("code")("swebok." + e.productPrefix), " ", <("i")(e.name))
    )
  )

