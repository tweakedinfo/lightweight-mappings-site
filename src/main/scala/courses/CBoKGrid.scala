package courses

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
  " ."+Category.Essential.css -> "background-color: #ffdfba; text-align: center;",
  " ."+Category.TechnologyBuilding.css -> "background-color: #baffc9; text-align: center;",
  " ."+Category.TechnologyResources.css -> "background-color: #ffffba; text-align: center;",
  " ."+Category.Management.css -> "background-color: #bae0ff; text-align: center;",
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



//
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


case class CBOKGridComponent(course:Course, plan:Plan) extends VHtmlComponent {

  var limitToMap:Boolean = true

  def render = 
    <.div(
      <.div(
        <.input(^.attr("id") := "limit-toggle",
          ^.attr("type") := "checkbox", ^.attr("checked") ?= (if limitToMap then Some("checked") else None),
          ^.on("change") --> { limitToMap = !limitToMap; rerender(); }
        ), " ",
        <("label")(^.attr("for") := "limit-toggle", "Limit to top 3 core units for each category")
      ),

      if (limitToMap && topCbok.contains(course.code)) then
        cbokGrid(plan, topCbok(course.code))
      else cbokGrid(plan, Map.empty)
    )
}


/** 
 * A specialised grid for showing CBOK values, colour coded into their categories
 */
def cbokGrid(plan:Plan, topCbokMap: Map[CBOK, Seq[String]] = Map.empty) = {

  def unitTH(u:Subject) =
    <.th(^.cls := "unit",
      <("small")(u.code), " ", u.name
    )

  def unitPermitted(category:CBOK, unitCode:String):Boolean = 
    !topCbokMap.contains(category) || topCbokMap(category).contains(unitCode) 

  def unitCBoKcells(u:Subject) =
    for cat <- CBOK.values.toSeq yield
      <.td(^.cls := cat.category.css, {
        val l = u.cbokLevel(cat)
        if unitPermitted(cat, u.code) && l > 0 then l.toString else ""
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
          case PrereqElement.coreq(els) =>
            Seq(<.tr(<.th(s"Corequisite(${els.stringify})")))
        }
      )


  <.table(^.cls := gridStyle.className,
    <.tr(
      <.th(""), <.td(),
      for cat <- CBOK.values.toSeq yield <.th(^.cls := "cat",
        <.span(^.cls := "cbokcat " + rotatedHeader.className + " " + cat.category.css, cat.name)
      )
    ),

    for
      pc <- plan
      html <- subTable(pc)
    yield html

  )
}


