import pandas
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score
from sklearn.metrics import confusion_matrix
from sklearn.tree import DecisionTreeClassifier

tabela = pandas.read_csv('C:/Users/franc/Desktop/IA/projeto2/speedDating_trab.csv')
tabela = tabela.dropna(how='any', axis=0)
#print(tabela.head(20))
X = tabela.copy()
del X ['match']
del X ['id']
del X ['partner']
y = tabela[['match']].copy()
X_train, X_test, y_train, y_test = train_test_split(X, y, train_size = 0.75, test_size = 0.25, random_state = 90)
#ver esta funcao de teste acima
y_pred = GaussianNB().fit(X_train, y_train.values.ravel()).predict(X_test)
#print(X.head(20))
print("Percentagem de Acertos Gauss = ", accuracy_score(y_test,y_pred)*100)
#print(accuracy_score(y_test,y_pred)*100)
print(confusion_matrix(y_test,y_pred))
decisao = DecisionTreeClassifier()
decisao.fit(X_train,y_train)
y_pred = decisao.predict(X_test)
print("Percentagem de Acertos ID3 = ", accuracy_score(y_test,y_pred)*100)
#print(accuracy_score(y_test,y_pred)*100)
print(confusion_matrix(y_test,y_pred)) 
