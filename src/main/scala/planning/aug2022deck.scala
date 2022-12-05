package planning

import com.wbillingsley.veautiful.html._
import com.wbillingsley.veautiful.templates.DeckBuilder
import org.scalajs.dom

import scalajs.js
import scala.util.Random
import acssite.Common
import Common.given

import courses._

def scrollableDiv = <.div(^.attr("style") := "height: 1000px; overflow-y: scroll")

val aug2022deck = DeckBuilder(1920, 1080)
  .markdownSlide("""
  |# Course review & CDF
  |A recap on plans while we wait for ACS
  |""".stripMargin).withClass("center middle")
  .markdownSlides("""
  |### Background 
  |
  |* BComp designed collectively for 2016. Modified gradually up to 2020. 
  |
  |* 2021/2022 was course review & accreditation season (paused updates)
  |
  |* Original design: Two majors, enable a double major + 12cp elective
  |
  |  - Software development (majority of students)
  |  - Data Science (majority of research)
  |
  |  (roughly, data engineering and data science)
  |
  |* Max 60cp 100-level; min 36cp 300-level
  |
  |* "No double counting" rule (shared units only applied to one major)
  |
  |---
  |
  |### Designed for varied cohort. 
  |
  |* Plays at both ends of the spectrum in many dimensons
  |
  |  - Some topics to let students show off in the market but attainable for first-in-family local students.  
  |    (Reputation comes from our best students, but we also have to serve our region)
  |  - Intense collaboration but in a defined subset of units  
  |    (Convince accreditors/employers that groupwork & prof skills covered in an online course, but a lot of students are already in work and don't need extra groupwork)
  |  - Polyglot. Strategically places programming languages. Some "choose your own adventure" units  
  |    (If you have an employer already, you'll want to use your tech stack. We can't tell your employer to prefer a different one. So we have to let you pick one often "enough".)
  |
  |* Informally earmarked placeholders for future growth (big & fast data, cloud/ops, information systems, cybersecurity)
  |
  |---
  |
  |## Georgia Tech effect
  |
  |> If you can get through their course, you'll be able to cope with working here
  |
  |Graduates with a reputation for being indomitable. (But achievable while juggling work and family)
  |
  |Staff with a reputation for ambitious units / fast updates. (But also some reliable, polished, and transferrable units)
  |
  |---
  |
  |## And to thrive, first you've got to stay alive...
  |
  |The pre-2015 team were all cleared out (perhaps having followed past exec decisions to 'raise enrolments' and cut costs too closely, that led to a weakened degree). 
  |So...
  |
  |* Relentless growth and impress our external stakeholders so that can never happen again.
  |
  |* Build momentum rather than rely on too many sugar hits. Public sector plans always underestimate risk and lead time for market growth.  
  |  (Even the successes get considered failures.)
  |
  |* Patience and opportunism. Exec desires usually match up with something on our wish-list, so we keep a lot of plans.
  |
  |---
  |
  |### Relentless growth...
  |
  |CS & ICT unit enrolments
  |
  |<table class="table">
  |<tr><th></th><th>2017</th><th>2018</th><th>2019</th><th>2020</th><th>2021</th><th>2022 T1&T2</th></tr>
  |<tr><td></td><td>1,069</td><td>1,235</td><td>1,527</td><td>1,898</td><td>1,928</td><td>2,041</td></tr>
  |</table>
  |
  |Impress our stakeholders
  |
  |* Twice #1 in Good Universities Guide for computing & ICT undergrad courses
  |* Some features in the course are there to impress IAB, ACS, and employers (rather than only chase student feedback scores)
  |
  |""".stripMargin)
  .veautifulSlide(<.div(
    <.h3("Current structure - 12 unit core; 36cp at 300-level; small majors (30cp); fits double major + 2 electives"),
    scrollableDiv(
      for c <- courses.find(_.code == "BCOMP single major generic") yield PlanPrereqWidget(c.structure)
    )
  ))
  .veautifulSlide(<.div(
    <.h3("Software Development"),
    scrollableDiv(
      for c <- courses.find(_.code == "BCOMP(SD)") yield PlanPrereqWidget(c.structure)
    )
  ))
  .veautifulSlide(<.div(
    <.h3("Data Science"),
    scrollableDiv(
      for c <- courses.find(_.code == "BCOMP(DS)") yield PlanPrereqWidget(c.structure)
    )
  ))
  .markdownSlides("""
  |## Past executive requests
  |
  |* Bachelor of IT. (DIT/BIT/BComp triple).  
  |  
  |  - Added DIT from 2020, but delayed BIT proposal to limit impact on BComp enrolment from transfers.  
  |  (BComp + DIT is up, but BComp flat/down as some students now enrol in DIT first)
  |
  |* Undergraduate Certificate in Computing. Added from 2022.
  |
  |* Half-and-half degrees, e.g. BBus (Analytics and Informatics), agriculture
  |
  |  - *limited impact as there are many "Technology and X" companies, but when looking for tech roles, they hire for tech skills not jack-of-all-trades. Pre-req pathways can be problematic*.
  |
  |* Short courses
  |
  |* International schemes (IEG Malaysia, China)
  |
  |* Cybersecurity, IoT, short courses
  |
  |* More programming languages (Dean, 2020). Other tech requests in Student Experience Survey.
  |
  |---
  |
  |## Currently active executive requests
  |
  |* Cybersecurity
  |
  |* Double degrees, CDF 4 year structure - note the issue with the size of our core major
  |
  |(plus some others, and the previous ones will cycle back around in time)
  |
  |---
  |
  |## Student Experience Survey
  |
  |QILT data, loaded onto UNE BI dashboard, only accessible to course coordinator (sorry!)
  |
  |Contains some prose responses. Selected responses on next few slides.
  |
  |---
  |
  |### Best aspects - technical depth & variety
  |
  |> “The course is very technical and by the end of the semester when I look back I appreciate a significant increase in my technical proficiency. The most important aspect is that the degree is entirely online and allows me to learn by distance. It's clear that UNE take their responsibility to provide good distance learning infrastructure seriously
  |
  |> "The variety and range of topics and skills taught across the degree."
  |
  |> "The assignments for most units have been extremely useful for learning the skills needed to work in a software development environment."
  |
  |---
  |
  |### Best aspeccts - Welcoming and approachable environment 
  |
  |> “Finally being able to learn what I've always wanted to do. Feeling very welcomed and very much enjoying the course.”
  |
  |> “Friendly and approachable lecturers teaching the subjects providing an intuitive and hands on learning experience.”​
  |
  |> “I really feel they WANT me to learn, understand and pass.”​
  |
  |> "I enjoyed cosc110 as the lecturer was great and responded quickly to all my emails."
  |
  |---
  |
  |### Best aspects - flexibility
  |
  |> “Able to submit work early to account for being away for a week.”
  |
  |> “Learning a broad range of topics throughout the degree. Being placed in a group of students that have industry experience for the final group project (COSC320) has really helped me to build my confidence in real world IT work.”
  |
  |> “Lecturers have fantastic, units have been interesting, flexible study times to suit my schedule.”
  |
  |> “Well spaced out for online learning.”
  |
  |---
  |
  |### Best aspects - some back-handed compliments
  |
  |> “Some of the assignments have been good, but honestly, nothing really comes to mind.”
  |
  |> “…I did struggle at first. However, once I realised that rote learning played a big part in examinations, I've found the degree was attainable…”
  |
  |---
  |
  |### Needs improvement - logistical / resourcing
  |
  |> “Video quality, mainly sound for some lectures etc.”
  |
  |> “All the resources ... Seem to be all over the place, and different subjects are set up differently.”
  |
  |> "Lack of feedback on assignments. Feedback I have received so far has been a bit brief, more suggestions for improvement would be helpful.""
  |
  |> “Teachers 1 on 1 interaction.”
  |
  |> “Online testing not flexible.”​
  |
  |---
  |
  |### Needs improvement - keeping student dev environment cutting edge
  |
  |> "Online exams."
  |
  |> "Practice sessions Real world applications more industry tools to be implemented."
  |
  |> “I am fed up with turing, bash and other outdated/slow/clunky programs and methods. There are much better ways of doing things these days...”​
  |
  |---
  |
  |### Needs improbement - workload & flexibility
  |
  |> “Maybe one less assignment towards the end of the period, the study load is tough in the end."
  |
  |> "Less assignments/deadlines and more freedom in being able to explore the material self paced."
  |
  |> "Less assignments/deadlines and more freedom in being able to explore the material self paced."
  |
  |> "There are no trimester 3 classes beyond STAT100 and COSC110."
  |
  |> "Better structure in units, very steep learning curve."
  |
  |> "The order units are studied really matters. So this means your start date does too. In hindsight I wish I did the easier primer units first..."
  |
  |---
  |
  |### Needs improvement - breadth / variety / depth
  |
  |> “More system administration subjects"
  |
  |> "In some courses, lectures and teaching material seemed unimaginative and unnecessarily arduous. this sometimes appeared to be a result of some requirement that the uni use a certain textbook."
  |
  |> "I would personally have preferred more information on the basic hardware involved."
  |
  |> "First year assignments/classes can be slightly dull for people with experience in computer science & programming"
  |
  |> "The relevancy to real world applications and preparation for the industry."​
  |
  |---
  |
  |## Course Design Framework
  | 
  |Originally a project of Jonathan Powells, [doc](https://policies.une.edu.au/download.php?id=512&version=2&associated)
  |
  |In policy, "Relevant considerations include, but are not limited to" (CDF, etc). Not strictly enforced, but steady movement towards it.
  |
  |* "Majors will comprise 48cp"
  |
  |  - Single degree: "core major" 48cp + major 48cp + 48cp elective = 144cp (3y)
  |  - Double degrees: degree A "core major" 48cp + degree A major 48cp + degree B "core major" + degree B major = 192cp (4y)
  |
  |* "Minors will comprise 24cp"
  |
  |* No units will be offered that are not part of majors or minors. There will be no “orphan” or elective-only units. 
  |
  |  - However, optional units within a major **are** allowed. "List no more than 72cp as available in any one major"
  |   (i.e. choose 7 from 12)
  |
  |  - eventually, students take 2-4 "foundation" units, one of which is a "threshold unit" 
  |  
  |  - and take "4-6" advanced units per major. Note CDF lacks a "200"-level...
  |
  |---
  |
  |### CDF vs AQF
  |
  |Some elements of CDF conflict with ACS. e.g.:
  |
  |> "Undergraduate units will be at two levels: Foundation and Advanced. ... Advanced units correspond to AQF levels 6-7,
  |current UNE “level 200” and “level 300” units. Currently, it is not required for students to complete a 
  |quantum of level 200 units before enrolling in level 300, so there is little justification for the existence
  |of the two separate advanced levels."
  |
  |TEQSA & policy requires 6 units at 300-level; CDF seems to try to push 200-level up to 300-level
  |
  |But ACS requires 
  |
  |> The ICT learning required to meet the professional roles identified for the program will progress
  |through at least 3 levels
  |
  |* Requires 4+ "advanced units", no more than 2 of which can be the capstone
  |
  |* Defines an "advanced" unit as one that has a pre-req that itself has pre-reqs. 
  |
  |---
  |
  |### Comparison elsewhere
  |
  |* ALTA survey 2020, Though our core is large to UNE, it's fairly small for a CBoK core in Australia.
  |
  |* UQ's new (2021) course design for BCompSc and BInfTech is interesting for meeting CDF requirements on core + major size:
  |
  |  - 8 subjects in the core
  |
  |  - capstone is in the majors **even though it's the same capstone!**
  |
  |  - (effectively giving a 9 subject core and 7 subject majors while meeting CDF-like requirements)
  |
  |> For students undertaking the BCompSc Major in Cyber Security - DECO3801 must be substituted in the BCompSc Major in Data Science, BCompSc Major in Machine Learning, or BCompSc Major in Scientific Computing by a course from BCompSc Program Elective Courses.
  |
  |* https://my.uq.edu.au/programs-courses/requirements/program/2451/2023  BCompSc 
  |* https://my.uq.edu.au/programs-courses/requirements/program/2453/2023  BInfTech 
  |
  |---
  |
  |### CDF other aspects
  |
  |* "Limit prerequisites ... to cases in which completion of the prerequisite is essential for completion of the later unit"
  |  (i.e. prefer advisory statements of assumed knowledge)
  |
  |* "Schedule at least one Foundation and one Advanced unit every trimester"
  |
  |* Proposes 3cp "half units" for micro-credentials. Perpetually delayed by T3 calendar decision.
  |
  |* Accelerated progression from Bachelor to Masters
  |
  |* Specialist vs breadth minors
  |
  |* "Nested" structure. i.e. exit pathways via UCCOMP, DIT and Adv Dip (currently Adv Dip Sc)
  |
  |---
  |
  |### Course Reviews
  |
  |Part of TEQSA governance process. External reviews. CS cognate courses reviewed in Q3 2021.
  |
  |1. EQD provides us with a "rubric" including QILT data (June 2021)
  |
  |2. We first proposed our response (August 2021)
  |
  |3. That goes out to external review & he reviewer provides their report (November 2021)
  |
  |In 2020, CDF had caused a problemts for mathematics when Environmental & Rural Science dropped 100-level mathematics units because of CDF.
  |
  |CDF progress was a specific question in the course review rubric, so we put a light description of a proposal out to the reviewer.
  |
  |---
  |
  |### Aside: Some informal observations from the course coordinator...
  |
  |* There is an open ended set of potential majors and minors we might want to introduce. Cybersecurity, AI, Ops, Information Systems, ... 
  |
  |* We do need 3 levels of units for ACS. 
  |
  |* Core needs to cover CBoK. CDF asks our "core major" to be reduced to 48cp (from current 72cp). 
  |  Likely to put the squeeze on units in core that aren't hitting CBoK categories. UQ BCompSc does not include calculus in core.
  |
  |* Inevitable tension between CS students who are "industry-inclined" vs "theoretically-inclined" with regards to mathematics
  |  https://dl.acm.org/doi/pdf/10.1145/3287324.3287416 
  |
  |* Should we support this distinction and more "industry-inclined" majors such as Information Systems within the BComp or do we need a BIT? 
  |  (Personal worry about splitting load. UQ's BCompSc 2021+ does not include calculus in core.)  
  |
  |* A 48cp core + 48cp major + 48cp second major would leave no space for any non-ICT electives (down from 12cp), making it a barrier for students with any non-ICT advanced standing
  |
  |* But, the "no double counting" rule does not actually appear to be anywhere in policy now. (Might just be a Callista limitation.)
  |
  |* I don't like that we say that students have a choice between MTHS130 and STAT100 in core, but the pre-reqs force all the Data Science students to choose STAT100. (Putting STAT100 in the major would be clearer.)
  |
  |---
  |
  |### Rough idea if we wanted to keep changes smaller
  |
  |- Move some units from core into majors, keeping core focused on CBoK so we can have majors in any area
  |
  |- Majors can be bigger. At "72cp" of choice, we even have room to add units. Computer vision? Ops? Embedded? etc
  |
  |- Allow double counting of units in both majors (replacement of overlap with elective), to free up elective space for dual majors. 
  |
  |- Add a computational mathematics minor / major, to make sure maths is still visible to our students
  |
  |  - Amir Karton keen to teach an HPC unit
  |
  |---
  |
  |### Bugs we might not have capacity to fix
  |
  |- COSC101 and COSC102 are only offered once. (Retain in core, to avoid having to repeat both. Fudge the major size slightly? Or allow choice of each inside major?)
  |
  |- We have no "Data Science Studio 2". Though we could re-name Software Development Studio 2. git, containers, build tools, cloud, etc, apply to more than just software dev.
  |
  |- AMTH140 is only offered once. 
  |
  |- We have 4 academics who do AI/ML work on computer vision and only two AI units.
  |
  |
  |""".stripMargin)
  .veautifulSlide(<.div(
    <.h3("Half-way house: 60cp core / 48 cp major + minors; overlap majors and allow double-counting to enable double major"),
    scrollableDiv(
      for c <- courses.find(_.code == "BCOMP(CDF60cp)") yield PlanPrereqWidget(c.structure)
    )
  ))
  .markdownSlides("""
  |## Niggles getting to 48cp core
  |
  |Moving other units into majors creates some hiccups:
  |
  |* Moving COSC101, COSC102 into majors slows progression
  |* Moving COSC120 breaks OO prereq for COSC230, COSC220
  |* Moving COSC220 would require is inclusion or duplication in the other majors 
  |  (it hits a lot of CBoK categories & a lot of material, e.g. git, build tools, docker containers, debugging & profiling, service management, security of dependencies, is hard to avoid)
  |
  |Possible hack: Use UQ's trick and put a unit all majors
  |
  |Edit: Peter's suggestion of putting COSC230 Data Structures & Algorithms (C++) into the software development major makes things
  |fit much more easily. 
  |
  |This would also allow us to specialise the cybersecurity or ethics materials if it became part of a unit in each major. 
  |(If the MCyber is going to introduce so many new units on this as it seems.)
  |
  |Another slight possibility (not included):
  |
  |* We do have students who say they've already done some programming and are less keen on COSC110. We could "or" it with the
  |ICT units from the DIT, allowing those students a choice between Python, JS, and ARM assembly/digital electronics? 
  |That would let us duck the CDF rule of having to offer COSC110 three times per year 
  |(in which the third offering always has high attrition because it corrals all the students who've already failed it, but all the students who've passed it can't take it again)
  |
  |""".stripMargin)
  .veautifulSlide(<.div(
    <.h3("CDF: 48cp core; require 2+ ACS-advanced from major; require remainder of 300-level from elective;"),
    <.div("Note: We could rename Software Development Studio 2 - probably just to 'Computing Studio 2' - and adjust the collaborative project to include more explicit DS and Cyber aspects"),
    scrollableDiv(
      for c <- courses.find(_.code == "BCOMP(CDFstrict)") yield PlanPrereqWidget(c.structure)
    )
  ))
  .markdownSlides("""
  |
  |### Commendations
  |
  |1. Emphasis on personalisations through mutliple entry/exit pathways
  |
  |2. Extent of curriculum mapping
  |
  |3. Success with external accreditation
  |
  |4. Industry Advisory Board "offers an exemplary template for other areas to engage with industry and provide job-ready graduates."
  |
  |5. Student-centred approach to curriculum review (in our submission)
  |
  |6. Student experience in S&T is largely and consistently positive on measures from the (QILT) Student Experience Survey
  |
  |---
  |
  |### Affirmations 
  |
  |* Re-map the course against CBoK. Fully supported. Done in ACS submission.
  |
  |* Investigate expanding the size of majors, and introduce a rule permitting students to count units that are common to the Data Science and Software
  |Development majors towards both majors. Considered beyond the reviewer's scope.
  |
  |* Consider moving mathematics units that are required for majors into those majors. (partial)
  |
  |* Consider the possibility of potential new majors (e.g. information systems or cybersecurity) in the re-mapping of the core units and the mathematics requirement.
  |
  |* Consider introducing a computational mathematics minor
  |
  |* Remove the long-list of out-of-discipline “listed units” from the majors.
  |
  |* Consider bringing some material forward to first year based on students being better prepared.
  |
  |---
  |
  |### Recommendations - for us
  |
  |* #3 risk-benefit analysis, to contrast the requirements of CDF and ACS
  |
  |* #4 establish internal Course Advisory Board, distinct from IAB (from Jan 2023)
  |
  |* #5 analyse where specific units (e.g. mathematics) cause attrition or progression issues annually (at CAB)
  |
  |* #6 a recommendation that *other* courses should adopt "emergent and successful interventions noted in the BCOMP and MIT" re employability
  |
  |""".stripMargin)
  .markdownSlide(Common.willCcBy).withClass("bottom")
  .renderSlides