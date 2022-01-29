import mysql.connector

class database:
    __db__= None
    def __init__(self):
        self.__db__ =mysql.connector.connect(user='root', password='root',
                              host='127.0.0.1',
                              database='employees')
        self.__db__.close()

c= database()
