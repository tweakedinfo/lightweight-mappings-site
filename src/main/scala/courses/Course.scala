package courses
import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, ^}
import acssite.Common

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

type PlanComponent = (String, Seq[PrereqElement])
type Plan = Seq[PlanComponent]

extension (p:Plan) {
  def findComponentName(s:Subject):Option[String] = 
    p.find((_, els) => els.flattened.contains(s.code)).map((name, _) => name)
}

def isMandatoryInPC(unit:Subject, planComponent: PlanComponent) =
  val (_, els) = planComponent
  els.exists(p => isMandatoryIn(unit.code, p))

// Turns a plan into a sequence of the main string in each row.
// Used to work out what row a unit is in, when drawing pre-req arrows
def flatStrings(plan: Plan):Seq[String] =
  plan.flatMap {
    case (name, els) => Seq(name) ++ els.flatMap(subjCodes)
  }


case class Course(
  code:String, name:String,
  structure: Plan,
  plans: Seq[(String, Plan)]
)

trait JSPlanComponent extends js.Object:
  val name:String
  val units:js.Array[PrereqElement]

trait JSCourse extends js.Object:
  val code:String
  val name:String
  val structure:js.Array[JSPlanComponent]
  val plans:js.Dictionary[js.Array[JSPlanComponent]]

given toScala:Conversion[JSPlanComponent, PlanComponent] with
  def apply(j:JSPlanComponent) = (j.name, j.units.toSeq)

val courses = mutable.Buffer.empty[Course]


@JSExportTopLevel("addCourse")
def addCourse(config:JSCourse): Unit = {
  try {
    val c = Course(
      code = config.code,
      name = config.name,
      structure = config.structure.map(toScala).toSeq,
      plans = for (name, jsplan) <- config.plans.toSeq yield
        name -> jsplan.map(toScala).toSeq
    )

    courses.append(c)
  } catch {
    case x:Throwable =>
      println("Failed to load a course!")
      println(x.getMessage())
      // TODO: handle errors
  }
}

@JSExportTopLevel("addCourses")
def addCourses(courses:js.Array[JSCourse]): Unit = {
  for c <- courses do addCourse(c)
}


def planPage(c:Course) = Unique(<.div(
  <.h1(c.name),
  <.p(
    <.a(^.href := s"https://handbook.une.edu.au/courses/2022/${c.code}?year=2022", "Link to handbook entry")
  ),
  Common.markdown(
    """ The basic structure of the course is shown below. 
      | Scroll further down for course plans annotated with prerequisites to visualise Form 2 of ACS. 
      | For canonical details on course structures, please see the handbook entry.
      |""".stripMargin
  ),
  <.h3("Structure"),
  PlanPrereqWidget(c.structure),
  <.h3("Course plans for visualisation"),
  Common.markdown(
    """ Select a plan from the drop-down list. Fixed pre-requisites are shown in red. 
      | Prerequisites that include some choice (i.e. assumed knowledge) are shown in grey.
      | Click on a unit to show only prerequisite lines that unit is involved in.
      | Click on the unit again to deselect it.
      |""".stripMargin
  ),
  <.div(
    HPlanChooser(c)
  )

))

def cbokPage(c:Course) = Unique(<.div(
    <.h1(c.name),
    <.p(
      <.a(^.href := s"https://handbook.une.edu.au/courses/2022/${c.code}?year=2022", "Link to handbook entry")
    ),
    Common.markdown(
      """ The table below is generated from the data files for each unit within the course structure.
        | (Consequently, it might sometimes identify more than 3 units in a column).
        |
        | Optional units have been included, as frequently (e.g. COSC101 or COSC102) there are two alternatives
        | but either path provides coverage of a CBoK aspect (e.g. teamwork).
        |""".stripMargin
    ),
    cbokGrid(c.structure),
  )
)