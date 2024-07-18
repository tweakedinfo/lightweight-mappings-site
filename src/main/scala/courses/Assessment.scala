package courses

import cats.{Comonad, Monoid}
import scala.PartialFunction.Combined


enum CalculationElement[T]:
    case score(a:Assessment[T]) 
    case bestOf(n:Int, a:Assessment[T]*)


case class SubjectLearningOutcome(text:String)

/**
  * 
  */
trait Assessment[T] {

    type Work
    type Integrity

    val name:String
    val LOs:Seq[SubjectLearningOutcome] = Seq.empty
    val mustAttempt:Boolean = false
    val mustPass:Boolean = false

    def score(work:Work, integrityData:Integrity):Score[T]

}

enum IntegrityManagement(evidence:IntegritySupport):
    case Proctored extends IntegrityManagement(IntegritySupport.CleanObserverReport)
    case GroupWork extends IntegrityManagement(IntegritySupport.CleanPeerReport)
    case DataTrails extends IntegrityManagement(IntegritySupport.WorkHistory)
    case Video extends IntegrityManagement(IntegritySupport.RecordedPerformance)


enum IntegritySupport:
    case CleanObserverReport
    case CleanPeerReport
    case WorkHistory
    case RecordedPerformance


/**
  * We need a way of combining scores when a calculation function is not specified. 
  * For numeric scores, this tends to be their sum
  * For pass/fail, whether they have all been passed
  * etc
  */
trait CombinationRule[T] extends Monoid[T]

/**
  * 
  */
trait Score[T] {

    def result:T

    def integrityReport: Map[Assessment[?], IntegritySupport]

}


case class AssignmentScore[T : CombinationRule](assessment:Assessment[T], score:T, integrity:IntegritySupport) extends Score[T] {
    def result = score
    def integrityReport: Map[Assessment[?], IntegritySupport] = Map(assessment -> integrity)
}


case class CombinedScore[T : CombinationRule](parts:Score[T]*) extends Score[T] {
    lazy val result = 
        val r = summon[CombinationRule[T]]
        parts.foldLeft(r.empty)((t, s) => r.combine(t, s.result))

    lazy val integrityReport: Map[Assessment[?], IntegritySupport] = 
        parts.foldLeft(Map.empty)((m, s) => m ++ s.integrityReport)
}

class ConvertedScore[B, T](f: Score[T] => B)(from:Score[T]) extends Score[B] {
    lazy val result = f(from)

    export from.integrityReport

}

/** A monoid describing how, by default, to combine scores of this type */ 
val sumScores = new CombinationRule[Double] {
    def combine(a:Double, b:Double) = a + b 

    def empty = 0
}

/**
  * Score is a Comonad, as described in the paper.
  * However, as this is done in Scala / JS rather than as a little language with its own compiler, we're just collecting the 
  * Integrity reports at run-time, rather than being able to annotate the Comonad with the kinds of integrity report that are present.
  */
given Comonad[Score] with {

    def extract[A](x: Score[A]): A = x.result

    def coflatMap[A, B](fa: Score[A])(f: Score[A] => B): Score[B] = ConvertedScore(f)(fa)

    def map[A, B](fa: Score[A])(f: A => B): Score[B] = ConvertedScore((s:Score[A]) => f(s.result))(fa)

}
