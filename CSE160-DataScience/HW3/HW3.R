#Yang Yi
#09/11/2017
#HW3

#1
startUp <- data.frame(
  employeeID = c(1501, 1502, 1604, 1503, 1509),
  firstNames = c("Nella", "Madeline", "Abby", "Ben", "Mike"), 
  lastNames = c("Cohen", "Doe", "Gail", "Baker", "Doe"), 
  age = c(35, 27, 22, 31, 23),
  jobLevel = c("boss", "fulltime", "parttime", "fulltime", "parttime"),
  jobTitle = c("CEO", "Programmer", "Secretary", "Business MGR", "Programmer"),
  startDate = c("2015-05-18", "2015-05-29", "2016-01-02", "2015-11-13", "2016-05-29"),
  row.names = "employeeID"
)

startUp$startDate <- as.Date(startUp$startDate)
startUp$jobLevel <- factor(startUp$jobLevel, order=TRUE, levels=c("parttime", "fulltime", "boss"))

#2
yearsYounger <- max(startUp$age) - startUp$age

#3
sum(startUp$jobLevel == 'fulltime')