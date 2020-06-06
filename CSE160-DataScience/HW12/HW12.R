#Yang Yi
#12/05/2017
#HW12

library(maptools)
library(ggplot2)
library(ggmap)


# read administrative boundaries                              
setwd("/Users/yiyang/Desktop/NY_counties_clip")
myMap <- readShapePoly(fn="NY_counties_clip.shp")
# extract sequence of points and assign IDs to states
myMapDf <- fortify(myMap)

library(foreign) # needed for read.dbf()
db <- read.dbf("NY_counties_clip.dbf")
db$rn <- as.integer(rownames(db))
db

# ggplot mapping, limited to area around USA
# data layer (empty map)
m0 <- ggplot(data=myMapDf) + xlim(-81,-72) + ylim(40,46)
# empty map + borders 
m1 <- m0 + geom_path(aes(x=long, y=lat, group=group), color='gray')
m1 + geom_polygon(aes(x=long, y=lat, group=group))

# interpret as integer
myMapDf$id3 <- as.integer(myMapDf$id)
m0 <- ggplot(data=myMapDf) + xlim(-81,-72)+ylim(40,46)
m1 <- m0 + geom_path(aes(x=long, y=lat, group=group), color='gray')
m2 <- m1 + geom_polygon(aes(x=long, y=lat, group=group, fill=id3))
#m2

# inverse order (to have visible borders)
m0 <- ggplot(data=myMapDf) + xlim(-81,-72) + ylim(40,46)
m1 <- m0 + geom_polygon(aes(x=long, y=lat, group=group, fill=id3))
m2 <- m1 + geom_path(aes(x=long, y=lat, group=group), color='black')
#m2

# over a GoogleMap
map <- get_map(location = 'New York State', zoom=5)
m0 <- ggmap(map) + xlim(-81,-72) + ylim(40,46)
m1 <- m0 + geom_polygon(aes(x=long, y=lat, group=group, fill=id3), data=myMapDf,alpha=.7)
m2 <- m1 + geom_path(aes(x=long, y=lat, group=group), data=myMapDf, color='black')
#m2

# read downloaded data
income <- read.csv("/Users/yiyang/Desktop/nyincome2.csv", header=TRUE)
db2 <- merge(db, income, by.x="NAME", by.y="County")
db2$Median_household_income<-as.numeric(db2$Median_household_income)
myMapAugDf <- merge(myMapDf, db2, by.x="id3", by.y="rn")

# merge map and data
map <- get_map(location = 'New York State', zoom=5)
m0 <- ggmap(map) + xlim(-81,-72) + ylim(40,46)
m1 <- m0 + geom_polygon(aes(x=long, y=lat, group=group, fill=Median_household_income),data=myMapAugDf, alpha=.7) + scale_fill_gradient(low="red", high="blue")
m2 <- m1 + geom_path(aes(x=long, y=lat, group=group), data=myMapDf,color='black')
#m2

# add text
library(doBy)
txtVal <- summaryBy(long + lat ~ id3, data=myMapDf, FUN=mean, keep.names=T)
m3 <- m2 + geom_text(aes(x=long, y=lat, label=db[id3+1,"NAME"]), data=txtVal, col="black", cex=2)
m3 + ggtitle("Median NY County Incomes") + xlab("longitude") + ylab("latitude")

