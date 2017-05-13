from flask import Flask, Response
import random, string, json

app = Flask(__name__)

class Product(object):
    def __init__(self, id, name, price, weight):
        self.id = id
        self.name = name
        self.price = price
        self.weight = weight
    def serialize(self):
        return dict(id=self.id, name=self.name, price=self.price, weight=self.weight)

def sample_data():
    data = []
    data.append(Product('prod3568', 'Egg Whisk', 3.99, 150))
    data.append(Product('prod7340', 'Tea Cosy', 5.99, 100))
    data.append(Product('prod8643', 'Spatula', 1, 80))
    return data

def fibonacci(n):
    if n < 2:
        return n
    return fibonacci(n - 2) + fibonacci(n - 1)

def random_string(len):
    return ''.join(random.choice('0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz') for _ in range(len))

@app.after_request
def custom_headers(response):
    response.headers['Connection'] = 'Close'
    return response

@app.route('/data')
def get_data():
    products = sample_data()
    response = json.dumps({'data': [p.serialize() for p in products]})
    return Response(response=response, status=200, mimetype="application/json")

@app.route('/concat')
def get_concat():
    response = json.dumps({'concat': random_string(10000)})
    return Response(response=response, status=200, mimetype="application/json")

@app.route('/fibonacci')
def get_fibonacci():
    response = json.dumps({'fibonacci': fibonacci(30)})
    return Response(response=response, status=200, mimetype="application/json")

from tornado.wsgi import WSGIContainer
from tornado.httpserver import HTTPServer
from tornado.ioloop import IOLoop
http_server = HTTPServer(WSGIContainer(app))
http_server.listen(3000)
IOLoop.instance().start()