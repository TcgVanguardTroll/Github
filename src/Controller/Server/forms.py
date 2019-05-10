from flask_wtf import FlaskForm
from wtforms import StringField,SubmitField
from wtforms.validators import DataRequired,Length


class RegistrationForm(FlaskForm):
    userame = StringField('Username', validators=[DataRequired(), Length(min=2, max=10)])

submit=SubmitField('Sign Up')