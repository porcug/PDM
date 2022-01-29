from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy();
def singleton(class_):
    instances = {}
    def getinstance(*args, **kwargs):
        if class_ not in instances:
            instances[class_] = class_(*args, **kwargs)
        return instances[class_]
    return getinstance

@singleton

class GlobalVars:
    def __init__(self):
        pass
    Database= None
