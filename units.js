"use strict";

console.log("Loading units")

addUnits([
    {
      code: "AMTH140",
      name: "Discrete Mathematics",
      prereq: [],
      cbok: [ problemSolving(2), understanding(1), fundamentals(2), cybersecurity(1) ],
      sfia: [],
      swebok: [swebok.MathFoundations ],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "AMTH250",
      name: "Introduction to Programming in the Sciences",
      prereq: [ or("MTHS120", "MTHS130")],
      cbok: [ programming(1) ], sfia: [],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "SCI210",
      name: "Introduction to Scientific Programming",
      prereq: [ choose(1, "MTHS110", "MTHS120", "MTHS130")],
      cbok: [ programming(1) ], sfia: [],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "MTHS100",
      name: "Introduction to Quantitative Skills",
      prereq: [], cbok: [ ], sfia: [], 
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "MTHS110",
      name: "Quantitative Skills with Applications",
      prereq: [], cbok: [ ], sfia: [], 
      other: [ idverify.ProctoredExam ]
    },


    {
      code: "MTHS120",
      name: "Calculus & Linear Algebra 1",
      prereq: [], cbok: [ ], sfia: [],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "MTHS130",
      name: "Calculus & Linear Algebra 2",
      prereq: [ "MTHS120" ], cbok: [ ], sfia: [],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "PMTH213",
      name: "Linear Algebra",
      prereq: [ "MTHS120", "MTHS130" ], cbok: [ ], sfia: [],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "MM105",
      name: "Communication for Business",
      prereq: [ ], cbok: [ communication(1) ], sfia: [], 
      other: [ idverify.TurnItIn ]
    },

    {
      code: "MM203",
      name: "Management Practices in Responsible Organisations",
      prereq: [ ], cbok: [ communication(1), teamwork(1) ], sfia: [],
      other: [ idverify.TurnItIn, idverify.ProctoredExam ]
    },


    {
      code: "STAT100",
      name: "Introduction to Statistical Modelling",
      prereq: [], cbok: [ data(2) ], sfia: [],
      other: [ idverify.ProctoredExam ] 
    },

    {
      code: "STAT210",
      name: "Statistical Modelling & Experimental Design",
      prereq: [ choose(1, "STAT100", "AMTH250", "SCI210") ],
      cbok: [ data(2) ], sfia: [], 
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "STAT410",
      name: "Statistical Modelling & Experimental Design",
      prereq: [ "candidature in a pg award" ],
      cbok: [ data(2) ], sfia: [], 
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "MATH260",
      name: "Probability and Simulation",
      prereq: [ "MTHS120", "MTHS130" ],
      cbok: [ data(3) ], sfia: [], 
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "STAT270",
      name: "Inference",
      prereq: [ "MATH260" ],
      cbok: [ data(3) ], sfia: [], 
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "STAT320",
      name: "Advanced Statistical Modelling",
      prereq: [ "STAT210" ],
      cbok: [ data(3) ], sfia: [], 
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "STAT330",
      name: "Statistical Learning",
      prereq: [ "STAT210" ],
      cbok: [ data(3) ], sfia: [], tags: ["Advanced"], 
      other: [ idverify.TurnItIn, idverify.Oral ]
    },

    {
      code: "STAT430",
      name: "Statistical Learning",
      prereq: [ "STAT100", "candidature in MDatSci or pg award" ],
      cbok: [ data(3) ], sfia: [], tags: ["Advanced"],
      other: [ idverify.TurnItIn, idverify.Oral ]
    },


    {
      code: "SCI410",
      name: "Introduction to Scientific Programming",
      prereq: [ cp(96), "or enrolment in pg award" ],
      cbok: [ programming(2), data(2) ], sfia: [], tags: ["Advanced"],
      other: [ idverify.ProctoredExam ]
    },


    {
      code: "SCI501",
      name: "Special Topics in Science A",
      prereq: [ cp(48), "permission of HoS"],
      cbok: [ data(3) ], sfia: [],
      tags: [ "Advanced", "Capstone" ],
      other: [ idverify.TurnItIn, idverify.Project, idverify.PersonalisedAssessment ]
    },

    {
      code: "QM161",
      name: "Business Statistics",
      prereq: [], cbok: [ data(2) ], sfia: [],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "QM262",
      name: "Introduction to Business Analytics",
      prereq: [], cbok: [ data(2) ], sfia: [],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "ICT100",
      name: "Computational Thinking",
      prereq: [],
      cbok: [ ethics(1), societal(1), humanFactors(1), problemSolving(1), programming(1) ],
      swebok: [ swebok.MathFoundations, swebok.CompFoundations, swebok.EngFoundations, swebok.Construction ],
      sfia: [],
      other: []
    },

    {
      code: "ICT101",
      name: "From Logic to Data Processing",
      prereq: [],
      cbok: [ fundamentals(1), understanding(1), data(1) ],
      swebok: [ swebok.MathFoundations, swebok.CompFoundations ],
      sfia: [],
      other: []
    },

    {
      code: "COSC101",
      name: "Software Development Studio 1",
      prereq: [],
      cbok: [ problemSolving(2), teamwork(2), communication(2), networking(1), humanFactors(1) ],
      swebok: [ swebok.MathFoundations, swebok.CompFoundations, swebok.Construction, swebok.ProfPractice ],
      sfia: [],
      other: [ idverify.ProctoredExam, idverify.GroupWork, idverify.Video ]
    },

    {
      code: "COSC102",
      name: "Data Science Studio 1",
      prereq: [ choose(2, "COSC110", "STAT110") ],
      cbok: [  programming(2), teamwork(2), communication(2), data(1),problemSolving(1)],
      swebok: [ swebok.MathFoundations, swebok.CompFoundations, swebok.EngFoundations, swebok.ProfPractice  ],
      sfia: [],
      other: [ idverify.ProctoredExam, idverify.GroupWork, idverify.Video ]
    },


    {
      code: "COSC110",
      name: "Introduction to Programming and the Unix Environment",
      prereq: [],
      cbok: [ ethics(1), expectations(1), teamwork(1), societal(2), understanding(1), communication(1), programming(2), fundamentals(1) ],
      sfia: [], 
      other: [ idverify.ProctoredExam, idverify.TurnItIn ]
    },

    {
      code: "COSC120",
      name: "Object Oriented Programming",
      prereq: [ "COSC110" ],
      cbok: [ programming(2), problemSolving(1) ],
      swebok: [ swebok.Construction, swebok.Design, swebok.ModelsAndMethods ],
      sfia: [],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "COSC210",
      name: "Database Management Systems",
      prereq: [ "COSC110" ],
      cbok: [ problemSolving(1), societal(1), data(3), networking(2), systems(1), cybersecurity(1) ],      
      swebok: [ swebok.Construction, swebok.Design, swebok.ModelsAndMethods ],
      sfia: [ ],
      other: [ idverify.ProctoredExam, idverify.TurnItIn ]
    },

    {
      code: "COSC220",
      name: "Software Development Studio 2",
      prereq: [ "COSC120" ],
      cbok: [
        problemSolving(3), ethics(1), expectations(3), teamwork(2), communication(2), societal(2),
        understanding(2), data(3), programming(3), humanFactors(2), systems(2), projectManagement(2),
        serviceManagement(1)
      ],
      swebok: [ 
        swebok.Requirements, swebok.Design, swebok.Construction, swebok.Testing, swebok.Maintenance,
        swebok.ConfigManagement, swebok.EngManagement, swebok.Process, swebok.ModelsAndMethods, swebok.Quality, 
        swebok.ProfPractice, swebok.EngFoundations
      ],
      sfia: [],
      other: [ idverify.TurnItIn, idverify.GroupWork, idverify.Project, idverify.DataTrails, idverify.Video ]
    },

    {
      code: "COSC230",
      name: "Data Structures & Algorithms",
      prereq: [ "AMTH140", "COSC120", choose(1, "COSC101", "COSC110", "SCI210", "AMTH250") ],
      cbok: [ fundamentals(2), programming(2) ],
      swebok: [ swebok.MathFoundations, swebok.CompFoundations, swebok.Construction, swebok.ModelsAndMethods, swebok.Design ],
      sfia: [], 
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "COSC240",
      name: "Operating Systems",
      prereq: [ "COSC120" ],
      cbok: [ problemSolving(2), societal(1), understanding(2), fundamentals(2), networking(2), programming(3), systems(1), cybersecurity(2) ],
      sfia: [], 
      swebok: [ swebok.CompFoundations ], 
      other: [ idverify.ProctoredExam, idverify.TurnItIn ]
    },

    {
      code: "COSC250",
      name: "Functional and Reactive Programming",
      prereq: [ "COSC120", choose(1, "COSC101", "COSC110", "SCI210", "AMTH250") ],
      cbok: [ understanding(1), programming(3), systems(2) ],
      swebok: [ swebok.Design, swebok.Construction, swebok.Testing, swebok.ModelsAndMethods, swebok.MathFoundations, swebok.CompFoundations ],
      sfia: [],
      other: [ idverify.ProctoredExam, idverify.Video ]
    },

    {
      code: "COSC260",
      name: "Web Programming",
      prereq: [ choose(2, "COSC101", "COSC110", "COSC120") ],
      cbok: [ societal(1), networking(2), programming(2), humanFactors(2), systems(2), cybersecurity(2) ],
      sfia: [], 
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "COSC301",
      name: "Special Topic in Computing",
      prereq: [ cp(48), "permission of HoS" ],
      cbok: [ ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.PersonalisedAssessment ]
    },


    {
      code: "COSC310",
      name: "Software Project Management",
      prereq: [ choose(1, "COSC210", "COSC220") ],
      cbok: [ ethics(2), expectations(2), teamwork(2), communication(3), systems(2), governance(3), projectManagement(3) ],
      dsbok: [],
      swebok: [ swebok.Requirements, swebok.Maintenance, swebok.EngManagement, swebok.Process, swebok.Quality, swebok.ProfPractice, swebok.Economics ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.ProctoredExam, idverify.GroupWork, idverify.TurnItIn, idverify.Video ]
    },

    {
      code: "COSC510",
      name: "Software Project Management",
      prereq: [ coreq("COSC110", "COSC120" ) ],
      cbok: [ ethics(2), expectations(2), teamwork(2), communication(3), systems(2), governance(3), projectManagement(3) ],
      swebok: [ swebok.Requirements, swebok.Maintenance, swebok.EngManagement, swebok.Process, swebok.Quality, swebok.ProfPractice, swebok.Economics ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.ProctoredExam, idverify.GroupWork, idverify.TurnItIn, idverify.Video ]
    },


    {
      code: "COSC320",
      name: "Information Technology Project",
      prereq: [ "COSC220", "COSC310", cp(96) ],
      cbok: [ problemSolving(3), ethics(2), expectations(2), teamwork(3), communication(3), systems(3), projectManagement(3) ],
      swebok: [ swebok.Requirements, swebok.Construction, swebok.EngManagement, swebok.Process, swebok.Quality, swebok.ProfPractice ],
      sfia: [],
      tags: [ "Advanced", "Capstone" ],
      other: [ idverify.GroupWork, idverify.Project, idverify.TurnItIn, idverify.Video, idverify.Oral ]
    },

    {
      code: "COSC591",
      name: "Information Technology Project",
      prereq: [ "COSC510", choose(1, "COSC530", "COSC540", "COSC550", "COSC560", "COSC570", "COSC572", "COSC580") ],
      cbok: [ problemSolving(3), ethics(2), expectations(2), teamwork(3), communication(3), systems(3), projectManagement(3) ],
      sfia: [],
      tags: [ "Advanced", "Capstone" ],
      other: [ idverify.GroupWork, idverify.Project, idverify.TurnItIn, idverify.Video, idverify.Oral ]
    },


    {
      code: "COSC594",
      name: "Information Technology Project: Proposal and Design",
      prereq: [ "COSC220", cp(48) ],
      cbok: [ problemSolving(3), ethics(2), expectations(2), teamwork(3), communication(3), systems(3), projectManagement(3) ],
      sfia: [],
      tags: [ "Advanced", "Capstone" ],
      other: [ idverify.GroupWork, idverify.Project, idverify.TurnItIn, idverify.Video, idverify.Oral ]
    },

    {
      code: "COSC595",
      name: "Information Technology Project: Implementation",
      prereq: [ "COSC220", cp(48) ],
      cbok: [ problemSolving(3), ethics(2), expectations(2), teamwork(3), communication(3), systems(3), projectManagement(3) ],
      sfia: [],
      tags: [ "Advanced", "Capstone" ],
      other: [ idverify.GroupWork, idverify.Project, idverify.TurnItIn, idverify.Video, idverify.Oral ]
    },


    {
      code: "COSC330",
      name: "Parallel and Distributed Computing",
      prereq: [ or("COSC230", "COSC240") ],
      cbok: [ fundamentals(3), data(3), networking(3), systems(2) ],
      sfia: [],
      tags: ["Advanced"],
      other: [  ]
    },

    {
      code: "COSC530",
      name: "Parallel and Distributed Computing",
      prereq: [ "COSC110", choose(2, "COSC210", "COSC220", "COSC230", "COSC240", "COSC250", "COSC260", "STAT210") ],
      cbok: [ fundamentals(3), data(3), networking(3), systems(2) ],
      sfia: [],
      tags: ["Advanced"],
      other: [ ]
    },

    
    {
      code: "COSC340",
      name: "Computer Networks and Information Security",
      prereq: [ cp(72), "AMTH140", "COSC240" ],
      cbok: [ societal(1), communication(3), fundamentals(2), understanding(1), data(2), networking(3), programming(3), cybersecurity(3) ],
      sfia: [],
      tags: ["Advanced"], 
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "COSC540",
      name: "Computer Networks and Information Security",
      prereq: [ "COSC110", choose(2, "COSC210", "COSC220", "COSC230", "COSC240", "COSC250", "COSC260", "STAT210") ],
      cbok: [ societal(1), communication(3), fundamentals(2), understanding(1), data(2), networking(3), programming(3), cybersecurity(3) ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.ProctoredExam ]
    },


    {
      code: "COSC350",
      name: "Artificial Intelligence",
      prereq: [ "COSC230" ],
      cbok: [ data(3), programming(3), fundamentals(2), systems(2) ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "COSC550",
      name: "Artificial Intelligence",
      prereq: [ "COSC110", choose(2, "COSC210", "COSC220", "COSC230", "COSC240", "COSC250", "COSC260", "STAT210") ],
      cbok: [ data(3), programming(3), fundamentals(2), systems(2) ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.ProctoredExam ]
    },

    {
      code: "COSC360",
      name: "Advanced Web Programming",
      prereq: [ "COSC120", "COSC260" ],
      cbok: [ communication(2), networking(3), programming(3), humanFactors(2), systems(2), serviceManagement(2), cybersecurity(2) ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.ProctoredExam, idverify.Project, idverify.Video ]
    },

    {
      code: "COSC560",
      name: "Advanced Web Programming",
      prereq: [ "COSC260", or("COSC110", "COSC120") ],
      cbok: [ communication(2), networking(3), programming(3), humanFactors(2), systems(2), serviceManagement(2), cybersecurity(2) ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.ProctoredExam, idverify.Project, idverify.Video ]
    },


    {
      code: "COSC370",
      name: "User Experience & Interaction Design",
      prereq: [ cp(72), "COSC220" ],
      cbok: [ communication(3), societal(3), humanFactors(3), systems(3) ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.Project, idverify.Video, idverify.TurnItIn ]
    },

    {
      code: "COSC570",
      name: "User Experience & Interaction Design",
      prereq: [ "COSC110", choose(2, "COSC210", "COSC220", "COSC230", "COSC240", "COSC250", "COSC260", "STAT210") ],
      cbok: [ communication(3), societal(3), humanFactors(3), systems(3) ],
      sfia: [],
      tags: ["Advanced"],
      other: [ idverify.Project, idverify.Video, idverify.TurnItIn ]
    },


    {
      code: "COSC372",
      name: "Management Information Systems",
      prereq: [ cp(48) ],
      cbok: [ societal(2), data(2), systems(3), projectManagement(1), serviceManagement(2) ],
      sfia: [],
      other: [ idverify.PersonalisedAssessment, idverify.TurnItIn ]
    },

    {
      code: "COSC572",
      name: "Management Information Systems",
      prereq: [ cp(48) ],
      cbok: [ societal(2), data(2), systems(3), projectManagement(1), serviceManagement(2) ],
      sfia: [],
      other: [ idverify.PersonalisedAssessment, idverify.TurnItIn ] 
    },


    {
      code: "COSC380",
      name: "Algorithms in Machine Learning",
      prereq: [ choose(1, "MTHS120", "MTHS130"), choose(1, "COSC230", "AMTH250", "SCI210", "STAT330") ],
      cbok: [ data(3), programming(2) ],
      sfia: [],
      tags: ["Advanced"]
    },

    {
      code: "COSC580",
      name: "Algorithms in Machine Learning",
      prereq: [ "MTHS120", or("COSC110", "SCI410") ],
      cbok: [ data(3), programming(2) ],
      sfia: [],
      tags: ["Advanced"]
    },


])