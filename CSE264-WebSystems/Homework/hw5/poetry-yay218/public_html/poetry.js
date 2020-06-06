/* 
 * Homework Assignment 5
 * Magnetic Poetry
 * Yang Yi
 * yay218
 * April 15, 2017
 */

var idValue = 0;
var bool_button1 = true;
var bool_button2 = true;
$(document).ready(function() {
    var x;
    var y;
    var button;
    $(document).mousedown(function(event){
        if(event.target !== null && event.target.type === "button" && event.target.id !== "addButton"){
            button = event.target;
            x = event.clientX - $(button).offset().left;
            y = event.clientY - $(button).offset().top;
        }
        
    });

    $(document).mousemove(function(event){
        if(event.target !== null) {
            $(button).offset({top:event.clientY - y, left:event.clientX - x});
            
        }
    });

    $(document).mouseup(function(){
        if(event.target.type === "button") 
        {
            if (event.clientX < 100 && event.clientY < 100){
                button.remove();  
                localStorage.removeItem(event.target.id);
                if(event.target.id==='button1')
                {
                    bool_button1 = false;
                }
                if(event.target.id==='button2')
                {
                    bool_button2 = false;
                }
            }
            else if (event.target.id!=='addButton')
            {
                // console.log(event.target.id);
                // console.log(event.clientX - x);
                // console.log(event.clientY - y);
                // console.log('');
                // console.log(JSON.stringify({'value':event.target.innerHTML, 'top':event.target.getBoundingClientRect().top, 'left':event.target.getBoundingClientRect().left}));
                localStorage.setItem(event.target.id, JSON.stringify({'value':event.target.innerHTML, 'top':event.target.getBoundingClientRect().top, 'left':event.target.getBoundingClientRect().left}));
            }
        }
        localStorage.setItem('bool_button1', bool_button1);
        localStorage.setItem('bool_button2', bool_button2);
        button = null;
    });
});

function add() {
    var addInput= $("form");
    var inputText = $("#inputText");
    if (inputText.val() !== "") {
        addInput.append("<button type = 'button' id = '" + idValue + "'>"+ inputText.val()+"</button>");
        
        var element = document.getElementById(idValue);
        // console.log(JSON.stringify({'value':element.innerHTML, 'top':element.getBoundingClientRect().top, 'left':element.getBoundingClientRect().left}));
        
        localStorage.setItem(idValue, JSON.stringify({'value':element.innerHTML, 'top':element.getBoundingClientRect().top, 'left':element.getBoundingClientRect().left}));
        idValue++;
        localStorage.setItem('idValue', idValue); 
    }
    else {
        alert("please enter some words.");
    }
    inputText.val(null);
}


function store() {
    var addInput= $("form");
    addInput.empty();

    if (localStorage.getItem('bool_button1')===null) 
    {
        addInput.append("<button type = 'button' id = 'button1'>Magnetic</button>");    
        var element = document.getElementById('button1');
        $(element).offset({top:110, left:0});
        localStorage.setItem('button1', JSON.stringify({'value':element.innerHTML, 'top':element.getBoundingClientRect().top, 'left':element.getBoundingClientRect().left}));

        addInput.append("<button type = 'button' id = 'button2'>Poetry</button>");    
        var element = document.getElementById('button2');
        $(element).offset({top:110, left:80});  
        localStorage.setItem('button2', JSON.stringify({'value':element.innerHTML, 'top':element.getBoundingClientRect().top, 'left':element.getBoundingClientRect().left}));
        

        localStorage.setItem('bool_button1', bool_button1);
        localStorage.setItem('bool_button2', bool_button2); 
        localStorage.setItem('idValue', idValue); 

        addInput.empty(); 
    }
    else
    {
        bool_button1 = localStorage['bool_button1'];
        bool_button2 = localStorage['bool_button2'];
        idValue = localStorage['idValue'];
        console.log(idValue);
    }

    for(var i in localStorage)
    {
        var value = JSON.parse(localStorage[i]);
        // console.log(i + '->' + value);
        // console.log(i + 'top->' + value['top']);
        // console.log(i + 'left->' + value['left']);
        // var element = document.getElementById(i);
        // $(element).offset({top:value['top'], left:value['left']});

        if(Number(parseFloat(i))===i)
        {
            addInput.append("<button type = 'button' id = '" + i + "'>"+ value['value']+"</button>");    

            var element = document.getElementById(i);
            $(element).offset({top:value['top'], left:value['left']});
        }

        if(i==='button1' && bool_button1)
        {
            addInput.append("<button type = 'button' id = '" + i + "'>"+ value['value']+"</button>");    
            var element = document.getElementById(i);
            $(element).offset({top:value['top'], left:value['left']});
        }

        if(i==='button2' && bool_button2)
        {
            addInput.append("<button type = 'button' id = '" + i + "'>"+ value['value']+"</button>");    
            var element = document.getElementById(i);
            $(element).offset({top:value['top'], left:value['left']});
        }   
    } 
}


$(function() {
    store();
});