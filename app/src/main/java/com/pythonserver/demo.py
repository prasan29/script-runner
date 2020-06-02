
import flask
import werkzeug 

app = flask.Flask(__name__)
# Endpoint named "name" that will simply return "Hello World!" as response when invoked
@app.route('/name', methods=['GET', 'POST'])
def name():
	return "Hello World!"

if __name__ == "__main__":
	# Change port as required
	app.run(host= '0.0.0.0', port=5057, debug=True, threaded=True)
