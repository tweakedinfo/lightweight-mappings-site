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
  " th" -> "font-style: italic;",
  " th,td" -> "border: 1px solid white;",
  " th.unit" -> "text-align:right; font-weight: normal; padding-right: 5px;",
  " th.cat" -> "font-weight: normal;",
  " th.section" -> "text-align: right;",
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

  <.table(^.cls := gridStyle.className,
    <.tr(
      <.th(""),
      for cat <- CBOK.values.toSeq yield <.th(^.cls := "cat",
        <.span(^.cls := "cbokcat " + rotatedHeader.className + " " + cat.category.css, cat.name)
      )
    ),

    for
      row <- flatStrings(plan)
      subj = subjects.find(_.code == row)
    yield subj match {
      case Some(u) =>
        <.tr(
          <.th(^.cls := "unit", <("small")(u.code), " ", u.name),
          for cat <- CBOK.values.toSeq yield <.td(^.cls := cat.category.css,
          {
            val l = u.cbokLevel(cat)
            if l > 0 then l.toString else ""
          })
        )
      case None =>
        <.tr(<.th(^.cls := "section", ^.attr("colspan") := 1, row))
    }

  )
}