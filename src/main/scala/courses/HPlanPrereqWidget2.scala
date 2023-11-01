package courses

import com.wbillingsley.veautiful.*
import com.wbillingsley.veautiful.html.{<, ^, DHtmlComponent, SVG, VHtmlContent, Styling}
import svg.{DSvgComponent, DSvgModifier}

import acssite.given

val planRowStyle = Styling(
    """|display: grid;
       |grid-template-columns: 150px 1fr;
       |border-top: 1px dotted rgb(140, 140, 140);
       |""".stripMargin
).modifiedBy(
    " .row" -> "display: flex; flex-wrap: nowrap;",

    " .or-box" -> "border: 1px solid rgb(200, 200, 200); border-radius: 5px; background: rgb(240, 240, 245); font-style: italic; margin: 10px; display: flex; flex-direction: column; flex-basis: min-content;",
    " .single-unit-box" -> "border: 1px solid white; border-radius: 5px; background: white; font-style: italic; margin: 10px 0 10px 0;",
    " .choice-line" -> "height: 20px; text-align: center;",
    " .notes" -> "text-align: center; font-size: 12px; margin: 6px;"
).register()


val subjectBoxStyle = Styling(
    """|display: inline-block;
       |width: 180px;
       |text-align: center;
       |margin: 10px;
       |""".stripMargin
).modifiedBy(
    " .top-handle" -> "width: 100%; display:block; text-align: center;",
    " .bottom-handle" -> "width: 100%; display:block; text-align: center;",
    " .subject-details" -> "border-radius: 5px; border: 1px solid; height: 180px; padding: 50px 5px 5px 5px; position: relative; top: 0; background: white; font-style: normal;",
    " .subject-details.selected" -> "background: rgb(240, 240, 220);",

    " .code" -> "font-size: 11px;",
    " .name" -> "font-size: 16px;",
    " .component" -> "position: absolute; top: 0; font-style: italic; font-size: 12px; color: orange;",
    " .tag" -> "margin: 2px; padding: 2px 5px 2px 5px; background-color: #add; font-size: 12px;",
    " .tag.Capstone" -> "background: #dad",
    " .tag.Applied" -> "background: #dda",
).register()

val prereqLines = Styling(
    """|position: absolute;
       |top: 0;
       |pointer-events: none;
       |""".stripMargin
).modifiedBy(
    " line" -> "stroke-width: 1px;",
    " .fixed" -> "stroke: red;",
    " .choice" -> "stroke: #aab;"
).register()

class SubjectBox(course:Course, val s:Subject)(selected: => Option[Subject], onClick: () => Unit) extends DHtmlComponent with Keep(s) {

    val topHandle = <.svg(^.cls := "top-handle", ^.attr.width := "10", ^.attr.height := "5", ^.attr.viewBox := "-5 -5 10 5", 
        SVG.circle(^.attr.cx := "0", ^.attr.cy := "0", ^.attr.r := "5")
    ).build()

    /** For rendering the pre-requisite lines, we need to know where the browser laid out the handles to be */
    def topHandlePos() = for n <- topHandle.domNode yield 
        val r = n.getBoundingClientRect()
        (r.x + r.width / 2, r.y + 5)

    /** For rendering the pre-requisite lines, we need to know where the browser laid out the handles to be */
    def bottomHandlePos() = for n <- bottomHandle.domNode yield 
        val r = n.getBoundingClientRect()
        (r.x + r.width / 2, r.y)

                
    val bottomHandle = <.svg(^.cls := "bottom-handle", ^.attr.width := "10", ^.attr.height := "5", ^.attr.viewBox := "-5 0 10 5", 
        SVG.circle(^.attr.cx := "0", ^.attr.cy := "0", ^.attr.r := "5")
    ).build()

    lazy val componentName = course.structure.findComponentName(PrereqElement.unit(s.code))

    def subjectDetails = <.div(^.cls := (if selected.contains(s) then "subject-details selected" else "subject-details"),
        //^.cls := subjectDetailsStyle.className,
        
        <.div(^.cls := "code", s.code),
        <.div(^.cls := "name", s.name),
        <.div(^.cls := "component", componentName),
        <.div(^.cls := "tags", 
            for t <- s.tags yield <.span(<.span(^.cls := s"tag $t", t), " "), 
        )
    )

    def render = <.div(^.cls := subjectBoxStyle,
        ^.onClick --> onClick(),

        topHandle, 
        subjectDetails,
        bottomHandle
    )

}


