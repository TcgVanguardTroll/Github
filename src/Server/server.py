from flask import Flask, render_template
from Server.forms import RegistrationForm
app = Flask(__name__)
app.config['SECRET_KEY']='0dc0d96f4f0c7ff20dda6e0c0698dfe2'

list=["Ant","Ahmed","Chris","Jordan"]


@app.route("/")
def hello():
    return render_template('index.html',list=list)


@app.route("/about")
def about():
    return render_template('about.html',title=about)


@app.route("/register")
def register():
    form=RegistrationForm()
    return render_template('register.html',title=title,form=form)


# @app.route("/")
# def hello():
#     return "Hello World"

if __name__ == "__main__":
    app.run()