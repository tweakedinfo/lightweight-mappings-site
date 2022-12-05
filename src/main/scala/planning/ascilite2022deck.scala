package planning

import com.wbillingsley.veautiful.html._
import com.wbillingsley.veautiful.templates.DeckBuilder
import org.scalajs.dom

import scalajs.js
import scala.util.Random
import acssite.Common
import Common.given

import courses._

def scrollableDiv = <.div(^.attr("style") := "height: 800px; overflow-y: scroll")

extension (db:DeckBuilder) {
  def imageSlide(caption:String, url:String) = db.veautifulSlide(
    <.div(^.style := "height: 1080px; position: relative; top: 0; margin-left: 2%; margin-right: 2%",
        <.div(<.img(^.src := url, ^.style := "max-height: 1080px;")),
        <.label(caption, ^.style := "position: absolute; top: 0; left:60%; padding: 1em; font-size: 42px; color: black;")
    )
  )

  def mistyroseImageSlide(caption:String, url:String) = db.veautifulSlide(
    <.div(^.style := "height: 1080px; position: relative; top: 0; margin-left: 2%; margin-right: 2%",
        <.div(<.img(^.src := url, ^.style := "max-height: 1080px;")),
        <.label(caption, ^.style := "position: absolute; top: 0; left:60%; padding: 1em; background: mistyrose; font-size: 42px; color: black;")
    )
  )
}

