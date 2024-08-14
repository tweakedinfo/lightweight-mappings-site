package mappings

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import scala.scalajs.js.JSConverters._

enum IdentityVerification(val name:String) extends GridCategory:
    case ProctoredExam extends IdentityVerification("Proctored online exam")
    case TurnItIn extends IdentityVerification("TurnItIn")
    case Oral extends IdentityVerification("Oral or live assessment")
    case Video extends IdentityVerification("Video submissions")
    case PersonalisedAssessment extends IdentityVerification("Personalised assessment")
    case Project extends IdentityVerification("Project work")
    case GroupWork extends IdentityVerification("Group work")
    case DataTrails extends IdentityVerification("Data trails")

    def jsName = productPrefix

    def css = "identity"

@JSExportTopLevel("idverify")
@JSExportAll 
val idverify = (for e <- IdentityVerification.values yield e.productPrefix -> e).toMap.toJSDictionary


import com.wbillingsley.veautiful.html.{<, ^}
import ui.*
import courses.*


def idverifyPage(c:Course) = <.div(
    <.h1("Methods of Identity Management"),
    <.h2(c.name),
  (for url <- handbookUrl yield
    <.p(
      <.a(^.href := url(c.code), "Link to handbook entry")
    )
  ),
    Common.markdown(
      """
        |This page maps units in the course against different mechanisms that are used in assessment to support
        |identity management and deter or detect academic misconduct
        |""".stripMargin
    ),
    booleanCategoryGrid[IdentityVerification](c.structure, IdentityVerification.values.toSeq) {
      (s, cat) => s.mappings.contains(cat)
    }, <("hr"),
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
        |Values for the columns (defined in `src/main/scala/courses/IdentityVerify.scala`) are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- IdentityVerification.values yield <.li(<("code")("idverify." + e.productPrefix), " ", <("i")(e.name))
    )
  )

