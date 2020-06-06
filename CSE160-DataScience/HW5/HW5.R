#Yang Yi
#09/24/2017
#HW5


#1
#import library
library(rpart)

#build decision tree
model <- rpart(Class~ ., data = wine, method = "class")

#calculate and print the accuracy
tab <- table(data.frame(wine$Class, pred = predict(model, type = "class")))
sum(diag(tab))/sum(tab)


#2
#create scatterplot
with(wine, plot(Alcohol, Malic.Acid, type = "n", xlab = "Alcohol", ylab = "Malic.Acid"))
with(wine, points(Alcohol, Malic.Acid))
