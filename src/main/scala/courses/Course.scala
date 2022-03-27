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

def isMandatoryInCourse(unit:Subject, course: Course) =
  course.structure.exists {
    case (_, els) => 
      els.exists(p => isMandatoryIn(unit.code, p))   
  }

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

// Limits the rows shown in the CBOK table for a course
val topCbok:mutable.Map[String, Map[CBOK, Seq[String]]] = mutable.Map.empty

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

@JSExportTopLevel("limitCBOK")
def limitCbok(courses:js.Array[String], category:CBOK, units:js.Array[String]) = {
  for course <- courses do
    topCbok(course) = topCbok.getOrElse(course, Map.empty) + (category -> units.toSeq)
}

def planPage(c:Course) = Unique(<.div(
  <.h1(c.name),
  <.p(
    <.a(^.href := s"https://handbook.une.edu.au/courses/2022/${c.code}?year=2022", "Link to handbook entry")
  ),
  Common.markdown(
    """ The structure of the course is illustrated below. 
      | Trimester-by-trimester plans are visualised further down the page. 
      | 
      | * Advanced units are tagged "Advanced"
      | * Integrated and Applied units (Criterion E) are tagged "Capstone" 
      |""".stripMargin
  ),
  <.h3("Structure"),
  PlanPrereqWidget(c.structure),
  <.h3("Trimester-by-trimester plans"),
  Common.markdown(
    """ 
      | * A thicker border indicates a mandatory unit. 
      |   Note that in some cases, there is a choice between two core units (causing neither to appear marked as mandatory here).
      |   These can be seen more clearly in the course structure illustration at the top of the page. 
      | * Fixed pre-requisites are shown in red. 
      | * Prerequisites that include some choice (i.e. assumed knowledge) are shown in grey.
      | * Click on a unit to show only prerequisite lines that unit is involved in.
      | * Click on the unit again (or on the background) to deselect it.
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
        | A toggle is provided to toggle between:
        |
        | * showing only the selected top 3 core units for each course (to show coverage of CBoK in core units) 
        | * showing where that CBoK item appears in all units (to show scaffolding, or in-depth knowledge in advanced units, such as within majors)
        |
        | Where there is a choice between units, the number to select is shown next to the rows, and the rows are faded.
        | These have been included as we sometimes allow choice between two units offering a skill such as teamwork.
        | (e.g. in the BCompSc, how teamwork is scaffolded from COSC101 and COSC102 in first year, rather than only showing
        | its mandatory presence in units in later years).
        |""".stripMargin
    ),
    CBOKGridComponent(c, c.structure)
  )
)


def swebokPage(c:Course) = Unique(<.div(
    <.h1(c.name),
    <.p(
      <.a(^.href := s"https://handbook.une.edu.au/courses/2022/${c.code}?year=2022", "Link to handbook entry")
    ),
    Common.markdown(
      """ The table below shows top-level SWEBOK categories that are relevant to each unit in the course
        |
        |""".stripMargin
    ),
    booleanCategoryGrid[SWEBOK](c.structure, SWEBOK.values.toSeq) {
      (s, cat) => s.swebok.contains(cat)
    },
  )
)

def dsbokPage(c:Course) = Unique(<.div(
    <.h1("Data Science bodies of knoweledge"),
    <.h2(c.name),
    <.p(
      <.a(^.href := s"https://handbook.une.edu.au/courses/2022/${c.code}?year=2022", "Link to handbook entry")
    ),
    Common.markdown(
      """
        |This page maps units in the course against ACM and Edison bodies of knowledge for data science.
        |Note however, that as the criteria ask for 1 EFTSL of content drawn from these bodies of knowledge,
        |rather than complete coverage of all bodies of knowledge, not every column may be covered.
        | 
        |### ACM Computing Competencies for Undergraduate Data Science Curricula (CCDS)
        |
        |""".stripMargin
    ),
    booleanCategoryGrid[CCDSC](c.structure, CCDSC.values.toSeq) {
      (s, cat) => s.dsbok.contains(cat)
    },
    Common.markdown(
      """
        |### Edison Data Science Body of Knowledge
        | 
        |The table below maps units to Knowledge Areas (KAs) in the Edison Data Science Body of Knowledge.
        |KAs within the same Knowledge Area Group (KAG) are given similar colouring. 
        |""".stripMargin
    ),
    booleanCategoryGrid[EdisonDSBOK](c.structure, EdisonDSBOK.values.toSeq) {
      (s, cat) => s.dsbok.contains(cat)
    },
  )
)

def idverifyPage(c:Course) = Unique(<.div(
    <.h1("Methods of Identity Management"),
    <.h2(c.name),
    <.p(
      <.a(^.href := s"https://handbook.une.edu.au/courses/2022/${c.code}?year=2022", "Link to handbook entry")
    ),
    Common.markdown(
      """
        |This page maps units in the course against different mechanisms that are used in assessment to support
        |identity management and deter or detect academic misconduct
        |""".stripMargin
    ),
    booleanCategoryGrid[IdentityVerification](c.structure, IdentityVerification.values.toSeq) {
      (s, cat) => s.dsbok.contains(cat)
    },
  )
)
