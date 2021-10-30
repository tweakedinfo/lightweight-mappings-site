"use strict";

console.log("Loading units")

addUnits([
    {
      code: "AMTH140",
      name: "Discrete Mathematics",
      prereq: [],
      cbok: [ problemSolving(2), understanding(1), fundamentals(2), cybersecurity(1) ],
      sfia: []
    },

    {
      code: "MTHS120",
      name: "Calculus & Linear Algebra 1",
      prereq: [], cbok: [ ], sfia: []
    },

    {
      code: "MTHS130",
      name: "Calculus & Linear Algebra 2",
      prereq: [ "MTHS120" ], cbok: [ ], sfia: []
    },

    {
      code: "STAT100",
      name: "Introduction to Statistical Modelling",
      prereq: [], cbok: [ data(2) ], sfia: []
    },

    {
      code: "ICT100",
      name: "Computational Thinking",
      prereq: [],
      cbok: [  ],
      sfia: []
    },

    {
      code: "ICT101",
      name: "From Logic to Data Processing",
      prereq: [],
      cbok: [ ],
      sfia: []
    },

    {
      code: "COSC101",
      name: "Software Development Studio 1",
      prereq: [],
      cbok: [ problemSolving(2), teamwork(2), communication(2), networking(1), humanFactors(1) ],
      sfia: []
    },

    {
      code: "COSC102",
      name: "Data Science Studio 1",
      prereq: [ choose(2, "COSC110", "STAT110") ],
      cbok: [  ],
      sfia: []
    },


    {
      code: "COSC110",
      name: "Introduction to Programming and the Unix Environment",
      prereq: [],
      cbok: [ ethics(1), expectations(1), teamwork(2), understanding(1), communication(1), programming(2), fundamentals(2) ],
      sfia: []
    },

    {
      code: "COSC120",
      name: "Object Oriented Programming",
      prereq: [ "COSC110" ],
      cbok: [ programming(2), problemSolving(1) ],
      sfia: []
    },

    {
      code: "COSC210",
      name: "Database Management Systems",
      prereq: [ "COSC110" ],
      cbok: [ problemSolving(1), societal(1), data(3), networking(2), systems(1), cybersecurity(1) ],
      sfia: []
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
      sfia: []
    },

    {
      code: "COSC230",
      name: "Data Structures & Algorithms",
      prereq: [ "AMTH140", "COSC120", choose(1, "COSC101", "COSC110", "SCI210", "AMTH250") ],
      cbok: [ fundamentals(2), programming(2) ],
      sfia: []
    },

    {
      code: "COSC240",
      name: "Operating Systems",
      prereq: [ "COSC120" ],
      cbok: [ problemSolving(2), societal(1), fundamentals(2), networking(2), programming(2), systems(1), cybersecurity(2) ],
      sfia: []
    },

    {
      code: "COSC250",
      name: "Functional and Reactive Programming",
      prereq: [ "COSC120", choose(1, "COSC101", "COSC110", "SCI210", "AMTH250") ],
      cbok: [ understanding(1), programming(3), systems(2) ],
      sfia: []
    },

    {
      code: "COSC260",
      name: "Web Programming",
      prereq: [ choose(2, "COSC101", "COSC110", "COSC120") ],
      cbok: [ societal(1), networking(2), programming(2), humanFactors(2), systems(2), cybersecurity(2) ],
      sfia: []
    },

    {
      code: "COSC310",
      name: "Software Project Management",
      prereq: [ choose(1, "COSC210", "COSC220") ],
      cbok: [ ethics(2), expectations(2), teamwork(2), communication(3), systems(2), governance(3), projectManagement(3) ],
      sfia: []
    },

    {
      code: "COSC320",
      name: "Information Technology Project",
      prereq: [ "COSC220", "COSC310", cp(96) ],
      cbok: [ problemSolving(3), ethics(2), expectations(2), teamwork(3), communication(3), systems(3), projectManagement(3) ],
      sfia: []
    },

    {
      code: "COSC330",
      name: "Parallel and Distributed Computing",
      prereq: [ or("COSC230", "COSC240") ],
      cbok: [ fundamentals(3), data(3), networking(3), systems(2) ],
      sfia: []
    },

    {
      code: "COSC340",
      name: "Computer Networks and Information Security",
      prereq: [ cp(72), "AMTH140", "COSC240" ],
      cbok: [ societal(1), fundamentals(2), data(2), networking(3), programming(1), cybersecurity(2) ],
      sfia: []
    },

    {
      code: "COSC350",
      name: "Artificial Intelligence",
      prereq: [ "COSC230" ],
      cbok: [ data(3), programming(3) ],
      sfia: []
    },

    {
      code: "COSC360",
      name: "Advanced Web Programming",
      prereq: [ "COSC120", "COSC260" ],
      cbok: [ communication(2), networking(3), programming(3), humanFactors(2), systems(2), serviceManagement(2), cybersecurity(2) ],
      sfia: []
    },

    {
      code: "COSC370",
      name: "User Experience & Interaction Design",
      prereq: [ cp(72), "COSC220" ],
      cbok: [ communication(3), societal(3), humanFactors(3), systems(3) ],
      sfia: []
    },
])