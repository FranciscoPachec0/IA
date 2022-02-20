tabela <- read.csv("C:/Users/franc/Desktop/IA/projeto2/speedDating_trab.csv")
head(tabela)

tabela <- na.omit(tabela) 
head(tabela)

tabela$goal<- as.factor(tabela$goal)
levels(tabela$goal) <- gsub("1", "Passar uma noite divertida", levels(tabela$goal))
levels(tabela$goal) <- gsub("2", "Conhecer novas pessoas", levels(tabela$goal))
levels(tabela$goal) <- gsub("3", "Conseguir um encontro", levels(tabela$goal))
levels(tabela$goal) <- gsub("4", "Procurar um relacionamento sério", levels(tabela$goal))
levels(tabela$goal) <- gsub("5", "Dizer que consegui", levels(tabela$goal))
levels(tabela$goal) <- gsub("6", "Outro", levels(tabela$goal))

tabela$match<- as.factor(tabela$match)
levels(tabela$match) <- gsub("0", "Não", levels(tabela$match))
levels(tabela$match) <- gsub("1", "Sim", levels(tabela$match))


#grafico de barras de "Age"
count <- table(tabela$age)
print(count)
barplot(count, ylab="Numero de Pessoas",xlab="Idade", main = "Age")
cat("mediana age = ", median(tabela$age))
sum(tabela$age)
cat("media age = ", mean(x = tabela$age))


#grafico de barras de "Goal"
count <- table(tabela$goal)
print(count)
#barplot(count, main = "Goal")
#barplot(count, names.arg = tabela$goal)
barplot(count,
        col="#69b3a2",
        names.arg=c("Passar uma noite divertida",
                    "Conhecer novas pessoas",
                    "Conseguir um encontro",
                    "Procurar um relacionamento sério",
                    "Dizer que consegui",
                    "Outro"), 
        cex.name = 0.5,
        las=2
       )


#grafico de barras de "Date"
count <- table(tabela$date) 
print(count)
barplot(count, main = "Date")


#grafico de barras de "Go_out"
count <- table(tabela$go_out) 
print(count)
barplot(count, main = "Go_Out")


#grafico de barras de "int_corr"
count <- table(tabela$int_corr) 
print(count)
barplot(count, main = "Correlação de Interesses")
sum(tabela$int_corr)
cat("media int_ocurr = ", mean(x = tabela$int_corr))

#grafico de barras de "length"
count <- table(tabela$length) 
print(count)
barplot(count, main = "Duração")

#grafico de barras de "met"
count <- table(tabela$met) 
print(count)
barplot(count, main = "Conhecia o Parceiro")

#grafico de barras de "like"
count <- table(tabela$like) 
print(count)
barplot(count, main = "Afinidade com o Par")

#grafico de barras de "prob"
count <- table(tabela$prob) 
print(count)
barplot(count, main = "Probabilidade do seu par ter gostado de si")

#grafico de barras de "Match"
count <- table(tabela$match) 
print(count)
barplot(count, main = "Match")
