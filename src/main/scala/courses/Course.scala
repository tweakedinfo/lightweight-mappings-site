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
        |
        | Levels shown in the grid are as per ACS template documents. 
        | 1 = Introductory (Blooms levels 1 & 2); 2 = Intermediate (Blooms level 3); 3 = Advanced (Blooms level 4 & 5)
        |""".stripMargin
    ),
    CBOKGridComponent(c, c.structure),  <("hr"),
    Common.markdown(
      s"""
        |#### How to edit this table 
        |
        |In `units.js`, this table is driven from the contents of the `cbok` array of each unit.
        |e.g.
        |
        |```js
        |{
        |  code: "FOO310",
        |  name: "Foo Management",
        |  prereq: [ choose(1, "COSC210", "COSC220") ],
        |  cbok: [ cbok.Governance.level(3), cbok.ProjectManagement.level(3) ],
        |  dsbok: [],
        |  sfia: [ "PROG" ],
        |  tags: ["Advanced"],
        |  other: [ idverify.ProctoredExam, idverify.GroupWork, idverify.TurnItIn, idverify.Video ]
        |},
        |```
        |
        |Functions for the columns (defined in `src/main/scala/courses/CBOK.scala`) are:
        |""".stripMargin
    ),
    <.ul(
      for e <- CBOK.values yield <.li(<("code")(s"cbok.${e.productPrefix}.level(i)"), " ", <("i")(e.name))
    ),
    Common.markdown("""
        |
        | Each function takes an int parameter for its level. E.g. `cbok.Ethics.level(3)`
        |
        | For convenience, these functions are also defined at the global level as:
        | 
        |`ethics`, `expectations`, `teamwork`, `communication`, `societal`, `understanding`, `problemSolving`,
        |`fundamentals`, `data`, `networking`, `humanFactors`, `programming`, `systems`,
        |`governance`, `projectManagement`, `serviceManagement`, `cybersecurity`
        |
        |So they can simply be called, e.g. `ethics(2)`
        |
        |#### How to limit units shown for each course
        |
        |Within `courses.js`, call `limitCBOK`. This takes an array of degrees, a CBOK column, and an array of unit codes. e.g.
        |
        |```js
        |limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Ethics, ["COSC110", "COSC310", "COSC320"])
        |```
        |
    """.stripMargin)
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
    }, <("hr"),
        Common.markdown(
      s"""
        |#### How to edit these tables 
        |
        |In `units.js`, these tables are driven from the contents of the `swebok` array of each unit.
        |e.g.
        |
        |```js
        |{
        |  code: "THI123",
        |  name: "Thingummy Design and Construction",
        |  prereq: [ choose(1, "COSC210", "COSC220") ],
        |  cbok: [ teamwork(2), communication(3) ],
        |  swebok: [ swebok.Design, swebok.Construction ],
        |  sfia: [ "PROG" ],
        |  tags: ["Advanced"],
        |  other: [ idverify.ProctoredExam ]
        |},
        |```
        |
        |Values for the columns (defined in `src/main/scala/courses/SWEBOK.scala`) are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- SWEBOK.values yield <.li(<("code")("swebok." + e.productPrefix), " ", <("i")(e.name))
    )
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
    }, <("hr"),
    Common.markdown(
      s"""
        |#### How to edit these tables 
        |
        |In `units.js`, these tables are driven from the contents of the `dsbok` array of each unit.
        |e.g.
        |
        |```js
        |{
        |  code: "FOO123",
        |  name: "Foo and Thingummy Analysis",
        |  prereq: [ choose(1, "COSC210", "COSC220") ],
        |  cbok: [ teamwork(2), communication(3) ],
        |  dsbok: [ ccdsc.AP, ccdsc.ML, edison.ML ],
        |  sfia: [ "DATS" ],
        |  tags: ["Advanced"],
        |  other: [ idverify.ProctoredExam ]
        |},
        |```
        |
        |Values for the columns (defined in `src/main/scala/courses/DSBOK.scala`) are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- CCDSC.values yield <.li(<("code")("ccsdsc." + e.productPrefix), " ", <("i")(e.name)),
      for e <- EdisonDSBOK.values yield <.li(<("code")("edison." + e.productPrefix), " ", <("i")(e.name)),
    )
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
      (s, cat) => s.other.contains(cat)
    }, <("hr"),
    Common.markdown(
      s"""
        |#### How to edit this table 
        |
        |In `units.js`, this table is driven from the contents of the `other` array of each unit.
        |e.g.
        |
        |```js
        |{
        |  code: "COSC310",
        |  name: "Software Project Management",
        |  prereq: [ choose(1, "COSC210", "COSC220") ],
        |  cbok: [ ethics(2), expectations(2), teamwork(2), communication(3), systems(2), governance(3), projectManagement(3) ],
        |  dsbok: [],
        |  sfia: [ "PROG" ],
        |  tags: ["Advanced"],
        |  other: [ idverify.ProctoredExam, idverify.GroupWork, idverify.TurnItIn, idverify.Video ]
        |},
        |```
        |
        |Values for the columns (defined in `src/main/scala/courses/IdentityVerify.scala`) are:
        |
        |""".stripMargin
    ),
    <.ul(
      for e <- IdentityVerification.values yield <.li(<("code")("idverify." + e.productPrefix), " ", <("i")(e.name))
    )
  )
)
