from LoadData import *

nombreArchivo = input("Que archivo vamos a leer?")
miDataSet =LoadData(nombreArchivo)
print(miDataSet)
#Data tipo diccionario para la informacion de un humedal:
humedal1 = { "nombre":"Lago de las garzas",
           "direccion":"Carrera 127 con calle 16A",
           "hectareas":4.7,
           "especies de aves":149,
           "especies florales":148,
           "estado":"En conservacion"}