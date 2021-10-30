package courses
import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.{<, ^}

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

type PrereqElement = String | ComplexPrereqElement
enum ComplexPrereqElement:
  @JSExportTopLevel("or")
  case or(a:String, b:String)

  @JSExportTopLevel("choose")
  case choose(i:Int, els:String*)

  @JSExportTopLevel("cp")
  case cp(i:Int)

def subjCodes(el:PrereqElement):Seq[String] = el match
  case s:String => Seq(s)
  case ComplexPrereqElement.or(a, b) => Seq(a, b)
  case ComplexPrereqElement.choose(_, units:_*) => units
  case ComplexPrereqElement.cp(_) => Seq.empty

// Note - we can't call this Unit because Unit is a Scala core type!
case class Subject(
 code:String,
 name:String,
 handbookUrl:String,
 cbok: Seq[(CBOK, Int)],
 sfia: Seq[String],
 prereq: Seq[PrereqElement]
) {
  def cbokLevel(cat:CBOK):Int =
    cbok.find(_._1 == cat).map(_._2).getOrElse(0)
}

val subjects = mutable.Buffer.empty[Subject]

@JSExportTopLevel("addUnit")
def addUnit(config:js.Dynamic) = {
  try {
    import js.JSConverters._

    val s = Subject(
      code = config.code.asInstanceOf[String],
      name = config.name.asInstanceOf[String],
      handbookUrl = "",
      cbok = config.cbok.asInstanceOf[js.Array[(CBOK, Int)]].toSeq,
      sfia = config.sfia.asInstanceOf[js.Array[String]].toSeq,
      prereq = config.prereq.asInstanceOf[js.Array[PrereqElement]].toSeq
    )

    subjects.append(s)
  } catch {
    case _ => // TODO: something with the errors
  }
}

@JSExportTopLevel("addUnits")
def addUnits(units:js.Array[js.Dynamic]) = {
  for u <- units do addUnit(u)
}


def unitPage(u:Subject) = Unique(<.div(
  <.h1(u.code, " ", u.name),
  <.p(
    <.a(^.href := s"https://handbook.une.edu.au/units/2022/${u.code}?year=2022", "Link to handbook entry")
  ),
  <.h3("CBOK Categories"),
  <.table(
    <.tr(<.th("Category"), <.th("Level")),
    for (cbok, l) <- u.cbok yield
      <.tr(<.td(cbok.name), <.td(l.toString))
  )

))

// Composes a name like ICT100 Computational Thinking from a unit code
// If there's not a unit with that code in the subjects list, leaves the string unchanged
// so Elective stays as Elective
def unitText(code:String) =
  subjects.find(_.code == code).map(s => s"$code ${s.name}").getOrElse(code)

