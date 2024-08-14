package mappings.cbok

import courses.*
import mappings.*

import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, DElement, SVG, Styling, VHtmlComponent, ^}
import org.scalajs.dom
import acssite.given


/**
  * Shows a "levelled grid", i.e. one that maps different units to different numerical levels, optionally limited to a subset of subjects
  *
  * @param grid
  * @param course
  * @param plan
  */
case class LevelledGridComponent(grid:LevelledGrid, course:Course, plan:Plan) extends VHtmlComponent {

  var limitToMap:Boolean = true

  def render = 
    <.div(
      <.div(
        <.input(^.attr("id") := "limit-toggle",
          ^.attr("type") := "checkbox", ^.attr("checked") ?= (if limitToMap then Some("checked") else None),
          ^.on("change") --> { limitToMap = !limitToMap; rerender(); }
        ), " ",
        <.label(^.attr("for") := "limit-toggle", "Limit to top 3 core units for each category")
      ),

      if (limitToMap && limitCourseGridEntries.contains(course.code)) then
        levelledGrid(grid, plan, limitCourseGridEntries(course.code))
      else levelledGrid(grid, plan, Map.empty)
    )
}


/** 
 * A specialised grid for showing CBOK values, colour coded into their categories
 */
def levelledGrid(grid:Grid, plan:Plan, categoryUnitLists: Map[GridCategory, Seq[String]] = Map.empty) = {

  def unitTH(u:Subject) =
    <.th(^.cls := "unit",
      <.small(u.code), " ", u.name
    )

  def unitPermitted(category:GridCategory, unitCode:String):Boolean = 
    !categoryUnitLists.contains(category) || categoryUnitLists(category).contains(unitCode) 

  def unitCells(u:Subject) =
    for cat <- grid.categories yield
      <.td(^.cls := cat.css, {
        if unitPermitted(cat, u.code) then 
          u.mappings.collect({ case (c, i:Int) if c == cat => i}).headOption match {
            case Some(i) => i.toString
            case None => ""
          }
        else ""
      })

  def subTable(planComponent: PlanComponent) =
    val (name, els) = planComponent
    <.tr(
      <.th(^.cls := "section", ^.attr("colspan") := 1, name)) +: (
        els.flatMap {
          case s:PrereqElement.unit =>
            subjects.find(_.code == s.code) match
              case Some(u) => Seq(<.tr(^.cls := "mandatory",
                unitTH(u), <.td(^.cls := "indicator"), unitCells(u)
              ))
              case None => 
                val u = Subject.empty(s.code)
                Seq(<.tr(^.cls := "optional",
                  unitTH(u), <.td(^.cls := "indicator"), unitCells(u)
                )) 
          case PrereqElement.choose(lim, units) =>
            for (s, i) <- units.zipWithIndex yield
              subjects.find(_.code == s.code) match
                case Some(u) =>
                  if i == 0 then
                    <.tr(^.cls := "optional choose",
                      unitTH(u), <.td(^.cls := "choose indicator", ^.attr("rowspan") := units.length, lim.toString), unitCells(u)
                    )
                  else
                    <.tr(^.cls := "optional choose",
                      unitTH(u), unitCells(u)
                    )
                case None => 
                    val u = Subject.empty(s.code)
                    <.tr(^.cls := "optional choose",
                      unitTH(u), unitCells(u)
                    ) 

          case PrereqElement.or(a, b) =>
            for (s, i) <- Seq(a, b).zipWithIndex yield
              subjects.find(_.code == s.code) match
                case Some(u) =>
                  if i == 0 then
                    <.tr(^.cls := "optional or",
                      unitTH(u), <.td(^.cls := "choose indicator", ^.attr("rowspan") := 2, 1.toString), unitCells(u)
                    )
                  else
                    <.tr(^.cls := "optional or",
                      unitTH(u), unitCells(u)
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
      for cat <- grid.categories.toSeq yield <.th(^.cls := "cat",
        <.span(^.cls := "cbokcat " + rotatedHeader.className + " " + cat.css, cat.name)
      )
    ),

    for
      pc <- plan
      html <- subTable(pc)
    yield html

  )
}


