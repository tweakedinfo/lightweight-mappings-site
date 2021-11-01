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

        {
          code: "BCOMP(CDF)",
          name: "Tinkering with CDF",
          structure: [
            {
              name: "Computational Thinking minor (24cp), DIT stuff",
              units: [
                "STAT100", "MTHS110", "ICT100", "ICT101"
              ]
            },


            {
              name: "Core major (54cp)",
              units: [
                "AMTH140",
                "COSC110", "COSC120",
                or("COSC101", "COSC102"),
                "COSC210", "COSC230", "COSC240",
                "COSC310", "COSC320"
              ]
            },

            {
              name: "Calculus & Linear Algebra minor (24cp)",
              units: [
                "MTHS120", "MTHS130", or("AMTH250", "SCI210"), "PMTH213"
              ]
            },

            {
              name: "Software Development major (42cp)",
              units: [
                "COSC220", "COSC250", "COSC260",
                choose(4, "COSC330", "COSC340", "COSC350", "COSC360", "COSC370")
              ]
            },
            {
              name: "Data Science major (42cp)",
              units: [
                "STAT100", "STAT210", "COSC350", "SCI501",
                choose(3, "MTHS120", "MTHS130", "COSC250", "STAT320", "STAT330", "COSC330", "COSC380")
              ]
            },

            {
              name: "Information Systems major (42cp), MIT equivalent",
              units: [
                or("QM161", "STAT100"), "QM262", "COSC340", "COSC370", "COSC372",
                choose(2, "COSC220", "COSC260", "COSC350", "COSC360")
              ]
            },

            {
              name: "Statistics major (42cp)",
              units: [
                "STAT100", "MTHS120", "STAT210", "STAT330", choose(3, "MTHS130", "MATH260", "STAT270", "STAT320")
              ]
            },
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
          code: "BCOMP(CDF60cp)",
          name: "Tinkering with CDF but 60cp Core",
          structure: [
            {
              name: "Computational Thinking minor (24cp), DIT stuff",
              units: [
                "STAT100", "MTHS110", "ICT100", "ICT101"
              ]
            },


            {
              name: "Core major (60cp)",
              units: [
                "AMTH140",
                "COSC110", "COSC120",
                or("COSC101", "COSC102"),
                "COSC210", "COSC220", "COSC230", "COSC240",
                "COSC310", "COSC320"
              ]
            },

            {
              name: "Calculus & Linear Algebra minor (24cp)",
              units: [
                "MTHS120", "MTHS130", or("AMTH250", "SCI210"), "PMTH213"
              ]
            },

            {
              name: "Software Development major (48cp)",
              units: [
                "COSC250", "COSC260",
                choose(6, "ICT100", "ICT101", "COSC330", "COSC340", "COSC350", "COSC360", "COSC370")
              ]
            },
            {
              name: "Data Science major (48cp)",
              units: [
                "STAT100", "STAT210", "COSC350", "SCI501",
                choose(4, "MTHS120", "MTHS130", "COSC250", "STAT320", "STAT330", "COSC330", "COSC380")
              ]
            },

            {
              name: "Information Systems major (48cp), MIT equivalent",
              units: [
                or("QM161", "STAT100"), "QM262", "COSC340", "COSC370", "COSC372",
                choose(3, "COSC220", "COSC260", "COSC350", "COSC360")
              ]
            },

            {
              name: "Statistics major (48cp)",
              units: [
                "STAT100", "MTHS120", "STAT210", "STAT330", choose(4, "MTHS130", "MATH260", "STAT270", "STAT320")
              ]
            },
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