val ascilite2022deck = DeckBuilder(1920, 1080).markdownSlide("""
  |# Lightweight mapping of identity verification methods and secondary course aspects: "Swiss cheese" modelling.
  |Will Billingsley  
  |University of New England, Australia  
  |wbilling@une.edu.au
  |""".stripMargin).withClass("center middle")
  .markdownSlides("""
  |### A less formal introduction...
  |
  |In 2022, I needed to do our accreditation with the Australian Computer Society (ACS), right after the pandemic
  |
  |Like most universities, we'd lost our paper-based exams.
  |
  |""".stripMargin)
  .imageSlide("Paper based exams are inconvenient and involve being watched", "images/human invigilated.jpg")
  .imageSlide("...but somehow students feared online proctoring more", "images/online proctored.jpg")
  .imageSlide("Although we understand how proctoring works and our exams team support it...", "images/proctor view.jpg")
  .imageSlide("...computing academics elsewhere keep expressing skepticism", "images/cheating.jpg")
  .veautifulSlide(div(
    h3("UNE Assessment policy"),
    Common.markdown(
      """|
         |Clauses 42 and 43: Students could opt out of proctoring by deciding not to consent to the collection of personal information. 
         |Academics had to set "alternative assessment"
         |
         |In some computing units, more than half the students opted out in 2020.
         |""".stripMargin),
    blockquote(^.style := "opacity: 0.5; background: #eee;",
      Common.markdown(
        """|42. **If a student** has considered the requirements for online examinations in clause 35 and is unable to meet the systems or conditions requirements 
           |or **does not consent to the collection of personal information, the student must apply for an alternative assessment** through the UNE website within 
           |four (4) weeks of the commencement of the teaching period. If an examination is scheduled within the first four weeks of the teaching period, the 
           |application must be made at least one (1) week before the examination.
           |
           |43. Subject to any inherent requirements and the requirements of any accrediting body, **the Unit Coordinator will provide an alternative assessment** for 
           |completion within the relevant teaching period. As with all assessment tasks, alternative assessments must be designed in accordance with the provisions
           |of Part A of this Policy and must enable the student to demonstrate achievement of the learning outcomes assessed through the online examination. Unless 
           |specified by an accrediting body, **the alternative assessment is not required to be a supervised examination**.
           |""".stripMargin
      )
    ),
  ))
  .markdownSlides("""
  |
  |### A knock-on effect
  |
  |Many computing academics didn't like dealing with the online exams process and having to set an alternative assessment.
  |
  |So, we had a number of subjects that dropped proctoring and just used whatever their "alternative assessment" would have been.
  |
  |Many of those are quite creative, but they're up to the individual academic
  |
  |---
  |
  |### Australian Computer Society accreditation
  |
  |> ## On-Line Education
  |>
  |> d. **There will be mechanisms to address identity management in a virtual environment**.
  |
  |So how do I defensibly express what it is?
  |
  |Realistically, the strategy isn't just "online proctoring" but all the techniques we use in our alternative assessments and non-exam assessments to make misconduct less likely.
  |
  |Let's map them across the degree
  |
  |---
  |
  |### Multi-layered techniques to deter cheating have been successful at unit level
  |
  |* Crime prevention case study (Baird & Clare, 2017)
  |* "Why students choose not to cheat" (Rundle et al., 2020)
  |
  |I created mappings of the mechanisms of IVA used in eachsubject across each degree
  |
  |* Inspired by "Swiss Cheese" model of accident causation (Reason, 1990)
  |* Shift in perspective to seeing the degree, rather than an individual subject within it, as the item being defended against cheating
  |
  |
  |
  |""".stripMargin)
  .veautifulSlide(div(
    scrollableDiv(^.prop("scrollTop") := "120",
      for c <- courses.find(_.code == "BCOMP(SD)") yield idverifyPage(c)
    )
  ))
  .markdownSlides("""
  |
  |### Mapping elements of courses is long established
  |
  |* Visual maps of curricula (Hausman, 1974)
  |* Instructional alignment (Cohen, 1987)
  |* Constructive alignment (Biggs, 1996)
  |* Using them as ways of measuring what's important in a course (Veltri et al., 2015)
  |* Use as a design tool and for collaboration (Uchiyama & Rudin, 2009; Weingards-de Meij & Merx, 2018)
  |* Using course maps to visualise learning outcome progress back to students (Kay et al., 2022)
  |
  |
  |""".stripMargin)
  .mistyroseImageSlide("Constructive alignment and mapping of course elements is well established in Australia", "images/courseloop_mapping.png")
  .mistyroseImageSlide("But course maps have moved into the governance process. (Slow, not a design tool.)", "images/courseloop_proposals.png")
  .markdownSlides("""
  |### Lightweight mapping
  |
  |The identity verification maps are not ones we would wish to put into governance 
  |
  |- it'd require several committees (and months) to add or remove a video task
  |
  |But there are a lot of other mappings we need that are a snapshot in time too. 
  |
  |""".stripMargin)
  .veautifulSlide(div(
    scrollableDiv(
      for c <- courses.find(_.code == "BCOMP(SD)") yield planPage(c)
    )
  ))
  .veautifulSlide(div(
    scrollableDiv(
      for c <- courses.find(_.code == "BCOMP(SD)") yield cbokPage(c)
    )
  ))
  .veautifulSlide(div(
    scrollableDiv(
      for c <- courses.find(_.code == "BCOMP(SD)") yield swebokPage(c)
    )
  ))
  .veautifulSlide(div(
    scrollableDiv(
      for c <- courses.find(_.code == "MDSC") yield dsbokPage(c)
    )
  ))
  .markdownSlides("""
  |
  |### Maps bring the burden of creation
  |
  |Across several degrees, I had to do a lot of maps. This is only feasible if there's an easy way to do it.
  |
  | - We're the computer science discipline; so I built one.
  | - Maps are a visualisation, so I built it using the same kit I used to write this deck.
  |
  |Very fast edit cycle
  | - tweak the course/unit data and hit reload.
  | - this has let us use this as a **design tool** and embed it into course presentations (and this talk!)
  |
  |---
  |
  |### Conclusion
  |
  |* Swiss Cheese modelling - modelling the identity verification mechanisms across a degree
  |
  |* Keeping lightweight maps that are not in the governance process, but snapshots in time
  |
  |(and maps are visualisations so don't draw them; generate them.)
  |
  |""".stripMargin)
  .markdownSlide(Common.willCcBy).withClass("bottom")
  .renderSlides