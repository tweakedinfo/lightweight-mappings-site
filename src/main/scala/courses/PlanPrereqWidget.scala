package courses

import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, DElement, SVG, Styling, VHtmlComponent, ^}
import org.scalajs.dom

/**
 * Lays out the structure of a course vertically, so that groups of subjects can be seen
 * more easily
 */
case class PlanPrereqWidget(plan:Plan) extends VHtmlComponent {

  import acssite.given

  val leftMargin = "50px"

  val unitStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |font-size: 15px;
      |dominant-baseline: middle;
      |padding-left: 2px;
      |padding-right: 5px;
      |background-color: #eee;
      |border-radius: 2px;
      |height: 42px;
      |""".stripMargin
  ).modifiedBy(
    " .code" -> "font-size: 13px;",
    " .requires" -> "font-style: italic; font-size: 13px; color: #d55;",
    " .tag" -> "margin: 2px; padding: 2px 5px 2px 5px; background-color: #add; font-size: 12px;",
    " .tag.Capstone" -> "background: #dad",
    " .tag.Applied" -> "background: #dda",
  ).register()

  val sectionStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |font-size: 18px;
      |fill: black;
      |dominant-baseline: middle;
      |""".stripMargin
  ).modifiedBy(
  ).register()

  val orStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |font-size: 15px;
      |font-style: italic;
      |fill: black;
      |dominant-baseline: middle;
      |text-anchor: end;
      |""".stripMargin
  ).modifiedBy(
  ).register()

  val chooseStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |font-size: 15px;
      |font-style: italic;
      |fill: black;
      |dominant-baseline: bottom;
      |text-anchor: middle;
      |""".stripMargin
  ).modifiedBy(
  ).register()

  val rowH = 50
  val halfH = rowH / 2

  val unitBlockStart = 50 - 4
  val unitBlockWidth = 600 + 8

  def subjectDetails(s:Subject) = <.div(^.cls := unitStyle.className,
    <.div(<.span(^.cls := "code", s.code), " ", 
    <.span(^.cls := "name"), s.name, " ", for t <- s.tags yield <.span(<.span(^.cls := s"tag $t", t), " ")),
    <.div(^.cls := "requires", "requires: ", s.prereq.stringify)
  )

  def subjectDetailsSVG(s:Subject) = SVG.g(^.attr("transform") := "translate(0, 15)", ^.cls := unitStyle.className,
    SVG.text(<("tspan", ns=DElement.svgNS)(^.cls := "code", s.code),<("tspan", ns=DElement.svgNS)(" "),
      <("tspan", ns = DElement.svgNS)(^.cls := "name", s.name, " "),
      (for 
        t <- s.tags 
        el <- Seq(<("tspan", ns=DElement.svgNS)(^.cls := s"tag $t", t), <("tspan", ns=DElement.svgNS)(" "))
      yield el)
    ),
    SVG.g(^.attr("transform") := "translate(0, 25)",
      SVG.text(^.cls := "requires", "requires: ", s.prereq.stringify)
    )
  )

  def subjectBox(offset:Int, s:Subject) = SVG.g(^.attr("transform") := s"translate(50, ${offset * rowH})",
    SVG.foreignObject(^.attr("width") := unitBlockWidth, ^.attr("height") := rowH - 4,
      subjectDetails(s)
    )   
  )

  def labelledBox(offset:Int, s:String) = SVG.g(^.attr("transform") := s"translate(50, ${offset * rowH})",
    SVG.foreignObject(^.attr("width") := unitBlockWidth, ^.attr("height") := rowH - 4,
      <.div(^.cls := unitStyle.className,
        <.div(^.cls := "code", " "),
        <.div(^.cls := "name", s)
      )
    )   
  )

  def singleUnitBox(offset:Int, s:String) = 
    subjects.find(_.code == s) match {
      case Some(subj) => subjectBox(offset, subj)
      case None => labelledBox(offset, s)
    }


  def svg(offset:Int, el:PrereqElement):(Int, <.SingleChild[dom.svg.G]) = el match {
    case s:String =>
      1 -> singleUnitBox(offset, s)
      /*SVG.g(
        SVG.rect(^.attr("x") := unitBlockStart, ^.attr("width") := unitBlockWidth, ^.attr("y") := offset * rowH + 2, ^.attr("height") := rowH - 4, ^.attr("fill") := "rgba(120, 120, 120, 0.2)" ),
        SVG.text(^.attr("x") := 50, ^.attr("y") := offset * rowH + halfH, ^.cls := unitStyle.className, unitText(s)),
      )*/

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
      val startX = unitBlockStart + unitBlockWidth
      val startY = startRow * rowH + rowH - 6
      val endY = endRow * rowH + 6

      val midXOffset = 50 + (startRow % 40) * 10 + number
      val midY = (startY + endY) / 2
      val endsX = startX //(endRow * 25) + (number * 5)
      SVG.path(
     //   ^.attr("d") := s"M $startX $startY C ${startX + midXOffset} $startY  ${startX + midXOffset} $endY $endsX $endY l 4 -4 m -4 4 l 4 4",
        ^.attr("d") := s"M $startX ${startY + number} l ${midXOffset - 5} 0 q 5 0 5 5 L ${startX + midXOffset} $endY q 0 5 -5 5 l ${5 - midXOffset} 0 l 4 -4 m -4 4 l 4 4",
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
    //prereqLines(plan, None),
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
