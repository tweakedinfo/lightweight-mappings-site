/**
 * This contains the Markdown text for the homepage. 
 * 
 * It's done as a js file rather than raw md so that it will still work if loaded from a file:// url
 * (If index.html is loaded from file:// then Chrome will regard the origin as null and dom.fetch would
 * refuse to access .md file content. Instead, we load it as a script from a script tag)
 */

let text = `
## Lightweight mappings for CBoK, SWEBOK, DSBOK, and Identity Verification

This site generates mappings and tabulations of various mappings for our courses. The rationale is described in 

> Billingsley, W. (2022). Lightweight Mapping of Identify Verification Methods and Secondary Course Aspects: “Swiss Cheese” Modelling.
> In S. Wilson, N. Arthars, D.Wardak, P. Yeoman, E. Kalman, & D.Y.T. Liu (Eds.), Reconnecting relationships through technology. 
> *Proceedings of the 39thInternational Conference on Innovation, Practice and Research in the Use of Educational Technologies in Tertiary Education, ASCILITE 2022 in Sydney*: 
> e22199. [https://doi.org/10.14742/apubs.2022.199](https://doi.org/10.14742/apubs.2022.199)

The navigation in the left sidebar provides tables for each course, as well as a short
summary of this information for each unit.

## Editing courses, units, and this page

The site is generated from a minimal amount of additional information about units and courses.
The text of this homepage is set in

* [home.js](home.js)

The bespoke data for this site can be found in:
* [units.js](units.js)
* [courses.js](courses.js)

To edit the tables, edit those files and hit refresh.

The entries in those files are *intentionally sparse* - we are not aiming to reproduce the
course handbook here, only to generate the additional mappings required for ACS.

A small amount of duplication of content is necessary - for instance, the code files need
the pre-requisites (and course structures) in order to generate the tables.

If a unit or a course hasn't loaded, maybe check the console log for errors (F12).
Or, we might not have written it into units.js or courses.js yet.

## If you have just cloned this repository

* The <code>main</code> branch contains the source-code that would need compiling to show.
* The <code>gh-pages</code> branch contains the published site, including the compiled java script

i.e. if you're not planning on editing the Scala definitions, but just write units, courses, or the homepage, 
you might want to <code>git switch gh-pages</code>. That should give you a version with all the Scala code 
pre-compiled. 
(Unit definitions, course definitions, and the homepage are set using plain JavaScript. 
Embeddable slide decks require Scala.)

## Editing the sourcecode

The full source code of the mappings is also contained in this repository.
It is a Scala.js project, using [Veautiful / Doctacular](https://www.wbillingsley.com/veautiful) for rendering.
This makes it quite short to edit and simple to build.

To build the code on your own computer:

* Install [sbt](https://scala-sbt.org)
* <code>sbt fastDeployScript</code>

To build a publishable version with more compact JavaScript

* Install [sbt](https://scala-sbt.org) and npm
* <code>sbt deployScript</code>

To publish to GitHub:

* A workflow for GitHub pages is already in the repository, in <code>.github</code>.
* Push the code to a new repository on GitHub, then go into the repository settings and select to publish to GitHub Pages from the <code>gh-pages</code> branch

To open in a Development Container, if you like using Visual Studio Code - Dev Containers plugin

* The <code>.devcontainer</code> directory contains config and a Dockerfile so that VS Code can open this as a repositoty
* This installs sbt in your container, so it should already be present
* If you want to add Scala syntax highlighting, add a plugin for it. You can also add the Metals plugin for smarter IDE features
  (but I've kept the configuration minimal to avoid complexity)


## Adding slide decks to your site

Mappings tables can be embedded into Doctacular slide decks. See the examples in the <code>src/main/scala/planning</code> directory.

To add or remove these from the Site navigation, edit <code>site.Toc</code> in <code>src/main/scala/acssite.Site.scala</code> and either recompile
(or if you're using GitHub Pages, just commit and push and the included GitHub Action will do the recompiling and republication for you)
`

setHomePage(text)