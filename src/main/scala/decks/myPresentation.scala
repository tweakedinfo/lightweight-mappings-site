package decks

import com.wbillingsley.veautiful.html._
import com.wbillingsley.veautiful.doctacular.DeckBuilder
import org.scalajs.dom

import scalajs.js
import scala.util.Random

import courses.* 
import mappings.*
import ui.* 
import Common.given

/* 
 * As this is based on Veautiful, we can define custom elements like this. 
 * In a veatiful slide we can now say
 * scrollableDiv(someContent)
 * and it'll put it in a scrollable dif
 */
def scrollableDiv = <.div(^.attr("style") := "height: 800px; overflow-y: scroll")

/*
 * This "extension" lets us quickly define image slides with captions next to them.
 * See the examples later down, where in the middle of building the deck, we say
 * .imageSlide(caption, url)
 * and then get back to adding other kinds of slides
 */
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

/*
 * The code of our sample presentation
 */
val myPresentation = DeckBuilder(1920, 1080).markdownSlide("""
  |# A cut-down sample presentation
  |
  |Will Billingsley  
  |University of New England, Australia  
  |wbilling@une.edu.au
  |""".stripMargin).withClass("center middle")
  .markdownSlides("""
  |### A quick note...
  |
  |To avoid spamming your repository with my images, I've removed the images from the images folder.
  |
  |That means those images won't load, but I've left the code in as a sample for *how* to load them.
  |
  |Likewise, your repository probably doesn't have the same courses I showed in this deck. So those
  |won't load either, but I've left the code in as an example
  |
  |---
  |
  |### Mostly this is markedown
  |
  |In 2020, introducing the Diploma of IT in the pandemic while down on staff, I built a kit that
  |let my build interactive OERs quickly.
  |
  |Mostly, these decks are markdown, but we can also include interactive stuff
  |
  |e.g. 
  |
  |- https://theintelligentbook.com/circuitsup/#/latches/0/0
  |- https://theintelligentbook.com/thinkingaboutprogramming/#/decks/state/4/fullscreen
  |
  |
  |[Veautiful / Doctacular](http://www.wbillingsley.com/veautiful)
  |
  |and I'd had some code to map our units, in order to ensure that all our course plans and pre-reqs worked,
  |which I'd used since 2017ish
  |
  |So (spoiler) we'll get to a set of mappings built so they can be sketched and shown easily 
  |in interactive decks and sites
  |
  |---
  |
  |## The images for the following slides have been removed
  |
  |The next four slides reger to images that aren't there. The template that lets you render an image slide
  |with a caption is in the repo though, so this might be a useful code example
  |
  |---
  |
  |""".stripMargin)
  .imageSlide("Paper based exams are inconvenient and involve being watched", "images/human invigilated.jpg")
  .markdownSlide("""
  |## The next slide is a code sample
  |
  |It's coded directly in Veautiful, and puts in a Scrollable div.
  |
  |Inside the div, it shows the course from courses.js called BCOMP(SD)
  |
  |That course might no longer be in your courses.js, in which case the course won't show!
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
      for c <- courses.find(_.code == "BCOMP(SD)") yield cbok.cbokPage(c)
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