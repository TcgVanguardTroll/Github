from flask import Flask, render_template, request

import Controller.Server.PlayerName
from Controller.Server.forms import RegistrationForm

app = Flask(__name__)
app.config['SECRET_KEY'] = '0dc0d96f4f0c7ff20dda6e0c0698dfe2'

list = ["Ant", "Ahmed", "Chris", "Jordan"]


@app.route("/")
def hello():
    return render_template('index.html', list=list)


@app.route("/about")
def about():
    return render_template('about.html', title='About')


@app.route("/register")
def register():
    form = RegistrationForm()
    return render_template('register.html', title='Register', form=form)


@app.route("/form", methods=['POST', 'GET'])
def form():
    if request.method == 'POST':
        name1 = request.form.get('name')
        Controller.Server.PlayerName.funct(name1)

        return '<h1>{} HAS BEEN ADDED TO THE GAME!</h1>'.format(name1)

    return '''<form method="POST">
    USERNAME <input type="text" name="name"
    <input type="submit">
    </form>
    '''


@app.route('/json', methods=['POST'])  # GET requests will be blocked
def json_example():
    req_data = request.get_json()
    names = req_data['player']
    return '''<h1>
           The names are: {}
           </h1>
          '''.format(names)


if __name__ == "__main__":
    app.run()
