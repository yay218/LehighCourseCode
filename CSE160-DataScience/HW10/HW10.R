#Yang Yi
#11/08/2017
#HW10


#Load and install packages and library
#install.packages("e1071")
library(e1071)

#Read data and set column names
cancer_data <- read.table("http://archive.ics.uci.edu/ml/machine-learning-databases/breast-cancer-wisconsin/breast-cancer-wisconsin.data", sep=",", header=F, col.names = c("Sample_code_number", "Clump Thickness", "Uniformity of Cell Size", "Uniformity of Cell Shape", "Marginal Adhesion", "Single Epithelial Cell Size", "Bare Nuclei", "Bland Chromatin","Normal Nucleoli","Mitoses","Class"),na.strings = c("NA"))

#Clean data
cancer_data$Sample_code_number <- NULL
cancer_data$Class <- as.factor(cancer_data$Class)

#Create 10 folds
cancer_data<-cancer_data[sample(nrow(cancer_data)),]
folds <- cut(seq(1,nrow(cancer_data)),breaks=10,labels=FALSE)

#Initialize total values
total_precision = 0
total_recall = 0
total_fmeasure = 0
total_accuracy = 0

#Perform 10 fold cross validation
for (i in 1:10) {
  index<-which(folds==i,arr.ind=T)
  train <- cancer_data[-index,]
  test <- cancer_data[index,]
  i= i + 1
  m <- naiveBayes(train[, -10], train[, 10])
  
  #Create confusion matrix and tp, fp, tn, fn
  table <- table(predict(m, test[, -10]), test[, 10])
  TP <- table[4]
  FP <- table[2]
  TN <- table[1]
  FN <- table[3]
  
  #Calculate accuracy
  precision <- (TP / (TP + FP))
  recall <- (TP / (TP + FN))
  fmeasure <- (2 * precision * recall)/(precision + recall)
  accuracy <- (TP + TN) / (sum(table))
  
  total_precision = total_precision + precision
  total_recall = total_recall + recall
  total_fmeasure = total_fmeasure + fmeasure
  total_accuracy = total_accuracy + accuracy
}

#Print values
cat("For 10 fold cross validation:",
    "\nThe average Precision is", total_precision/10,
    "\nThe Average Recall is", total_recall/10,
    "\nThe Average Fmeasure is", total_fmeasure/10,
    "\nThe Average Accuracy is", total_accuracy/10)