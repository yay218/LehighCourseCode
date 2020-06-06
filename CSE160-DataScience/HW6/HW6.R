#Yang Yi
#09/25/2017
#HW6


#Install ggplot2
install.packages("ggplot2")

#Use ggplot2
library("ggplot2")  

#Read the wine data and name the attribute 
wine <- read.table("http://archive.ics.uci.edu/ml/machine-learning-databases/wine/wine.data", col.names = c("Class", "Alcohol", "Malic Acid", "Ash", "Alcalinity of Ash", "Magnesium", "Total Phenols", "Flavanoids", "Nonflavanoid Phenols", "Proanthocyanins", "Color Intensity", "Hue", "Ratio of Dilution", "Proline"), sep=",")

#Factor class column
wine$Class <- factor(wine$Class)

#Scatterplot of Flavanoids vs. Alcohol
qplot(data = wine, x = Flavanoids, y = Alcohol, color = Class) + ggtitle("Flavanoids vs. Alcohol") + theme(plot.title = element_text(hjust = 0.5))

#Save the plot
ggsave("wine_scatterplot.jpg")