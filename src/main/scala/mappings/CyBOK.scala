package mappings

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import scala.scalajs.js.JSConverters._

import courses.*

enum CyBOK(val name: String) extends GridCategory {
  case HORA extends CyBOK("Human, Organisational, & Regulatory Aspects")
  case AD extends CyBOK("Attacks and Defences")
  case SS extends CyBOK("Systems Security")
  case SAPS extends CyBOK("Software and Platform Security")
  case IS extends CyBOK("Infrastructure Security")
  
  def css = "swebok" 
}

/*
 * JavaScript API
 */

@JSExportTopLevel("cybok")
val cybokjs = (for e <- CyBOK.values yield e.productPrefix -> e).toMap.toJSDictionary

import com.wbillingsley.veautiful.html.{<, ^}
import ui.*

def cybokPage(c:Course) = <.div(
    <.h1(c.name),
  (for url <- handbookUrl yield
    <.p(
      <.a(^.href := url(c.code), "Link to handbook entry")
    )
  ),
    Common.markdown(
      """ The table below shows top-level CyBOK categories that are relevant to each unit in the course
        |
        |""".stripMargin
    ),
    booleanCategoryGrid[CyBOK](c.structure, CyBOK.values.toSeq) {
      (s, cat) => s.swebok.contains(cat)
    }, <("hr"),
        Common.markdown(
      s"""
        |#### How to edit these tables 
        |
        |In `units.js`, these tables are driven from the contents of the `cybok` array of each unit.
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
        |Values for the columns (defined in `src/main/scala/courses/CyBOK.scala`) are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- CyBOK.values yield <.li(<("code")("cybok." + e.productPrefix), " ", <("i")(e.name))
    )
  )

