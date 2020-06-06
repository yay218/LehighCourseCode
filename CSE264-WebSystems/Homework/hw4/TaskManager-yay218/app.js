var express = require("express");
var bodyParser = require("body-parser");
var path = require("path");

var app = express();

// Using a Javascript object as a hashmap to hold the Person entries
var entries = {};
app.locals.entries = entries;

var nextId = 1;

// Constructor function for a Task object
var Task = function (n, a, b, c) {
	this.id = nextId++;
	this.description = n;
	this.type = a;
        this.type2 = b;
        this.date = c;
}

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true })); // Note: extended:true

app.get("/", function(req, res) {
  // Send the page itself to the browser
  res.sendFile(path.join(__dirname, 'index.html'));
});

app.get("/load", function(req, res) {
  // Send the current list of Persons back to the client page.
  res.send(entries);
});

app.post("/add", function(req, res) {
  // Create a new Person object from the name and age entered into the form on the client
  var newTask = new Task(req.body.description, req.body.type, req.body.type2, req.body.date);
  // Use the id field as a key to the object/hashmap
  entries[newTask.id] = newTask;
  // Send the updated list of Persons back to the client page.
  res.send(entries);
});

app.post("/delete", function(req, res) {
  var xlist = req.body.xlist;
  // For each id on the list, delete it's Person from the table
  xlist.forEach( (ele) => {delete entries[ele];} );
  // Send the remaining list of persons back to the client page 
  res.send(entries);
});

// Catch all middleware for requests that don't match an acceptable pattern
app.use(function(req, res) {
  res.status(404).render("404");
});

// Fire up the server
app.listen(3000, function() {
  console.log("Express app started on port 3000.");
});