# Lightweight mappings for CS Courses

This site generates some of our mappings.
It's intended to be easy(ish) to play around with.

The rationale is described in 

> Billingsley, W. (2022). Lightweight Mapping of Identify Verification Methods and Secondary Course Aspects: “Swiss Cheese” Modelling.
> In S. Wilson, N. Arthars, D.Wardak, P. Yeoman, E. Kalman, & D.Y.T. Liu (Eds.), Reconnecting relationships through technology. 
> *Proceedings of the 39thInternational Conference on Innovation, Practice and Research in the Use of Educational Technologies in Tertiary Education, ASCILITE 2022 in Sydney*: 
> e22199. [https://doi.org/10.14742/apubs.2022.199](https://doi.org/10.14742/apubs.2022.199)

Course and unit definitions (and the homepage) are defined simply in JS. Just edit and reload.
It's only if you want to write slide-decks (or do fancier stuff) you'd need Scala.

To grab a copy and view it on your computer:

1. Clone the repository
2. Switch to the `gh-pages` branch so you have a version with pre-compiled JavaScript
2. Open index.html in a browser

To alter the home page, course mappings or units:

1. Edit home.js, units.js, or course.js
2. Hit refresh in your browser

To edit the source code or write slide-decks, you'll need [sbt](https://scala-sbt.org)

1. Switch to the `main` branch (and bring any changes you've made to the js files with you)
2. Edit the Scala
3. `sbt fastDeployScript` to recompile the JavaScript so you can view it on your own computer.
4. Push the site to GitHub to publish it. An included Action will compile the site up on 


## units.js

It's easiest to use the examples I've already created to understand the format.

Units are added in the array in the `addUnits` call.
You can individually `addUnit` a single unit if you want though.

A unit looks like:

```js
{
  code: "XYZ321",
  name: "Defining crazy units",
  prereq: [ "COSC220", "COSC310", cp(96), or("XYZ101", "XYZ102"), choose(2, "XYZ200", "XYZ201", "XYZ202") ],
  cbok: [ problemSolving(3), ethics(2), expectations(2), teamwork(3), communication(3), systems(3), projectManagement(3) ],
  sfia: []
},
```

* `prereq` is an array of requirements.   
  - Some of them are just strings (codes of other units)
  - Some are function calls, eg `cp(int)`, `or(a, b)`, `choose(int, code, code, code...)`
* CBOK has entries that are given levels. Each entry in the array looks like a function call. 
  The CBOK categories are defined in CBOK.scala. Currently, they are:
  - ethics
  - expectations
  - teamwork
  - communication
  - societal
  - understanding
  - problemSolving

  - fundamentals
  - data
  - networking

  - humanFactors
  - programming
  - systems

  - governance
  - projectManagement
  - serviceManagement
  - cybersecurity

* SFIA is still to come, just leave that empty.

*A handy hint*:

Unit codes are just text. I commonly define "units" called things like "Elective" or "300-level Elective" to produce diagrams in courses
showing where electives can be taken.

## courses.js

Again, it's probably better to start with the examples.

Courses are added within the `addCourses` call (or an `addCourse` call)

```scala
{
  code: "BCOMP",
  name: "Bachelor of Computer Science (Software Development)",
  structure: [
    {
      name: "Core",
      units: [
        "AMTH140", "MTHS120", or("MTHS130", "STAT100"),
        "COSC110",
        or("COSC101", "COSC102"), "COSC120",
        "COSC210", "COSC220", "COSC230", "COSC240",
        "COSC310", "COSC320"
      ]
    },

    {
      name: "Software Development major",
      units: [
        choose(5, "COSC250", "COSC260", "COSC330", "COSC340", "COSC350", "COSC360", "COSC370")
      ]
    }
  ],
  plans: {
    "Full-time, T1 start": [
      { name: "Y1 Trimester 1", units: [ "COSC101", "COSC110", "MTHS120", or("ICT101", "Elective") ] },
      { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC102", "COSC120", or("STAT100", "MTHS130") ] },
      { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", or("COSC250", "Elective"), "Elective" ] },
      { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", or("COSC260", "Elective"), "Elective" ] },
      { name: "Y3 Trimester 1", units: [ "COSC310", choose(3, "COSC340", "COSC370", "Elective", "Elective") ] },
      { name: "Y3 Trimester 2", units: [ "COSC320", choose(3, "COSC330", "COSC350", "COSC360", "Elective") ] },
    ]
  }
}
```

* `structure` is the course structure and controls what appears in both the structure and the CBOK grid
* `plans` are named sets of course plans
* In any of these, the list of units is the same format as prerequisites. They are rules to be met.
* The plans grid will pull out the list of pre-reqs and draw arrows. 
* Still to come: 
  - colour-coding on whether a pre-req is mandatory or part of a choice (required vs assumed)
  - altering the style of optional units so it's more obvious in the CBOK table which are mandatory
  - SFIA tables
  - Probably an "in-depth" column

*A handy hint*:

Some university handbooks (e.g. UNE's) ignore parts of the URL that are in parentheses. E.g. ..../BCOMP(SD) will link to the ..../BCOMP page.
That can be useful for showing different diagrams for different majors in the same degree but still having a link to handbook.

## home.js

For consistency, the homepage text is also set using a loaded JS file. It's very self-explanatory, so open it and take a look.