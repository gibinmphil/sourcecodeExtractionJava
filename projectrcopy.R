library("FSelector")
library("e1071")


d <- read.csv("C:\\Users\\iiitmk\\workspacejee\\SourceExtractionv4\\src\\dataset.csv")
na.omit(d)

d1 <- read.csv("C:\\Users\\iiitmk\\workspacejee\\SourceExtractionv4\\src\\dataset1.csv")
#print(dataset)
d
ig <- information.gain(types~.,d)
ig

nb <- naiveBayes(as.factor(types)~. , data=d)

predict(nb,d1)
#install.packages("MASS")
#chisq.test(d)

