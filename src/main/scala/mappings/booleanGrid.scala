package mappings


import courses.*
import mappings.*

import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, DElement, SVG, Styling, VHtmlComponent, ^}
import org.scalajs.dom
import acssite.given


val gridStyle = Styling(
  """font-family: 'Lato', sans-serif;
    |size: 12px;
    |margin-top: 300px;
    |""".stripMargin
).modifiedBy(
  " tr.optional" -> "opacity: 0.67;",
  " .choose.indicator, .or.indicator" -> "border-left: 1px solid #aaa;",
  " th" -> "font-style: italic; font-weight: normal;",
  " td" -> "border: 1px solid white;",
  " th.unit" -> "text-align:right; padding-right: 5px;",
  " td.indicator" -> "background: #ddd;",
  " th.cat" -> "font-weight: normal;",
  " th.section" -> "text-align: right; font-weight: bold;",
  " ."+ cbok.old.Category.Essential.css -> "background-color: #ffdfba; text-align: center;",
  " ."+ cbok.old.Category.TechnologyBuilding.css -> "background-color: #baffc9; text-align: center;",
  " ."+ cbok.old.Category.TechnologyResources.css -> "background-color: #ffffba; text-align: center;",
  " ."+ cbok.old.Category.Management.css -> "background-color: #bae0ff; text-align: center;",

  " .cbok-professional" -> "background-color: #ffdfba; text-align: center;",
  " .cbok-core" -> "background-color: #ffffba; text-align: center;",
  " .cbok-depth" -> "background-color: #bae0ff; text-align: center;",

  " .swebok" -> "background-color: #baffc9; text-align: center;",
  " .ccdsc" -> "background-color: #baffc9; text-align: center;",
  " .edison.dsda" -> "background-color: #baffc9; text-align: center;",
  " .edison.dseng" -> "background-color: #ffffba; text-align: center;",
  " .edison.dsdm" -> "background-color: #ffdfba; text-align: center;",
  " .edison.dsrmpm" -> "background-color: #bae0ff; text-align: center;",
  " .identity" -> "background-color: #baffc9; text-align: center;",
).register()

val rotatedHeader = Styling(
  """    white-space: nowrap;
    |    height: 20px;
    |    text-align: right;
    |    transform: rotate(-60deg);
    |    display: inline-block;
    |    transform-origin: left;
    |    width: 1.8em;
    |    bottom: 0;
    |""".stripMargin
).register()



/**
  * A grid where each category either is or is not mapped to a unit
  *
  * @param plan
  * @param categories
  * @param f
  * @return
  */
def booleanCategoryGrid[C <: GridCategory](plan:Plan, categories:Seq[C])(f: (Subject, C) => Boolean) = {

  def unitTH(u:Subject) =
    <.th(^.cls := "unit",
      <("small")(u.code), " ", u.name
    )

  def unitCBoKcells(u:Subject) =
    for cat <- categories yield
      <.td(^.cls := cat.css, {
        if f(u, cat) then "✔" else ""
      })

  def subTable(planComponent: PlanComponent) =
    val (name, els) = planComponent
    <.tr(
      <.th(^.cls := "section", ^.attr("colspan") := 1, name)) +: (
        els.flatMap {
          case s:PrereqElement.unit =>
            subjects.find(_.code == s.code) match
              case Some(u) => Seq(<.tr(^.cls := "mandatory",
                unitTH(u), <.td(^.cls := "indicator"), unitCBoKcells(u)
              ))
              case None => 
                val u = Subject.empty(s.code)
                Seq(<.tr(^.cls := "optional",
                  unitTH(u), <.td(^.cls := "indicator"), unitCBoKcells(u)
                )) 
          case PrereqElement.choose(lim, units) =>
            for (s, i) <- units.zipWithIndex yield
              subjects.find(_.code == s.code) match
                case Some(u) =>
                  if i == 0 then
                    <.tr(^.cls := "optional choose",
                      unitTH(u), <.td(^.cls := "choose indicator", ^.attr("rowspan") := units.length, lim.toString), unitCBoKcells(u)
                    )
                  else
                    <.tr(^.cls := "optional choose",
                      unitTH(u), unitCBoKcells(u)
                    )
                case None => 
                  val u = Subject.empty(s.code)
                  <.tr(^.cls := "optional choose",
                    unitTH(u), unitCBoKcells(u)
                  ) 
          case PrereqElement.or(a, b) =>
            for (s, i) <- Seq(a, b).zipWithIndex yield
              subjects.find(_.code == s.code) match
                case Some(u) =>
                  if i == 0 then
                    <.tr(^.cls := "optional or",
                      unitTH(u), <.td(^.cls := "choose indicator", ^.attr("rowspan") := 2, 1.toString), unitCBoKcells(u)
                    )
                  else
                    <.tr(^.cls := "optional or",
                      unitTH(u), unitCBoKcells(u)
                    )
                case None => <.tr("Unit not found: " + s)
          case PrereqElement.cp(x) =>
            Seq(<.tr(<.th(s"Complete $x credit points")))
          case PrereqElement.coreq(els) => Seq(<.tr(s"Corequisite ${els.stringify}"))
        }
      )


  <.table(^.cls := gridStyle.className,
    <.tr(
      <.th(""), <.td(),
      for cat <- categories yield <.th(^.cls := "cat",
        <.span(^.cls := "cat " + rotatedHeader.className + " " + cat.css, cat.name)
      )
    ),

    for
      pc <- plan
      html <- subTable(pc)
    yield html

  )
}


def gridPage(c:Course, grid:Grid) = <.div(
    <.h1(c.name),
  (for url <- handbookUrl yield
    <.p(
      <.a(^.href := url(c.code), "Link to handbook entry")
    )
  ),
    ui.Common.markdown(
      """ The table below shows top-level SWEBOK categories that are relevant to each unit in the course
        |
        |""".stripMargin
    ),
    booleanCategoryGrid[GridCategory](c.structure, grid.categories) {
      (s, cat) => s.mappings.contains(cat)
    }, <("hr"),
        ui.Common.markdown(
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
        |Values for the columns are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- grid.categories yield <.li(<.code(e.jsName), " ", <("i")(e.name))
    )
  )
