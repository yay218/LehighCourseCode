#Yang Yi
#09/10/2017
#HW2

#2
female<-subset(cats,Sex=="F")
min(female$Hwt)

#3
seq(-1.5, 16.5, by = 2)

#4
schedule<-c("MWF10:10-11:00am", "MWF1:10-2:00pm")
names(schedule)<-c("CSE160","CSE406")

#5
schedule["CSE160"]

#6
names(schedule)[names(schedule)!="CSE160"]
