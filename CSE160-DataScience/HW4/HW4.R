#Yang Yi
#09/18/2017
#HW4


#1
#Import data
wine <- read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/wine/wine.data", col.names = c("Class","Alcohol", "Malic.Acid", "Ash", "Alcalinity.of.Ash", "Magnesium", "Total.Phenols", "Flavanoids", "Nonflavanoid.Phenols", "Proanthocyanins", "Color.Intensity", "Hue", "Diluted.Wines.Measure", "Proline"), header = FALSE)

#Delete attributes
wine2 <- wine
wine2$Nonflavanoid.Phenols <- NULL
wine2$Proanthocyanins <- NULL
wine2$Color.Intensity <- NULL
wine2$Hue <- NULL
wine2$Diluted.Wines.Measure <- NULL
wine2$Proline <- NULL

#Calculate attributes
wine2$useless <- wine2$Alcohol * wine2$Flavanoids
wine2$evenMoreUseless <- wine2$Alcohol > 12


#2
#Calculate average
avgMalicAcid <- mean(wine$Malic.Acid)

#print rows less than average
for(i in 1:nrow(wine)) {
  if(!is.na(wine[i,3]) && wine[i,3] < avgMalicAcid) {
    print(wine[i,])
  }
}