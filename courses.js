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
            "Listed elective", "Listed elective", "Listed elective", "Listed elective", "Listed elective",
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
            "Listed elective", "Listed elective", "Listed elective", "Listed elective", "Listed elective",
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
            choose(3, "COSC530", "COSC540", "COSC550", "COSC572"), "Listed elective", "Listed elective"
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
            "Listed elective", "Listed elective", "Listed elective"
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


])


limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.ethics, ["COSC110", "COSC310", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.expectations, ["COSC110", "COSC220", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.teamwork, ["COSC101", "COSC102", "COSC220", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.communication, ["COSC101", "COSC102", "COSC220", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.societal, ["COSC110", "COSC210", "COSC240"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.understanding, ["COSC110", "COSC220", "COSC240"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.problemSolving, ["COSC101", "COSC102", "COSC220", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.fundamentals, ["COSC110", "COSC230", "COSC240"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.data, ["COSC210", "COSC220", "STAT100"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.networking, ["COSC210", "COSC220", "COSC240"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.humanFactors, ["COSC101", "COSC102", "COSC220"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.programming, ["COSC110", "COSC120", "COSC230"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.systems, ["COSC210", "COSC220", "COSC310"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.governance, ["COSC310"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.projectManagement, ["COSC220", "COSC310", "COSC320"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.serviceManagement, ["COSC220"])
limitCBOK([ "BCOMP(SD)", "BCOMP(DS)", "BCOMP(dbl)", "BCSLAW" ], cbok.cybersecurity, ["AMTH140", "COSC210", "COSC240"])
