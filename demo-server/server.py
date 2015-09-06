from flask import Flask
from flask import request
app = Flask(__name__)

@app.route('/', methods=['POST'])
def hello_world():
    print(request.json)
    return 'Thank you!'

if __name__ == '__main__':
    app.run(host='0.0.0.0')
