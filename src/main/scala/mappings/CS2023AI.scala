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

  def jsName = productPrefix
  
  def css = "swebok" 
}

/*
 * JavaScript API
 */

@JSExportTopLevel("cs2023ai")
val cs2023aijs = (
  (for e <- CS2023AI.values yield e.jsName -> e).toMap + 
  ("page" -> ("CS2023 AI", "cs2023-ai", cs2023aiPage))
).toJSDictionary

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
      (s, cat) => s.mappings.contains(cat)
    }, <("hr"),
        Common.markdown(
      s"""
        |#### How to edit these tables 
        |
        |In `units.js`, these tables are driven from the contents of the `mappings` array of each unit.
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
        |Values for the columns (defined in `src/main/scala/courses/CS2023AI.scala`) are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- CS2023AI.values yield <.li(<("code")("cs2023ai." + e.productPrefix), " ", <.i(e.name))
    )
  )

