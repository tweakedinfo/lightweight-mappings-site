"use strict";

console.log("Loading courses")

addCourses([
    {
      code: "DIT",
      name: "Diploma in Information Technology",
      structure: [
        {
          name: "Core",
          units: [ "ICT100", "ICT101", "COSC110", "COSC120", or("COSC101", "COSC102") ]
        },
        {
          name: "Listed",
          units: [ choose(3, "AMTH140", "AMTH250", "MTHS100", "MTHS120", "STAT100", "STAT210", "SCI210",
            "COSC101", "COSC102",
            "COSC210", "COSC220", "COSC230", "COSC240", "COSC250", "COSC260",
            "MM105") ]
        }
      ],
      plans: {
        "Full-time, T3 start": [
          { name: "Y1 Trimester 3", units: [ "ICT100", "ICT101" ] },
          { name: "Y1 Trimester 1", units: [ "COSC110", "MTHS110", "COSC101" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "STAT100", "COSC102" ] },
        ]
      }
    },

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
    },

    {
      code: "BCOMP(DS)",
      name: "Bachelor of Computer Science (Data Science)",
      structure: [
        {
          name: "Core",
          units: [
            "AMTH140", "MTHS120", "STAT100",
            "COSC110",
            or("COSC101", "COSC102"), "COSC120",
            "COSC210", "COSC220", "COSC230", "COSC240",
            "COSC310", "COSC320"
          ]
        },

        {
          name: "Data Science major",
          units: [
            choose(5, "STAT210", "STAT330", "COSC250", "COSC330", "COSC350", "COSC380")
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
    },
])