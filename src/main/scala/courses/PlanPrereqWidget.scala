package courses

import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, DElement, SVG, Styling, VHtmlComponent, ^}
import org.scalajs.dom


case class PlanPrereqWidget(plan:Plan) extends VHtmlComponent {

  import acssite.given

  val leftMargin = "50px"

  val unitStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |size: 12px;
      |fill: black;
      |dominant-baseline: middle;
      |""".stripMargin
  ).modifiedBy(
  ).register()

  val sectionStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |size: 12px;
      |fill: black;
      |dominant-baseline: middle;
      |""".stripMargin
  ).modifiedBy(
  ).register()

  val orStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |size: 12px;
      |font-style: italic;
      |fill: black;
      |dominant-baseline: middle;
      |text-anchor: end;
      |""".stripMargin
  ).modifiedBy(
  ).register()

  val chooseStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |size: 12px;
      |font-style: italic;
      |fill: black;
      |dominant-baseline: bottom;
      |text-anchor: middle;
      |""".stripMargin
  ).modifiedBy(
  ).register()

  val rowH = 36
  val halfH = rowH / 2

  def svg(offset:Int, el:PrereqElement):(Int, <.SingleChild[dom.svg.G]) = el match {
    case s:String =>
      1 -> SVG.g(
        SVG.rect(^.attr("x") := 50 - 4, ^.attr("width") := 850, ^.attr("y") := offset * rowH + 2, ^.attr("height") := rowH - 4, ^.attr("fill") := "rgba(120, 120, 120, 0.2)" ),
        SVG.text(^.attr("x") := 50, ^.attr("y") := offset * rowH + halfH, ^.cls := unitStyle.className, unitText(s)),
      )

    case ComplexPrereqElement.or(a, b) =>
      2 -> SVG.g(^.attr("transform") := s"translate(0, ${offset * rowH})",
        SVG.text(^.attr("x") := 40, ^.attr("y") := rowH, ^.cls := orStyle.className, "or"),
        SVG.line(^.attr("x1") := 45, ^.attr("x2") := 45, ^.attr("y1") := 5, ^.attr("y2") := 2 * rowH - 5, ^.attr("stroke") := "black"),
        svg(0, a)._2,
        svg(1, b)._2,
      )

    case ComplexPrereqElement.choose(num, units:_*) =>
      units.length -> SVG.g(^.attr("transform") := s"translate(0, ${offset * rowH})",
        SVG.text(
          ^.attr("x") := 40, ^.attr("y") := (units.length * rowH) / 2, ^.cls := chooseStyle.className,
          ^.attr("transform") := s"rotate(-90 40 ${units.length * rowH / 2})",
          s"choose $num"
        ),
        SVG.line(^.attr("x1") := 45, ^.attr("x2") := 45, ^.attr("y1") := 5, ^.attr("y2") := units.length * rowH - 5, ^.attr("stroke") := "black"),
        for (u, index) <- units.zipWithIndex yield svg(index, u)._2
      )

    case _ => (0, SVG.g())
  }

  val planStrings = flatStrings(plan)
  println(planStrings)

  def prereqLines(plan: Plan, active:Option[String]) =
    def arrow(startRow:Int, endRow:Int, number:Int) =
      val startY = startRow * rowH + rowH - 6
      val endY = endRow * rowH + 6
      val midY = (startY + endY) / 2
      val endsX = (endRow * 25) + (number * 5)
      SVG.path(
        ^.attr("d") := s"M $endsX $startY L $endsX $endY l -4 -4 m 4 4 l 4 -4",
        ^.attr("stroke") := "red", ^.attr("fill") := "none"
      )

    SVG.g(
      for
        (code, endRow) <- planStrings.zipWithIndex
        subj <- subjects.find(_.code == code).toSeq
        prereqCodes = for c <- subj.prereq.flatMap(subjCodes) if planStrings.contains(c) yield c
        (prereqCode, number) <- prereqCodes.zipWithIndex
        startRow = planStrings.indexOf(prereqCode)
      yield arrow(startRow, endRow, number)
    )



  override def render = SVG.svg(
    ^.attr("width") := 1000, ^.attr("height") := planStrings.length * rowH,
    ^.attr("viewBox") := s"0 0 1000 ${planStrings.length * rowH}",
    prereqLines(plan, None),
    {
      var row = 0
      def textY = (row * rowH) + rowH/2
      for
        (name, units) <- plan
      yield
        SVG.g(
          SVG.text(^.attr("y") := textY, ^.cls := sectionStyle.className, name),
          {
            row += 1

            for
              el <- units
              (h, content) = svg(row, el)
              c = {
                row += h
                content
              }
            yield c
          }
        )
    },
  )

}
