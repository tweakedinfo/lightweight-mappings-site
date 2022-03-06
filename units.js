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
      code: "AMTH250",
      name: "Introduction to Programming in the Sciences",
      prereq: [ or("MTHS120", "MTHS130")],
      cbok: [ programming(1) ],
      sfia: []
    },

    {
      code: "SCI210",
      name: "Introduction to Scientific Programming",
      prereq: [ choose(1, "MTHS110", "MTHS120", "MTHS130")],
      cbok: [ programming(1) ],
      sfia: []
    },

    {
      code: "MTHS100",
      name: "Introduction to Quantitative Skills",
      prereq: [], cbok: [ ], sfia: []
    },

    {
      code: "MTHS110",
      name: "Quantitative Skills with Applications",
      prereq: [], cbok: [ ], sfia: []
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
      code: "PMTH213",
      name: "Linear Algebra",
      prereq: [ "MTHS120", "MTHS130" ], cbok: [ ], sfia: []
    },

    {
      code: "MM105",
      name: "Communication for Business",
      prereq: [ ], cbok: [ communication(1) ], sfia: []
    },

    {
      code: "MM200",
      name: "Management Practices in Responsible Organisations",
      prereq: [ ], cbok: [ communication(1), teamwork(1) ], sfia: []
    },


    {
      code: "STAT100",
      name: "Introduction to Statistical Modelling",
      prereq: [], cbok: [ data(2) ], sfia: []
    },

    {
      code: "STAT210",
      name: "Statistical Modelling & Experimental Design",
      prereq: [ choose(1, "STAT100", "AMTH250", "SCI210") ],
      cbok: [ data(2) ], sfia: []
    },

    {
      code: "MATH260",
      name: "Probability and Simulation",
      prereq: [ "MTHS120", "MTHS130" ],
      cbok: [ data(3) ], sfia: []
    },

    {
      code: "STAT270",
      name: "Inference",
      prereq: [ "MATH260" ],
      cbok: [ data(3) ], sfia: []
    },

    {
      code: "STAT320",
      name: "Advanced Statistical Modelling",
      prereq: [ "STAT210" ],
      cbok: [ data(3) ], sfia: []
    },

    {
      code: "STAT330",
      name: "Statistical Learning",
      prereq: [ "STAT210" ],
      cbok: [ data(3) ], sfia: []
    },

    {
      code: "SCI501",
      name: "Special Topics in Science A (data science studio 2)",
      prereq: [ ],
      cbok: [ data(3) ], sfia: []
    },

    {
      code: "QM161",
      name: "Business Statistics",
      prereq: [], cbok: [ data(2) ], sfia: []
    },

    {
      code: "QM262",
      name: "Introduction to Business Analytics",
      prereq: [], cbok: [ data(2) ], sfia: []
    },

    {
      code: "ICT100",
      name: "Computational Thinking",
      prereq: [],
      cbok: [ ethics(1), societal(1), humanFactors(1), problemSolving(1), programming(1) ],
      sfia: []
    },

    {
      code: "ICT101",
      name: "From Logic to Data Processing",
      prereq: [],
      cbok: [ fundamentals(1), understanding(1), data(1) ],
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
      cbok: [  programming(2), teamwork(2), communication(2), data(1),problemSolving(1)],
      sfia: []
    },


    {
      code: "COSC110",
      name: "Introduction to Programming and the Unix Environment",
      prereq: [],
      cbok: [ ethics(1), expectations(1), teamwork(1), societal(2), understanding(1), communication(1), programming(2), fundamentals(1) ],
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
      cbok: [ problemSolving(2), societal(1), understanding(2), fundamentals(2), networking(2), programming(3), systems(1), cybersecurity(2) ],
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
      cbok: [ societal(1), communication(3), fundamentals(2), understanding(1), data(2), networking(3), programming(3), cybersecurity(3) ],
      sfia: []
    },

    {
      code: "COSC350",
      name: "Artificial Intelligence",
      prereq: [ "COSC230" ],
      cbok: [ data(3), programming(3), fundamentals(2), systems(2) ],
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

    {
      code: "COSC372",
      name: "Management Information Systems",
      prereq: [ cp(48) ],
      cbok: [ societal(2), data(2), systems(3), projectManagement(1), serviceManagement(2) ],
      sfia: []
    },



    {
      code: "COSC380",
      name: "Algorithms in Machine Learning",
      prereq: [ choose(1, "MTHS120", "MTHS130"), choose(1, "COSC230", "AMTH250", "SCI210", "STAT330") ],
      cbok: [ data(3), programming(2) ],
      sfia: []
    },

])