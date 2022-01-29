import string

from server.Database.Product import Product


class Order:
    address:string = ""

    telefon:string = ""
    productlist:Product = []
    coments:string = ""

    def getPrice(self):
        totalprice = 0.0
        if len(self.productlist) == 0:
            return 0.0
        for product in self.productlist :
            totalprice+=product.price()
        return totalprice