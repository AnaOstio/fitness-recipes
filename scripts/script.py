import csv
import os
import pandas as pd
import random
import numpy as np

# Obtiene la ruta absoluta del directorio actual
#directorio_actual = os.path.dirname(os.path.abspath(__file__))

# Concatena el nombre del archivo CSV a la ruta del directorio actual
# ruta_archivo = os.path.join(directorio_actual, 'food.csv')

# Abre el archivo CSV en modo lectura
# with open(ruta_archivo, 'r') as archivo_csv:
#    lector_csv = csv.reader(archivo_csv)
#    for linea in lector_csv:
#       print(linea)


# Ruta al archivo CSV
directorio_actual = os.path.dirname(os.path.abspath(__file__))
ruta_archivo = os.path.join(directorio_actual, 'food.csv')

# Lee el archivo CSV usando pandas
dataframe = pd.read_csv(ruta_archivo)

# Elimina la columna específica del dataframe
nombre_columna = 'Cleaned_Ingredients'
dataframe = dataframe.drop(nombre_columna, axis=1)

# Añadimos la informacion del tipo de comida
tipoComida = ["Breakfast", "Lunch", "Dinner", "Dessert", "Mid-morning meal"]
tipoRandom = [random.choice(tipoComida) for _ in range(len(dataframe))]

dataframe["TypeMeal"] = tipoRandom

# Añadimos la informacion del Rating
rating = np.arange(0, 5.5, 0.5).tolist()
typeRating = [random.choice(rating) for _ in range(len(dataframe))]

dataframe["Rating"] = typeRating

# Macro Nutrients %
macro = ["carbohydrates", "greases", "protein", "fiber"]

randomPercentage = np.round(np.random.dirichlet(np.ones(4), len(dataframe))*100, decimals=2)

macroSet = [{macro[j]:randomPercentage[i][j] for j in range(len(macro))} for i in range(len(randomPercentage))]

dataframe["macronutrientsPercentages"] = macroSet

# Guarda el dataframe actualizado en un nuevo archivo CSV
ruta_nuevo_archivo = os.path.join(directorio_actual, 'food6.csv')
dataframe.to_csv(ruta_nuevo_archivo, index=False)

print("Columna eliminada y archivo CSV actualizado correctamente.")