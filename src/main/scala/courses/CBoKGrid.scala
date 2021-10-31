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
  " ."+Category.Essential.css -> "background-color: #ffdfba;",
  " ."+Category.TechnologyBuilding.css -> "background-color: #baffc9;",
  " ."+Category.TechnologyResources.css -> "background-color: #ffffba;",
  " ."+Category.Management.css -> "background-color: #bae0ff;",
).register()

val rotatedHeader = Styling(
  """    white-space: nowrap;
    |    height: 20px;
    |    text-align: right;
    |    transform: rotate(-60deg);
    |    display: inline-block;
    |    transform-origin: left;
    |    width: 30px;
    |    bottom: 0;
    |""".stripMargin
).register()

def cbokGrid(plan:Plan) = {

  def unitTH(u:Subject) =
    <.th(^.cls := "unit",
      <("small")(u.code), " ", u.name
    )

  def unitCBoKcells(u:Subject) =
    for cat <- CBOK.values.toSeq yield
      <.td(^.cls := cat.category.css, {
        val l = u.cbokLevel(cat)
        if l > 0 then l.toString else ""
      })

  def subTable(planComponent: PlanComponent) =
    val (name, els) = planComponent
    <.tr(
      <.th(^.cls := "section", ^.attr("colspan") := 1, name)) +: (
        els.flatMap {
          case s:String =>
            subjects.find(_.code == s) match
              case Some(u) => Seq(<.tr(^.cls := "mandatory",
                unitTH(u), <.td(^.cls := "indicator"), unitCBoKcells(u)
              ))
              case None => Seq(<.tr("Unit not found: " + s))
          case ComplexPrereqElement.choose(lim, units:_*) =>
            for (s, i) <- units.zipWithIndex yield
              subjects.find(_.code == s) match
                case Some(u) =>
                  if i == 0 then
                    <.tr(^.cls := "optional choose",
                      unitTH(u), <.td(^.cls := "choose indicator", ^.attr("rowspan") := units.length, lim.toString), unitCBoKcells(u)
                    )
                  else
                    <.tr(^.cls := "optional choose",
                      unitTH(u), unitCBoKcells(u)
                    )
                case None => <.tr("Unit not found: " + s)
          case ComplexPrereqElement.or(a, b) =>
            for (s, i) <- Seq(a, b).zipWithIndex yield
              subjects.find(_.code == s) match
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
          case ComplexPrereqElement.cp(x) =>
            Seq(<.tr(<.th(s"Complete $x credit points")))
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