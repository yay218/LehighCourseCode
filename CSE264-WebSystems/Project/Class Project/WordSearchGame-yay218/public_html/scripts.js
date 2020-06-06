/*
CSE264 Class Project - Word Search Game
Yang Yi
yay218
04/27/2017
 */

var HOST = "cse264.info:3000";
//var HOST = "localhost:3000";
var SERVER = "http://" + HOST + "/wordsearch/";

var okToLoad = true;


function loadGrid(grid) {    
    var table = document.getElementById("grid");
    var rowCurrent;
    var colCurrent;
    var cell;
    var list = "";
    
    for (var i = 0; i < grid.words.length; i++) {
        list += "<li>"+grid.words[i].text+"</li>";
        for (var j = 0; j < grid.words[i].letters.length; j++) {
            rowCurrent = grid.words[i].letters[j].r;
            colCurrent = grid.words[i].letters[j].c;
            //console.log(rowCurrent);
            //console.log(colCurrent);
            cell = table.rows[rowCurrent].cells[colCurrent];
            cell.style.background = "pink";
        }
    }
    $("#text").html(list);
}

// loadStatus reloads the leader board from the list of players
function loadStatus(players) {
    $("#grid td").click(clickCard);
    var usergrid = "";
    for (var i = 0; i < players.length; ++i) {
        var player = players[i];
        var row = "<tr style='background-color:" + (player.winner ? "gold" : "white") + "'>" +
                "<td>" + player.name + "</td>" +
                "<td>" + player.score + "</td>" +
                "</tr>";
        usergrid += row;
    }
    $("#leaderboard tbody").html(usergrid);
}

function nullFcn(result) {
}
// Utility method for encapsulating the jQuery Ajax Call
function doAjaxCall(method, cmd, params, fcn) {
    $.ajax(
            SERVER + cmd,
            {
                type: method,
                processData: true,
                data: params,
                dataType: "json",
                success: function (result) {
                    fcn(result);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error: " + jqXHR.responseText);
                    alert("Error: " + textStatus);
                    alert("Error: " + errorThrown);
                }
            }
    );
}



var myid = 0;

function login(loginname) {
    doAjaxCall("GET", "login", {username: loginname},
    function (result) {
        myid = result.id;
        $("#lname").html(result.username);
    });
}


// Retrieve the user's login name from the server
function loadName() {
    doAjaxCall("GET", "username", {id: myid},
    function (result) {
        $("#lname").html(result);
    });
}

function getPuzzle () {
    doAjaxCall("GET", "puzzle", {id: myid},
    function (result) {
        var row = result.nrows;
        var column = result.ncols;
        var theme = result.theme;
        var grid = result.grid;
        
        inputTheme(theme);
        inputTable(row, column, grid);
    });
}


function inputTheme(theme) {
    $("#theme").empty();
    var a = $("#theme");
    a.append(theme);
}

function inputTable(row, column, grid) {
    $("#grid").empty();
    for (var i = 0; i < row; i++) {
        var newRow = $("<tr></tr>");
        for (var j = 0; j < column; j++) {
            var newCell = $("<td></td>");
            $(newCell).html('');
            $(newCell).append(grid[i * column + j]);
            $(newRow).append(newCell);
        }
        $("#grid").append(newRow);
    }
}

//var lst = new Array();

function submitWord (list) {
    console.log(list);
    doAjaxCall("GET", "submit", {id: myid, letters: list}, 
    function (result) {
        console.log(result);
   
        $("#grid td.selected").each(function () {
            $(this).css("color", "black");
            $(this).removeClass("selected");
        });
    });
}

function clickCard (evt) {
    if ($(this).hasClass("selected")) {
        $(this).removeClass("selected");
        $(this).css("color", "black");
    } 
    else {
        $(this).addClass("selected");
        $(this).css("color", "red");
    }
}


/*var SubObj = function(letters) {
    this.id = myid;
    this.letters = letters;
};*/


var Word = function(r, c) {
    this.r = r;
    this.c = c;
}

// Attach an event handler to each button on the page
function attachEventHandlers() {
    $("#login").click(function () {
        login($("#username").val());
        $("#username").val("");

    });
    $("#puzzle").click(function () {
        getPuzzle();
    });
    $("#submit").click(function () {
        var r;
        var c;
        var lst = new Array();
        $("#grid td.selected").each(function () {
            r = $(this).parent('tr').prevAll().length;
            c = $(this).prevAll().length;
            console.log(r);
            console.log(c);
            //var w = new Word(r,c);
            //lst.push(w);
            lst.push({r:r,c:c});
            //lst.push(id);
        });
        submitWord(lst);
        
    });
}

$( () => {
    attachEventHandlers();
    $("#grid td").click(clickCard);
    var socket = io.connect(HOST);
    
    // Update the grid when a new word is found from the server
    socket.on('gridupdates', function (grid) {
        console.log(grid);
        loadGrid(grid);
    });
    
    // Update the leader board when an updated player list arrives from the server
    socket.on('players', function (players) {
        loadStatus(players);
    });
});