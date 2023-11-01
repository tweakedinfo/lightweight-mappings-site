"use strict";

console.log("Loading courses")

// Sets a function that can link to the Handbook for a course. 
// If this isn't set, the "Link to handbook" will not be shown on course pages
setHandbookUrl((code) => `https://handbook.une.edu.au/courses/2022/${code}?year=2022`)

addCourses([
  {
    code: "BCOMP2024CS",
    name: "Bachelor of Computer Science (Cybersecurity) 2024",
    structure: [

      {
        name: "Core CS",
        units: [
          "AMTH140", "COSC110", "COSC120", "COSC130",
          "COSC210", "COSC220", "COSC230", "COSC240",
          "COSC310", "COSC320"
        ]
      },
      {
        name: "Cybersecurity major",
        units: [
          "COSC102", "MTHS120",
          choose(1, "MTHS130", "PMTH338", "STAT100"),
          choose(2, "COSC340", "COSC350", "COSC372"),
          choose(2, "COSC481", "COSC482", "COSC483", "COSC484")
        ]
      },
    ],
    plans: { 
      "Full-time, T1 start, Cybersecurity Major": [
          { name: "Y1 Trimester 1", units: [ "COSC110", "COSC130", "Elective", "Elective" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC120", "COSC102", "MTHS120" ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "Elective", "Elective" ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", choose(1, "MTHS130", "STAT100", "PMTH338"), choose(1, "COSC350", "COSC372") ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", choose(1, "COSC340", "Elective*"), choose(2, "COSC481", "COSC482", "Elective**", "Elective**") ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", choose(1, "COSC350", "COSC372", "Elective*"), choose(2, "COSC483", "COSC484", "Elective**", "Elective**") ] },
          // Can only do one Elective*
          // Can only do two Elective**
      ]
    }
  },

  {
    code: "BCOMP2024SD",
    name: "Bachelor of Computer Science (Software Development) 2024",
    structure: [

      {
        name: "Core CS",
        units: [
          "AMTH140", "COSC110", "COSC120", "COSC130",
          "COSC210", "COSC220", "COSC230", "COSC240",
          "COSC310", "COSC320"
        ]
      },
      {
        name: "Software Development major",
        units: [
          choose(1, "MTHS110", "MTHS120", "STAT100"),
          choose(4, "COSC330", "COSC340", "COSC350", "COSC360", "COSC370"),
          choose(1, "COSC101", "COSC250", "COSC260", "COSC330", "COSC340", "COSC350", "COSC360", "COSC370", "COSC484")
        ]
      },
    ],
    plans: { 
      "Full-time, T1 start, Software Development Major": [
          { name: "Y1 Trimester 1", units: [ "COSC110", "COSC130", "Elective", "Elective" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC120", choose(1, "MTHS110", "MTHS120", "STAT100"), "Elective" ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", choose(2, "COSC101", "COSC250", "Elective**", "Elective**") ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", choose(1, "COSC260", "Elective**"), choose(1, "COSC350", "Elective*") ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", choose(2, "COSC340", "COSC370", "Elective*"), "Elective" ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", choose(2, "COSC330", "COSC360", "Elective*"), choose(1, "COSC484", "Elective**") ] },
          // Can do at most one of the Elective* units
          // Can do at most three of the Elective* or Elective** units
      ]
    }
  },

  {
    code: "BCOMP2024DS",
    name: "Bachelor of Computer Science (Data Science) 2024",
    structure: [

      {
        name: "Core CS",
        units: [
          "AMTH140", "COSC110", "COSC120", "COSC130",
          "COSC210", "COSC220", "COSC230", "COSC240",
          "COSC310", "COSC320"
        ]
      },
      {
        name: "Data Science major",
        units: [
          "MTHS120", "STAT100", "STAT210", 
          choose(1, "COSC102", "MTHS130"),
          choose(3, "COSC330", "COSC350", "COSC380", "STAT320", "STAT330")
        ]
      },
    ],
    plans: { 
      "Full-time, T1 start, Data Science Major": [
          { name: "Y1 Trimester 1", units: [ "COSC110", "COSC130", "Elective", "Elective" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC120", "MTHS120", "STAT100" ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "STAT210", "Elective" ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", choose(1, "COSC102", "MTHS130"), "Elective" ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", choose(1, "STAT330", "Elective*"), "Elective", "Elective" ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", choose(3, "COSC330", "COSC350", "COSC380", "STAT320", "Elective*") ] },
          // Can do at most one of the Elective* units
      ]
    }
  },

    {
    code: "BCOMP2024CSSD",
    name: "Bachelor of Computer Science (Cybersecurity and Software Development) 2024",
    structure: [

      {
        name: "Core CS",
        units: [
          "AMTH140", "COSC110", "COSC120", "COSC130",
          "COSC210", "COSC220", "COSC230", "COSC240",
          "COSC310", "COSC320"
        ]
      },
      {
        name: "Cybersecurity major",
        units: [
          "COSC102", "MTHS120",
          choose(1, "MTHS130", "PMTH338", "STAT100"),
          choose(2, "COSC340", "COSC350", "COSC372"),
          choose(2, "COSC481", "COSC482", "COSC483", "COSC484")
        ]
      },
      {
        name: "Software Development major",
        units: [
          choose(1, "MTHS110", "MTHS120", "STAT100"),
          choose(4, "COSC330", "COSC340", "COSC350", "COSC360", "COSC370"),
          choose(1, "COSC101", "COSC250", "COSC260", "COSC330", "COSC340", "COSC350", "COSC360", "COSC370", "COSC484")
        ]
      },
    ],
    plans: { 
      "Full-time, T1 start, Cybersecurity and Software Development Majors": [
          { name: "Y1 Trimester 1", units: [ "COSC110", "COSC130", "COSC101", "Elective" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC120", "COSC102", "MTHS120" ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "Elective", "Elective" ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", "COSC350", choose(1, "MTHS130", "STAT100", "PMTH338") ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", "COSC340", "COSC370", choose(1, "COSC481", "COSC482", "Elective**") ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", "COSC330", choose(2, "COSC483", "COSC484", "Elective**", "Elective**") ] },
          // Can only do two Elective**
      ]
    }
    },

    {
    code: "BCOMP2024CSDS",
    name: "Bachelor of Computer Science (Cybersecurity and Data Science) 2024",
    structure: [

      {
        name: "Core CS",
        units: [
          "AMTH140", "COSC110", "COSC120", "COSC130",
          "COSC210", "COSC220", "COSC230", "COSC240",
          "COSC310", "COSC320"
        ]
      },
      {
        name: "Cybersecurity major",
        units: [
          "COSC102", "MTHS120",
          choose(1, "MTHS130", "PMTH338", "STAT100"),
          choose(2, "COSC340", "COSC350", "COSC372"),
          choose(2, "COSC481", "COSC482", "COSC483", "COSC484")
        ]
      },
      {
        name: "Data Science major",
        units: [
          "MTHS120", "STAT100", "STAT210", 
          choose(1, "COSC102", "MTHS130"),
          choose(3, "COSC330", "COSC350", "COSC380", "STAT320", "STAT330")
        ]
      },

    ],
    plans: { 
      "Full-time, T1 start, Cybersecurity and Data Science Major": [
          { name: "Y1 Trimester 1", units: [ "COSC110", "COSC130", "MTHS120", "Elective" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC120", "COSC102", "STAT100" ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "STAT210", "STAT330" ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", "COSC350", choose(1, "STAT320", "COSC380") ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", choose(1, "COSC340", "Elective*"), choose(2, "COSC481", "COSC482", "Elective**", "Elective**") ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", choose(1, "COSC372", "Elective*"), choose(2, "COSC483", "COSC484", "Elective**", "Elective**") ] },
          // Can only do one Elective*
          // Can only do two Elective**
      ]
    }
    },
  {
    code: "BCOMP2024SDDS",
    name: "Bachelor of Computer Science (Software Development and Data Science) 2024",
    structure: [

      {
        name: "Core CS",
        units: [
          "AMTH140", "COSC110", "COSC120", "COSC130",
          "COSC210", "COSC220", "COSC230", "COSC240",
          "COSC310", "COSC320"
        ]
      },
      {
        name: "Software Development major",
        units: [
          choose(1, "MTHS110", "MTHS120", "STAT100"),
          choose(4, "COSC330", "COSC340", "COSC350", "COSC360", "COSC370"),
          choose(1, "COSC101", "COSC250", "COSC260", "COSC330", "COSC340", "COSC350", "COSC360", "COSC370", "COSC484")
        ]
      },
      {
        name: "Data Science major",
        units: [
          "MTHS120", "STAT100", "STAT210", 
          choose(1, "COSC102", "MTHS130"),
          choose(3, "COSC330", "COSC350", "COSC380", "STAT320", "STAT330")
        ]
      },
    ],
    plans: { 
      "Full-time, T1 start, Software Development & Data Science Major": [
          { name: "Y1 Trimester 1", units: [ "COSC110", "COSC130", "COSC101", "Elective" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC120", "MTHS120", "STAT100" ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "STAT210", "Elective" ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", choose(1, "COSC102", "MTHS130"), choose(1, "COSC380", "STAT320", "Elective*") ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", "COSC340", "COSC370", choose(1, "STAT330", "Elective*") ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", "COSC330", "COSC350", "COSC360" ] },
          // Can do at most one of the Elective* units
      ]
    }
  },


    {
      code: "BCSLAW2024",
      name: "Bachelor of Computer Science / Bacherlor of Laws 2024",
      structure: [
        {
          name: "Computer Science Core",
          units: [
            "AMTH140",
            "COSC110", "COSC120", "COSC130", "COSC210", "COSC220", "COSC230", "COSC240", "COSC310", "COSC320"
          ]
        },
        {
          name: "Computer Science Listed",
          units: [
            choose(4, "COSC301", "COSC330", "COSC340", "COSC350", "COSC360", "COSC370", "COSC380", "STAT330", "STAT320"),
            choose(2, "COSC301", "COSC330", "COSC340", "COSC350", "COSC360", "COSC370", "COSC380", "STAT330", "STAT320", "COSC101", "COSC102", "COSC250", "COSC260", "COSC481", "COSC482", "COSC483", "COSC484", "MTHS120", "MATH260", "STAT100", "STAT210")
          ]
        },
        {
          name: "Law component",
          units: [
            "Law unit", "Law unit", "Law unit", "Law unit", "Law unit", "Law unit", "Law unit", "Law unit", "Law unit", "Law unit", 
            "Law unit", "Law unit", "Law unit", "Law unit", "Law unit", "Law unit", 
          ]
        }
      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [ "COSC110", "COSC130", "Law unit", "Law unit" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC120", choose(2, "COSC102", "COSC260", "MTHS120", "STAT100", "Law unit*", "Law unit*") ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", choose(2, "COSC101", "COSC250", "STAT210", "Law unit*", "Law unit*") ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", choose(2, "COSC380", "STAT320", "Law unit**", "Law unit**") ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", choose(3, "COSC340", "COSC370", "STAT330", "Law unit**", "Law unit**", "Law unit**") ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", choose(3, "COSC330", "COSC350", "COSC360", "Law unit**", "Law unit**", "Law unit**") ] },
          { name: "Y4 Trimester 1", units: [ choose([0,2], "COSC481", "COSC482").withNote("Two 400-level cybersecurity subjects must be taken across the degree") ] },
          { name: "Y4 Trimester 2", units: [ choose([0,2], "COSC483", "COSC484") ] },
          { name: "Y5 Trimester 1", units: [ "Law unit", "Law unit", "Law unit", "Law unit" ] },
          { name: "Y5 Trimester 2", units: [ "Law unit", "Law unit", "Law unit", "Law unit" ] },
        ]
        // At least 2 Law unit**
        // At most 4 Law unit**
        // At least 6 Law unit*
        // At most 10 (Law unit* and Law unit**) units
      }
    },


])

// Top 3 for BCOMP
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Ethics, ["COSC110", "COSC310", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.ProfExpectations, ["COSC110", "COSC220", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Teamwork, ["COSC101", "COSC102", "COSC220", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Communication, ["COSC101", "COSC102", "COSC220", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Societal, ["COSC110", "COSC210", "COSC240"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Understanding, ["COSC110", "COSC220", "COSC240"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.ProblemSolving, ["COSC101", "COSC102", "COSC220", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Fundamentals, ["COSC110", "COSC230", "COSC240"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Data, ["COSC210", "COSC220", "STAT100"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Networking, ["COSC210", "COSC220", "COSC240"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.HumanFactors, ["COSC101", "COSC102", "COSC220"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Programming, ["COSC110", "COSC120", "COSC230"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Systems, ["COSC210", "COSC220", "COSC310"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Governance, ["COSC310"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.ProjectManagement, ["COSC220", "COSC310", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.ServiceManagement, ["COSC220"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.Cybersecurity, ["AMTH140", "COSC210", "COSC240"])

// Top 3 for MIT
limitCBOK([ "MIT" ], cbok.Ethics, ["COSC110", "COSC510", "COSC594", "COSC595"])
limitCBOK([ "MIT" ], cbok.ProfExpectations, ["COSC110", "COSC220", "COSC594", "COSC595"])
limitCBOK([ "MIT" ], cbok.Teamwork, ["COSC101", "COSC220", "COSC594", "COSC595"])
limitCBOK([ "MIT" ], cbok.Communication, ["COSC220", "COSC570", "COSC594", "COSC595"])
limitCBOK([ "MIT" ], cbok.Societal, ["COSC110", "COSC210", "COSC570"])
limitCBOK([ "MIT" ], cbok.Understanding, ["COSC110", "COSC220", "COSC594", "COSC595"])
limitCBOK([ "MIT" ], cbok.ProblemSolving, ["COSC101", "COSC102", "COSC220", "COSC594", "COSC595"])
limitCBOK([ "MIT" ], cbok.Fundamentals, ["COSC110" ])
limitCBOK([ "MIT" ], cbok.Data, ["COSC210", "COSC220", "STAT100"])
limitCBOK([ "MIT" ], cbok.Networking, ["COSC210", "COSC260", "COSC560"])
limitCBOK([ "MIT" ], cbok.HumanFactors, ["COSC101", "COSC510", "COSC570"])
limitCBOK([ "MIT" ], cbok.Programming, ["COSC110", "COSC120", "COSC560"])
limitCBOK([ "MIT" ], cbok.Systems, ["COSC210", "COSC220", "COSC510"])
limitCBOK([ "MIT" ], cbok.Governance, ["COSC510"])
limitCBOK([ "MIT" ], cbok.ProjectManagement, ["COSC220", "COSC510", "COSC594", "COSC595"])
limitCBOK([ "MIT" ], cbok.ServiceManagement, ["COSC220"])
limitCBOK([ "MIT" ], cbok.Cybersecurity, ["COSC210", "COSC260", "COSC560"])

// Top 3 for MIT
limitCBOK([ "MDSC" ], cbok.Ethics, ["COSC110", "COSC510", "COSC591"])
limitCBOK([ "MDSC" ], cbok.ProfExpectations, ["COSC110", "COSC510", "COSC591" ])
limitCBOK([ "MDSC" ], cbok.Teamwork, ["COSC110", "COSC510", "COSC591", "SCI501"])
limitCBOK([ "MDSC" ], cbok.Communication, ["COSC110", "COSC510", "COSC591"])
limitCBOK([ "MDSC" ], cbok.Societal, ["COSC110", "COSC210", "COSC572"])
limitCBOK([ "MDSC" ], cbok.Understanding, ["COSC110", "COSC572", "COSC591"])
limitCBOK([ "MDSC" ], cbok.ProblemSolving, [ "COSC210", "COSC550", "COSC591", "SCI501"])
limitCBOK([ "MDSC" ], cbok.Fundamentals, ["COSC110" ])
limitCBOK([ "MDSC" ], cbok.Data, ["COSC210", "COSC572", "STAT430"])
limitCBOK([ "MDSC" ], cbok.Networking, ["COSC210", "COSC572"])
limitCBOK([ "MDSC" ], cbok.HumanFactors, ["COSC101", "COSC510", "COSC572"])
limitCBOK([ "MDSC" ], cbok.Programming, ["COSC110", "COSC580", "SCI410"])
limitCBOK([ "MDSC" ], cbok.Systems, ["COSC210", "COSC572", "COSC510"])
limitCBOK([ "MDSC" ], cbok.Governance, ["COSC510"])
limitCBOK([ "MDSC" ], cbok.ProjectManagement, ["COSC510", "COSC591", "COSC572"])
limitCBOK([ "MDSC" ], cbok.ServiceManagement, ["COSC572"])
limitCBOK([ "MDSC" ], cbok.Cybersecurity, ["COSC210", "COSC572" ])
