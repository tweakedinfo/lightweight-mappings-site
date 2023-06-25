package courses

import com.wbillingsley.veautiful.Unique
import com.wbillingsley.veautiful.html.<
import acssite.Common

val home = <.div(
  Common.markdown(
    """# UNE ACS Accreditation Mappings 2022
      |
      |This site generates mappings and tabulations of CBOK and SFIA requirements for our courses.
      |The navigation in the left sidebar provides tables for each course, as well as a short
      |summary of this information for each unit.
      |
      |The site is generated from a minimal amount of additional information about units and courses.
      |
      |The bespoke data for this site can be found in:
      |* [units.js](units.js)
      |* [courses.js](courses.js)
      |
      |To edit the tables, edit those files and hit refresh.
      |
      |The entries in those files are *intentionally sparse* - we are not aiming to reproduce the
      |course handbook here, only to generate the additional mappings required for ACS.
      |
      |A small amount of duplication of content is necessary - for instance, the code files need
      |the pre-requisites (and course structures) in order to generate the tables.
      |
      |If a unit or a course hasn't loaded, maybe check the console log for errors (F12).
      |Or, we might not have written it into units.js or courses.js yet.
      |""".stripMargin),
)