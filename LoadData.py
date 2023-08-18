#Funcion para crear un "dataset" como una lista de tuplas
# a partir de un archivo plano
#param: la ruta del archivo plano
def LoadData(fileName):
    dataset = []
    with open(fileName, encoding='utf-8') as f:
        for line in f: #Literal por cada renglon del archivo
            values = line.split(sep=";")
            registro = {"nombre":values[0],
                        "direccion":values[1],
                        "hectareas":values[2],
                        "aves":values[3],
                        "flora":values[4],
                        "estado":values[5]}
            dataset.append(registro)
    return dataset