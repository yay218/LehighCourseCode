library(rpart)


mushroom <- read.csv("http://archive.ics.uci.edu/ml/machine-learning-databases/mushroom/agaricus-lepiota.data", header = F)
colnames(mushroom) <- c("class", "cap-shape","cap-surface","cap-color","bruises", "odor", "gill-attachment", "gill-spacing", "gill-size", "gill-color", "stalk-shape", "stalk-root", "stalk-surface-above-ring", "stalk-surface-below-ring", "stalk-color-above-ring", "stalk-color-below-ring", "veil-type", "veil-color", "ring-number", "ring-type", "spore-print-color", "population", "habitat")

m <- dim(mushroom)[1]
val <- sample(1:m, m/2)

training <- mushroom[val,]
test <- mushroom[-val,]

model <- rpart(class~ ., data = mushroom, method = "class")
training_results <- predict(model, training, type="class")
test_results <- predict(model, test, type="class")

training_tab <- table(data.frame(training$class, training_results))
sum(diag(training_tab))/sum(training_tab)

test_tab <- table(data.frame(test$class, test_results))
sum(diag(test_tab))/sum(test_tab)