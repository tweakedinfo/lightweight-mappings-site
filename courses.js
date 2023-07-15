"use strict";

console.log("Loading courses")

// Sets a function that can link to the Handbook for a course. 
// If this isn't set, the "Link to handbook" will not be shown on course pages
setHandbookUrl((code) => `https://handbook.une.edu.au/courses/2022/${code}?year=2022`)

addCourses([
  
  {
    code: "BCOMP(2024)",
    name: "Bachelor of Computer Science 2024 draft",
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
          or("MTHS120", "STAT100"),
          choose(6, "COSC101", "COSC250", "COSC260", "COSC330", "COSC340", "COSC350", "COSC360", "COSC370", "COSC484")
        ]
      },
      {
        name: "Data Science major",
        units: [
          "MTHS120", "STAT100", "STAT210", 
          choose(1, "COSC102", "MTHS130", "MATH260", "COSC250"),
          choose(3, "COSC330", "COSC350", "COSC380", "STAT320", "STAT330")
        ]
      },

      {
        name: "Cybersecurity major",
        units: [
          "STAT100", 
          choose(1, "COSC101", "COSC102", "MTHS120"),
          "COSC340", "COSC350", "COSC372",
          choose(2, "COSC481", "COSC482", "COSC483", "COSC484")
        ]
      },

      {
        name: "Artificial Intelligence major",
        units: [
          "MTHS120", "STAT100",
          choose(2, "COSC102", "COSC250", "MATH260"),
          choose(3, "COSC331", "COSC350", "COSC351", "COSC352", "COSC380", "COSC483")
        ]
      },

      {
        name: "Preparatory Minor",
        units: [
          choose(4, "MTHS100", "MTHS110", "ICT100", "ICT101", "MM105")
        ]
      },

      {
        name: "Computational Science Minor",
        units: [
          choose(4, "MTHS120", "MTHS130", "AMTH250", "MATH260", "SCI310")
        ]
      },


      {
        name: "Elective space (or second major / minors)",
        units: [
          "Elective", "Elective", "Elective", "Elective", "Elective", "Elective", "Elective"
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
    code: "DIT",
    name: "Diploma in Information Technology",
    structure: [
      {
        name: "Core",
        units: [ "ICT101", "COSC110", "COSC130", or("ICT100", "COSC120"), or("COSC101", "COSC102") ]
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
      code: "BCOMP(SD)",
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
        },

        {
          name: "Listed & Elective",
          units: [ 
            choose(5, 
              "ICT100", "ICT101", "COSC301", "COSC372", "COSC380",
              "Listed elective", "Listed elective", "Listed elective", "Listed elective", "Listed elective"
            ),
            "Elective", "Elective"
          ]
        }
      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [ "COSC101", "COSC110", "MTHS120", "Elective" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC102", "COSC120", or("STAT100", "MTHS130") ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "COSC250", "Elective" ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", "COSC260", "Listed Elective" ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", "COSC340", "COSC370", "Listed Elective" ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", "COSC330", "COSC350", "COSC360" ] },
        ]
      }
    },

    {
      code: "BCOMP single major sd (ACS diagram)",
      name: "Bachelor of Computer Science",
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
          name: "Major",
          units: [
            choose(5, "Prescribed 300-level", "Prescribed 300-level", "Prescribed 300-level", "Prescribed 300-level", "Prescribed 300-level", "Prescribed", "Prescribed")
          ]
        },

        {
          name: "Listed & Elective",
          units: [ 
            choose(5, 
              "Listed", "Listed", "Listed", "Listed", "Listed",
            ),
            choose(2, 
              "Elective", "Elective"
            )
          ]
        }
      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [ or("COSC101", "Elective"), "COSC110", "MTHS120", "Elective", ] },
          { name: "Y1 Trimester 2", units: [ or("COSC102", "Elective"), "AMTH140", "COSC120", or("STAT100", "MTHS130"),   ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "Listed", "Listed" ] },
          { name: "Y2 Trimester 2", units: [ "COSC240", "COSC220", "Prescribed", "Listed" ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", "Prescribed 300-level", "Prescribed 300-level", "Listed" ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", "Prescribed 300-level", "Prescribed 300-level", "Listed", ] },
        ]
      }
    },


    {
      code: "BCOMP single major (ACS diagram)",
      name: "Bachelor of Computer Science",
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
          name: "Major",
          units: [
            choose(5, "Prescribed 300-level", "Prescribed 300-level", "Prescribed 300-level", "Prescribed 300-level", "Prescribed 300-level", "Prescribed", "Prescribed")
          ]
        },

        {
          name: "Listed & Elective",
          units: [ 
            choose(5, 
              "Listed", "Listed", "Listed", "Listed", "Listed 300-level COSC",
            ),
            choose(2, 
              "Elective", "Elective"
            )
          ]
        }
      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [ or("COSC101", "Elective"), "COSC110", "MTHS120", "Elective", ] },
          { name: "Y1 Trimester 2", units: [ or("COSC102", "Elective"), "AMTH140", "COSC120", or("STAT100", "MTHS130"),   ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "Listed", "Listed" ] },
          { name: "Y2 Trimester 2", units: [ "COSC240", "COSC220", "Prescribed", "Listed" ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", "Prescribed 300-level", "Prescribed", "Listed" ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", "Prescribed 300-level", "Prescribed 300-level", "Listed 300-level COSC", ] },
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
        },

        {
          name: "Listed & Elective",
          units: [ 
            choose(5,
              "ICT100", "ICT101", "COSC260", "COSC301", "COSC340", "COSC360", "COSC370", "COSC372",
              "Listed elective", "Listed elective", "Listed elective", "Listed elective", "Listed elective"
            ),
            "Elective", "Elective"
          ]
        }

      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [ "COSC101", "COSC110", "MTHS120", "Elective" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC102", "COSC120", "STAT100" ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "COSC250", "STAT210" ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", "Listed Elective", "Elective" ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", "STAT330", "Listed Elective", "Listed Elective" ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", "COSC330", "COSC350", "COSC380" ] },
        ]
      }
    },

    {
      code: "BCOMP(dbl)",
      name: "Bachelor of Computer Science (Software Development & Data Science)",
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
            "STAT210", "STAT330", "COSC250", "COSC350", "COSC380"
          ]
        },

        {
          name: "Software Development major",
          units: [
            "COSC260", "COSC330", "COSC340", "COSC360", "COSC370"
          ]
        },

        {
          name: "Listed & Elective",
          units: [ 
            "Elective", "Elective"
          ]
        }

      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [  "COSC110", "MTHS120", "Elective", or("COSC101", "Elective") ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC120", "STAT100", or("COSC102", "Elective") ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "COSC250", "STAT210" ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", "COSC260", "COSC350" ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", "COSC340", "COSC370", "STAT330" ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", "COSC330", "COSC360", "COSC380" ] },
        ]
      }
    },

    {
      code: "BCSLAW",
      name: "Bachelor of Computer Science / Bacherlor of Laws",
      structure: [
        {
          name: "Computer Science Core",
          units: [
            "AMTH140",
            "COSC101", "COSC110", "COSC120", "COSC210", "COSC220", "COSC230", "COSC240", "COSC310", "COSC320",
            or("MTHS120", "STAT100")
          ]
        },
        {
          name: "Computer Science Listed",
          units: [
            choose(5, "COSC250", "COSC260", "COSC301", "COSC330", "COSC340", "COSC350", "COSC360", "COSC370", "COSC380", "STAT210", "STAT330")
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
          { name: "Y1 Trimester 1", units: [ "COSC110", "COSC101", "Law unit", "Law unit" ] },
          { name: "Y1 Trimester 2", units: [ "AMTH140", "COSC120", "Law unit", or("MTHS120", "STAT100") ] },
          { name: "Y2 Trimester 1", units: [ "COSC210", "COSC230", "Law unit", choose(1, "COSC250", "STAT210") ] },
          { name: "Y2 Trimester 2", units: [ "COSC220", "COSC240", "Law unit", "COSC260" ] },
          { name: "Y3 Trimester 1", units: [ "COSC310", "Law unit", choose(2, "COSC340", "COSC370", "STAT330") ] },
          { name: "Y3 Trimester 2", units: [ "COSC320", "Law unit", "Law unit", choose(1, "COSC330", "COSC350", "COSC360", "COSC380") ] },
          { name: "Y4 Trimester 1", units: [ "Law unit", "Law unit", "Law unit", "Law unit",  ] },
          { name: "Y4 Trimester 2", units: [ "Law unit", "Law unit", "Law unit", "Law unit",  ] },
          { name: "Y5 Trimester 1", units: [ "Law unit", "Law unit", "Law unit", "Law unit",  ] },
          { name: "Y5 Trimester 2", units: [ "Law unit", "Law unit", "Law unit", "Law unit",  ] },
        ]
      }
    },


    {
      code: "MIT",
      name: "Master of Information Technology",
      structure: [
        {
          name: "Core",
          units: [
            "COSC101", "COSC110", "COSC120", "COSC210", "COSC220", "COSC260", "COSC510", "COSC560", "COSC570"
          ]
        },
        {
          name: "Research and Capstone",
          units: [
            "COSC594", "COSC595"
          ]
        },
        {
          name: "Listed group 1",
          units: [
            choose(3, "COSC530", "COSC540", "COSC550", "COSC572")
          ]
        },
        {
          name: "Listed group 2",
          units: [
            choose(2,
              "AMTH250", "COSC240", "COSC250", "SCI410", 
              "COSC530", "COSC540", "COSC550", "COSC572", "Listed elective", "Listed elective"
            )
          ]
        }
      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [ "COSC101", "COSC110", "COSC120", "COSC210" ] },
          { name: "Y1 Trimester 2", units: [ "COSC220", "COSC260", "COSC572", "Listed elective" ] },
          { name: "Y2 Trimester 1", units: [ "COSC510", "COSC540", "COSC570", "COSC594" ] },
          { name: "Y2 Trimester 2", units: [ "COSC240", "COSC550", "COSC560", "COSC595" ] },
        ]
      }
    },

    {
      code: "MDSC",
      name: "Master of Data Science",
      structure: [
        {
          name: "Core",
          units: [
            "COSC110", "COSC210", "COSC510", "COSC550", "COSC572", "COSC580", "MTHS120", "SCI410", 
            "STAT100", or("STAT210", "STAT410"), "STAT430"
          ]
        },
        {
          name: "Research and Capstone",
          units: [
            "COSC591", "SCI501"
          ]
        },
        {
          name: "Listed units",
          units: [
            choose(3, "AMTH250", "COSC230", "COSC250", "COSC240", "COSC260", "COSC530", "COSC540", "COSC560", "COSC570", "STAT420",
              "Listed elective", "Listed elective", "Listed elective")
          ]
        }
      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [ "COSC110", "COSC210", "COSC510", "SCI410" ] },
          { name: "Y1 Trimester 2", units: [ "STAT100", "MTHS120", "COSC572", "Listed elective" ] },
          { name: "Y2 Trimester 1", units: [ "STAT210", "STAT430", "Listed elective", "Listed elective" ] },
          { name: "Y2 Trimester 2", units: [ "COSC550", "COSC580", "SCI501", "COSC591" ] },
        ]
      }
    },

    {
      code: "MDSC",
      name: "Master of Data Science",
      structure: [
        {
          name: "Core",
          units: [
            "COSC110", "COSC210", "COSC510", "COSC550", "COSC572", "COSC580", "MTHS120", "SCI410", 
            "STAT100", or("STAT210", "STAT410"), "STAT430"
          ]
        },
        {
          name: "Research and Capstone",
          units: [
            "COSC591", "SCI501"
          ]
        },
        {
          name: "Listed units",
          units: [
            choose(3, "Listed 500-level", "Listed 500-level", "Listed")
          ]
        }
      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [ "SCI410", "COSC110", "COSC210", "COSC510",  ] },
          { name: "Y1 Trimester 2", units: [ "MTHS120", "COSC572", "STAT100", "Listed" ] },
          { name: "Y2 Trimester 1", units: [ or("STAT210", "STAT410"), "STAT430", "Listed 500-level", "Listed 500-level" ] },
          { name: "Y2 Trimester 2", units: ["COSC591", "SCI501", "COSC580", "COSC550",   ] },
        ]
      }
    },


    {
      code: "MIT(ACS diagram)",
      name: "Master of Information Technology",
      structure: [
        {
          name: "Core",
          units: [
            "COSC101", "COSC110", "COSC120", "COSC210", "COSC220", "COSC260", "COSC510", "COSC560", "COSC570"
          ]
        },
        {
          name: "Research and Capstone",
          units: [
            "COSC594", "COSC595"
          ]
        },
        {
          name: "Listed group 1",
          units: [
            choose(3, "COSC530", "COSC540", "COSC550", "COSC572")
          ]
        },
        {
          name: "Listed group 2",
          units: [
            choose(2,
              "AMTH250", "COSC240", "COSC250", "SCI410", 
              "COSC530", "COSC540", "COSC550", "COSC572", "Listed elective", "Listed elective"
            )
          ]
        }
      ],
      plans: {
        "Full-time, T1 start": [
          { name: "Y1 Trimester 1", units: [ "COSC101", "COSC110", "COSC120", "COSC210" ] },
          { name: "Y1 Trimester 2", units: [ "COSC220", "COSC260", "Listed Group 1", "Listed Group 2" ] },
          { name: "Y2 Trimester 1", units: [ "COSC510", "Listed Group 1", "COSC570",    "COSC594",] },
          { name: "Y2 Trimester 2", units: [  "COSC560",  "Listed Group 2", "Listed Group 1", "COSC595",] },
        ]
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

// limits for the DIT, though these are moot (not accredited)
limitCBOK([ "DIT" ], cbok.Ethics, [ "ICT100", "COSC110", "COSC220"])
limitCBOK([ "DIT" ], cbok.ProfExpectations, [ "COSC110", "COSC220"])
limitCBOK([ "DIT" ], cbok.Teamwork, [ "COSC110", "COSC101", "COSC102", "COSC220"])
limitCBOK([ "DIT" ], cbok.Communication, [ "COSC101", "COSC102", "MM105"])
limitCBOK([ "DIT" ], cbok.Societal, ["ICT100", "COSC110", "COSC210"])
limitCBOK([ "DIT" ], cbok.Understanding, [ "ICT101", "COSC110", "COSC220"])
limitCBOK([ "DIT" ], cbok.ProblemSolving, [ "ICT100", "COSC120", "COSC101", "COSC102"])
limitCBOK([ "DIT" ], cbok.Fundamentals, ["ICT101", "COSC110", "COSC240"])
limitCBOK([ "DIT" ], cbok.Data, [ "ICT101", "COSC102", "COSC210"])
limitCBOK([ "DIT" ], cbok.Networking, [ "COSC101", "COSC210", "COSC240"])
limitCBOK([ "DIT" ], cbok.HumanFactors, ["COSC101", "COSC102", "ICT100"])
limitCBOK([ "DIT" ], cbok.Programming, ["ICT100", "COSC110", "COSC120" ])
limitCBOK([ "DIT" ], cbok.Systems, ["COSC210", "COSC220", "COSC260"])
limitCBOK([ "DIT" ], cbok.Governance, [ "ICT100 "])
limitCBOK([ "DIT" ], cbok.ProjectManagement, ["COSC220" ])
limitCBOK([ "DIT" ], cbok.ServiceManagement, ["COSC220"])
limitCBOK([ "DIT" ], cbok.Cybersecurity, ["COSC210", "COSC240", "COSC260"])
