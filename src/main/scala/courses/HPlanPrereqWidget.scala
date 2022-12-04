package courses

import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, DElement, SVG, Styling, VHtmlComponent, ^, EventMethods}
import com.wbillingsley.veautiful.svg.DSvgContent
import org.scalajs.dom


case class HPlanChooser(course:Course) extends VHtmlComponent {

  var selected = course.plans.headOption.map(_._1)

  def render = {
    <.div(
      <("select")(
        ^.on("change") ==> { (evt) =>
          selected = evt.inputValue
          rerender()
        },
        ^.attr("name") := "Plan",
        for 
          (name, plan) <- course.plans
        yield
          <("option")(^.attr("value") := name, name)
      ),
      <.div(
        for 
          name <- selected
          (_, plan) <- course.plans.find((n, _) => n == name)
        yield HPlanPrereqWidget(course, plan)
      )
    )
  }

}

/**
 * A Plan pre-requisite widget that lays out units in a study-period left to right to better
 * match ACS expectations
 */ 
case class HPlanPrereqWidget(course:Course, plan:Plan) extends VHtmlComponent {

  import acssite.given

  val leftMargin = "50px"

  val bgStyle = Styling(
    """
    |fill: white;
    |stroke: none;
    |""".stripMargin
  ).modifiedBy(
  ).register()


  val subjectBoxStyle = Styling(
    """
    |fill: white;
    |stroke: #888;
    |""".stripMargin
  ).modifiedBy(
      ".selected" -> "fill: rgb(240, 240, 220);",
      " .mandatory" -> "stroke-width: 2;",
      " .code" -> "font-size: 11px;",
      " .name" -> "",
      " .prereq-anchor" -> "fill: #777; stroke: #aaa;"
  ).register()


  val subjectDetailsStyle = Styling(
    """
    |height: 200px;
    |width: 200px;
    |display: inline-block;
    |font-family: 'Lato', sans-serif;
    |size: 12px;
    |fill: black;
    |text-align: center;
    |padding: 50px 5px 5px 5px;
    |""".stripMargin
  ).modifiedBy(
      " .code" -> "font-size: 11px;",
      " .name" -> "",
      " .component" -> "position: absolute; top: 0; font-style: italic; font-size: 12px; color: orange;",
      " .tag" -> "margin: 2px; padding: 2px 5px 2px 5px; background-color: #add; font-size: 12px;",
      " .tag.Capstone" -> "background: #dad",
      " .tag.Applied" -> "background: #dda",
  ).register()

  val sectionStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |size: 12px;
      |fill: black;
      |dominant-baseline: middle;
      |""".stripMargin
  ).modifiedBy(
  ).register()

  val prereqLineStyle = Styling(
    """stroke: black;
      |""".stripMargin
  ).modifiedBy(
    ".fixed" -> "stroke: red;",
    ".choice" -> "stroke: #aaa;"
  ).register()

  val orStyle = Styling(
    """font-family: 'Lato', sans-serif;
      |size: 12px;
      |fill: rgb(240, 240, 245);
      |stroke: rgb(200, 200, 200);
      |dominant-baseline: hanging;
      |text-anchor: middle;
      |""".stripMargin
  ).modifiedBy(
    " .orLabel" -> "fill: black; stroke: none; font-style: italic;"
  ).register()

  val titleGutter = 150
  val unitBlockWidth = 200
  val unitBlockHeight = 200
  val unitColumnWidth = 220
  val planRowHeight = 250
  val maxColumns = 8

  val bracketMargin = 5
  val bracketLabelHeight = 15

  // We need to adjust the height of the image to allow an "or" or similar grouping in the first row
  val labelSpaceY = 2 * bracketMargin + bracketLabelHeight

  var selected:Option[Subject] = None
  def toggle(s:Subject) = {
    if selected.contains(s) then 
      selected = None
      rerender()
    else
      selected = Some(s)
      rerender()
  }

  def unselect() =
    selected = None
    rerender()

  def subjectDetails(s:Subject) = <.div(
      ^.cls := subjectDetailsStyle.className,
      
      <.div(^.cls := "code", s.code),
      <.div(^.cls := "name", s.name),
      <.div(^.cls := "component", course.structure.findComponentName(s)),
      <.div(^.cls := "tags", 
        for t <- s.tags yield <.span(<.span(^.cls := s"tag $t", t), " "), 
      )
  )

  def subjectBox(s:Subject) = SVG.g(
    ^.cls := (if selected.contains(s) then subjectBoxStyle.className + " selected" else subjectBoxStyle.className),
    ^.onClick --> toggle(s),
    SVG.rect(
      ^.cls := (if isMandatoryInCourse(s, course) then "mandatory" else "optional"),
      ^.attr("width") := unitBlockWidth, ^.attr("height") := unitBlockHeight, ^.attr("ry") := 5
    ),
    SVG.circle(^.attr("cx") := unitBlockWidth / 2, ^.attr("cy") := 25, ^.attr("r") := 5, ^.cls := "prereq-anchor"),
    SVG.circle(^.attr("cx") := unitBlockWidth / 2, ^.attr("cy") := unitBlockHeight - 25, ^.attr("r") := 5, ^.cls := "prereq-anchor"),
    SVG.foreignObject(^.attr("width") := unitBlockWidth, ^.attr("height") := unitBlockHeight,
      subjectDetails(s)
    )   
  )

  def labelledBox(s:String) = SVG.g(^.cls := subjectBoxStyle.className,
    SVG.rect(^.attr("width") := unitBlockWidth, ^.attr("height") := unitBlockHeight, ^.attr("ry") := 5),
    SVG.foreignObject(^.attr("width") := unitBlockWidth, ^.attr("height") := unitBlockHeight,
      <.div(^.cls := subjectDetailsStyle.className,
        <.div(^.cls := "code", " "),
        <.div(^.cls := "name", s)
      )
    )   
  )

  def singleUnitBox(s:String) = 
    subjects.find(_.code == s) match {
      case Some(subj) => subjectBox(subj)
      case None => labelledBox(s)
    }

  def columnX(i:Int, gutter:Boolean = false):Int = 
      if gutter then titleGutter + (i * unitColumnWidth) else (i * unitColumnWidth)

  def columnTransform(i:Int, gutter:Boolean = false):String = s"translate(${columnX(i, gutter)}, 0)"


  def svg(el:PrereqElement):(Int, DSvgContent) = el match {
    // A single unit is 
    case s:String =>
      1 -> singleUnitBox(s)
      
    case ComplexPrereqElement.or(a, b) =>
      2 -> SVG.g(^.cls := orStyle.className,
        SVG.rect(^.cls := "orBracket",
          ^.attr("x") := -bracketMargin, ^.attr("y") := -labelSpaceY, 
          ^.attr("width") := (2 * bracketMargin + unitColumnWidth + unitBlockWidth), 
          ^.attr("height") := (unitBlockHeight + labelSpaceY + bracketMargin),
          ^.attr("ry") := 5
        ),
        SVG.text(^.cls := "orLabel", 
          ^.attr("x") := (2 * bracketMargin + unitColumnWidth + unitBlockWidth) / 2,
          ^.attr("y") := - bracketMargin - bracketLabelHeight,
          "or"
        ),
        singleUnitBox(a),
        SVG.g(^.attr("transform") := columnTransform(1, false),
          singleUnitBox(b)
        )
      )

    case ComplexPrereqElement.choose(num, els:_*) =>
      els.length -> SVG.g(^.cls := orStyle.className,
        SVG.rect(^.cls := "orBracket",
          ^.attr("x") := -bracketMargin, ^.attr("y") := -labelSpaceY, 
          ^.attr("width") := (2 * bracketMargin + (els.length - 1) * unitColumnWidth + unitBlockWidth), 
          ^.attr("height") := (unitBlockHeight + labelSpaceY + bracketMargin),
          ^.attr("ry") := 5
        ),
        SVG.text(^.cls := "orLabel", 
          ^.attr("x") := (2 * bracketMargin + (els.length - 1) * unitColumnWidth + unitBlockWidth) / 2,
          ^.attr("y") := - bracketMargin - bracketLabelHeight,
          s"choose $num"
        ),
        for 
          (unit, i) <- els.zipWithIndex
        yield 
          SVG.g(^.attr("transform") := columnTransform(i, false),
            singleUnitBox(unit)
          )
      )

    case _ => (0, SVG.g())
  }

  def prerequisiteLines(unit:Option[Subject] = None) = {
    val unitMap = (for 
      ((_, rowContent), y) <- plan.zipWithIndex
      (unitCode, x) <- rowContent.flattened.zipWithIndex
      subject <- subjects.find(_.code == unitCode)
    yield subject -> (x, y)).toMap

    def toBoxTop(p:(Int, Int)):(Int, Int) = {
      val (x, y) = p
      (
        unitBlockWidth / 2 + (x * unitColumnWidth),
        y * planRowHeight + 25
      )
    }

    def toBoxBottom(p:(Int, Int)):(Int, Int) = {
      val (x, y) = p
      (
        unitBlockWidth / 2 + (x * unitColumnWidth),
        y * planRowHeight + unitBlockHeight - 25
      )
    }

    def singleLine(from:(Int, Int), to:(Int, Int), cls:String) = {
      val (x1, y1) = toBoxTop(from)
      val (x2, y2) = toBoxBottom(to)
      SVG.line(
        ^.cls := prereqLineStyle.className + " " + cls,
        ^.attr("x1") := x1, ^.attr("y1") := y1, ^.attr("x2") := x2, ^.attr("y2") := y2,
      )
    }

    def lines(subject:Subject, prereq:PrereqElement) = {

      prereq match {
        case string:String => 
          for 
            s <- Seq(string)
            subj <- subjects.find(_.code == s) if unitMap.contains(subj) && (selected.isEmpty || selected.contains(subject) || selected.contains(subj))
          yield
            singleLine(unitMap(subject), unitMap(subj), "fixed")

        case ComplexPrereqElement.or(a, b) => 
          for 
            s <- Seq(a, b)
            subj <- subjects.find(_.code == s) if unitMap.contains(subj) && (selected.isEmpty || selected.contains(subject) || selected.contains(subj))
          yield
            singleLine(unitMap(subject), unitMap(subj), "choice")

        case ComplexPrereqElement.choose(num, els:_*) => 
          for 
            s <- els
            subj <- subjects.find(_.code == s) if unitMap.contains(subj) && (selected.isEmpty || selected.contains(subject) || selected.contains(subj))
          yield
            singleLine(unitMap(subject), unitMap(subj), "choice")

        case _ => Seq.empty 

      }
    }

    for 
      (s, (x, y)) <- unitMap
      el <- s.prereq
      line <- lines(s, el)
    yield line
  }

  override def render = {

    val height = plan.length * planRowHeight + labelSpaceY + bracketMargin

    def rowTransform(i:Int):String = s"translate(0, ${i * planRowHeight})"

    def rowLabel(name:String) = 
      SVG.text(^.attr("y") := 20, ^.cls := sectionStyle.className, name)

    SVG.svg(
      ^.attr("width") := (maxColumns * unitColumnWidth), ^.attr("height") := height,
      ^.attr("viewBox") := s"0 ${-labelSpaceY} ${maxColumns * unitColumnWidth} ${height - labelSpaceY}",

      SVG.rect(^.cls := bgStyle.className,
        ^.attr("x") := 0, ^.attr("y") := 0, 
        ^.attr("width") := maxColumns * unitColumnWidth, 
        ^.attr("height") := height,
        ^.onClick --> { selected = None; rerender(); }
      ),
      
      for {
        ((periodName, elements), row) <- plan.zipWithIndex
      } yield {
        SVG.g(
          ^.attr("transform") := rowTransform(row),
          rowLabel(periodName),

          {
            var column = 0
            val segments = elements.map(svg(_))

            for {
              (width, seg) <- segments
            } yield {
              val positioned = SVG.g(^.attr("transform") := columnTransform(column, true), seg)
              column += width
              positioned
            }
          }
        )
      },

      SVG.g(^.attr("transform") := columnTransform(0, true), prerequisiteLines())
    )
  }

}
