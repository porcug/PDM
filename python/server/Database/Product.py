import string


class Product:
    title:string =""
    def price(self):
        return self._price *self._cantitate
    _price:float = 0.0
    _cantitate:int = 1
    image_Url:string = ""
    description:string = ""
