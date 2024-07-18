package mappings

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import scala.scalajs.js.JSConverters._

import courses.*

enum CS2023AI(val name: String) extends GridCategory {

  case FundamentalIssues extends CS2023AI("Fundamental Issues")
  case Search extends CS2023AI("Search")
  case FKRR extends CS2023AI("Fundamental Knowledge Representation & Reasoning")
  case MachineLearning extends CS2023AI("Machine Learning")
  case Applications extends CS2023AI("Applications and Societal Impact")
  case Probabilistic extends CS2023AI("Probabilistic Reperesentation and Reasoning")
  case Planning extends CS2023AI("Planning")
  case Logical extends CS2023AI("Logical Representation and Reasoning")
  case Agents extends CS2023AI("Agents and Cognitive Systems")
  case NLP extends CS2023AI("Natural Language Processing")
  case Robotics extends CS2023AI("Robotics")
  case Perception extends CS2023AI("Perception and Computer Vision")
  
  def css = "swebok" 
}

/*
 * JavaScript API
 */

@JSExportTopLevel("cs2023ai")
val cs2023aijs = (for e <- CyBOK.values yield e.productPrefix -> e).toMap.toJSDictionary

import com.wbillingsley.veautiful.html.{<, ^}
import ui.*

def cs2023aiPage(c:Course) = <.div(
    <.h1(c.name),
  (for url <- handbookUrl yield
    <.p(
      <.a(^.href := url(c.code), "Link to handbook entry")
    )
  ),
    Common.markdown(
      """ The table below shows categories from the Artificial Intelligence knowledge area of ACM's CS2023 curriculum report that are relevant to each unit in the course
        |
        |""".stripMargin
    ),
    booleanCategoryGrid[CS2023AI](c.structure, CS2023AI.values.toSeq) {
      (s, cat) => s.swebok.contains(cat)
    }, <("hr"),
        Common.markdown(
      s"""
        |#### How to edit these tables 
        |
        |In `units.js`, these tables are driven from the contents of the `cs2023ai` array of each unit.
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
        |Values for the columns (defined in `src/main/scala/courses/CS2023AI.scala`) are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- CS2023AI.values yield <.li(<("code")("cs2023ai." + e.productPrefix), " ", <.i(e.name))
    )
  )

