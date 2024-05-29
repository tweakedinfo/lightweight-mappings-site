package courses

import cats.{Comonad, Monoid}
import scala.PartialFunction.Combined


/**
  * A simplified token of integrity evidence that an assessment would receive in marking.
  */
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


case class AssignmentScore[T](assessment:Assessment[T], score:T, integrity:IntegritySupport) extends Score[T] {
    def result = score
    def integrityReport: Map[Assessment[?], IntegritySupport] = Map(assessment -> integrity)
}


/**
  * Represents a score converted from one format to another. E.g. from a subject mark to a grade
  *
  * @param f
  * @param from
  */
class ConvertedScore[B, T](f: Score[T] => B)(from:Score[T]) extends Score[B] {
    lazy val result = f(from)

    export from.integrityReport

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

/**
  * Combines scores into an aggregated result using a combination rule
  *
  * @param parts
  */
case class CombinedScore[T : CombinationRule](parts:Score[T]*) extends Score[T] {
    lazy val result = 
        val r = summon[CombinationRule[T]]
        parts.foldLeft(r.empty)((t, s) => r.combine(t, s.result))

    lazy val integrityReport: Map[Assessment[?], IntegritySupport] = 
        parts.foldLeft(Map.empty)((m, s) => m ++ s.integrityReport)
}


/** A monoid describing how, by default, to combine scores of this type */ 
val sumScores = new CombinationRule[Double] {
    def combine(a:Double, b:Double) = a + b 

    def empty = 0
}


/* -- 
   Static view
 */


 /**
   * Represents an integrity strategy and the kind of evidence that it produces
   *
   * @param evidence
   */
enum IntegrityManagement(evidence:IntegritySupport):
    case Proctored extends IntegrityManagement(IntegritySupport.CleanObserverReport)
    case GroupWork extends IntegrityManagement(IntegritySupport.CleanPeerReport)
    case DataTrails extends IntegrityManagement(IntegritySupport.WorkHistory)
    case Video extends IntegrityManagement(IntegritySupport.RecordedPerformance)


/**
  * How much evidence of authenticity is produced involves two perspectives.
  * From a positive perspective, we are interested in seeing every place where a student might produce evidence of learning
  * From a negative perspective, we are interested in the cases where they will definitely produce evidence of learning
  * 
  * A text parameter is included for the report
  */
enum IntegrityAssurance:
    case MayProduce(i:IntegrityManagement, text:String)
    case WillRequire(i:IntegrityManagement, text:String)


trait GradeCalculation[T] {

    def children:Seq[GradeCalculation[?]]

    def IntegrityAssurance:Seq[IntegrityAssurance]


}


case class SubjectLearningOutcome(text:String)

/**
  * 
  */
trait Assessment[T] extends GradeCalculation[T] {

    type Work = Unit // A placeholder, as we do not actually pass work into the model

    val name:String
    val LOs:Seq[SubjectLearningOutcome] = Seq.empty
    val mustAttempt:Boolean = false

    
    val mustPass:Boolean = false

    def integrityAssurance:Seq[IntegrityAssurance]

    override val children = Seq.empty

    /** 
     * The action of marking work involves both the work and the data about its evidence of authenticity.
     * We leave this unimplemented, as we are modelling the assessment system, rather than implementing an automarker.
     */
    def grade(work:Work, integrityData:IntegritySupport):Score[T] = 
        ???

}


