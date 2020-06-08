from flask import Flask, request

app = Flask(__name__)
HTTP_METHODS = ['GET', 'HEAD', 'POST', 'PUT', 'DELETE', 'CONNECT', 'OPTIONS', 'TRACE', 'PATCH']

@app.route('/', methods=HTTP_METHODS)
def index():
    return 'Headers:' + '\n'.join([f'{h}: {v}' for h,v in request.headers.items()])

app.run(host='127.0.0.1', port=80, debug=True)