case class HPlanPrereqWidget2(course:Course, plan:Plan) extends DHtmlComponent {

    val selected = stateVariable[Option[Subject]](None)

    def toggle(s:Subject):Unit = 
        if selected.value.contains(s) then selected.value = None else selected.value = Some(s)

    def widget(e:PrereqElement):(Seq[SubjectBox], VHtmlContent) = e match {
        case u:PrereqElement.unit => 
            val s = u.toSubject
            val sb = SubjectBox(course, s)(selected.value, () => toggle(s))
            Seq(sb) -> <.div(^.cls := "single-unit-box", 
                <.div(^.cls := "choice-line",
                    ""
                ),

                <.div(^.style := "display: flex;",
                    sb
                ),

                <.div(^.cls := "notes",
                  for (_, text) <- e.notes yield <.div(text)            
                )
            )

        case PrereqElement.or(a, b) =>
            val sa = a.toSubject
            val sb = b.toSubject

            val ssa = SubjectBox(course, sa)(selected.value, () => toggle(sa))
            val ssb = SubjectBox(course, sb)(selected.value, () => toggle(sb))

            Seq(ssa, ssb) -> <.div(^.cls := "or-box", 
                <.div(^.cls := "choice-line",
                    "or"
                ),

                <.div(^.style := "display: flex;",
                    ssa, ssb
                ),

                <.div(^.cls := "notes",
                  for (_, text) <- e.notes yield <.div(text)            
                )
            )

        case PrereqElement.choose(num, els) =>
            val sbs = for u <- els yield
                val s = u.toSubject
                SubjectBox(course, s)(selected.value, () => toggle(s))

            sbs -> <.div(^.cls := "or-box", 
                <.div(^.cls := "choice-line",
                    num match {
                        case (from, to) => s"choose $from - $to"
                        case num => s"choose $num"
                    }
                ),

                <.div(^.style := "display: flex;",
                    sbs
                ), 

                <.div(^.cls := "notes",
                  for (_, text) <- e.notes yield <.div(text)            
                )
            )

        case PrereqElement.cp(i) =>
            // doesn't make sense in a horizontal plan widget
            Seq.empty -> <.div(

            )


        case PrereqElement.coreq(els) =>
            // doesn't make sense in a horizontal plan widget
            Seq.empty -> <.div(

            )

    }

    lazy val widgetStructure = 
        for 
            (name, entries) <- plan
        yield 
            name -> (for entry <- entries yield widget(entry))

    lazy val subjectBoxes = 
        (for 
            (_, row) <- widgetStructure
            (boxes, _) <- row
            box <- boxes
        yield box.s -> box).toMap

    
    /** When rendering the SVG pre-requisite lines, we need to know the plan's relative position, width, and height */
    def clientRect() = for n <- domNode yield n.getBoundingClientRect()

    object PrereqLines extends DSvgComponent {
        override def render = {
            val ocr = clientRect()
            val subjects = if selected.value.nonEmpty then selected.value.toSeq else subjectBoxes.keySet.toSeq

            <.svg(^.cls := prereqLines,
                (for 
                    cr <- ocr.toSeq
                    m <- Seq(
                        ^.attr.viewPort := s"0 0 ${cr.width} ${cr.height}", 
                        ^.attr.width := cr.width, ^.attr.height := cr.height,
                    )
                yield m),


                SVG.g(
                        for 
                            cr <- ocr.toSeq
                            s <- subjects
                            destination <- subjectBoxes.get(s).toSeq
                            (x1, y1) <- destination.topHandlePos().toSeq
                            prereq <- s.prereq                            
                        yield SVG.g(
                            prereq match {
                                case p:PrereqElement.unit => 
                                    for 
                                        source <- subjectBoxes.get(p.toSubject)
                                        (x2, y2) <- source.bottomHandlePos()
                                    yield SVG.line(^.cls := "prereq-line fixed",
                                        ^.attr.x1 := (x1 - cr.x), 
                                        ^.attr.x2 := (x2 - cr.x),
                                        ^.attr.y1 := (y1 - cr.y), 
                                        ^.attr.y2 := (y2 - cr.y)
                                    )

                                case _ => 
                                    for 
                                        subj <- prereq.flattened
                                        source <- subjectBoxes.get(subj.toSubject)
                                        (x2, y2) <- source.bottomHandlePos()
                                    yield SVG.line(^.cls := "prereq-line choice",
                                        ^.attr.x1 := (x1 - cr.x), 
                                        ^.attr.x2 := (x2 - cr.x),
                                        ^.attr.y1 := (y1 - cr.y), 
                                        ^.attr.y2 := (y2 - cr.y)
                                    )
                            }

                        )       
                    )
                ) 
        }
    }

    override def render = {

        <.div(^.style := "position: relative; top: 0;",
            for 
                (name, entries) <- widgetStructure 
            yield
                <.div(^.cls := planRowStyle,
                    <.div(name), 
                    <.div(^.cls := " row",
                        for (_, widget) <- entries yield widget
                    )
                )

            ,

            PrereqLines

            

        )
    }

}