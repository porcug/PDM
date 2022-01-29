from flask import Flask, render_template, request, jsonify


from GlobalVariabiles import GlobalVars, db


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] =""

db.init_app(app)
@app.route("/login", methods=["POST"])
def post_login():
    request.json
  #  print(json)

app.run(host="0.0.0.0",port=8080, threaded =True,debug=True,ssl_context='adhoc')
