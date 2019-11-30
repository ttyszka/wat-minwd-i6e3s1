from django import forms

STATES = (
    ('', 'Choose...'),
    ('10', '10'),
    ('50', '20'),
    ('100', '100')
)


class WordcloudForm(forms.Form):
    tweets_count = forms.ChoiceField(choices=STATES, label=None)

