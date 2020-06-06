/* 
 * Yang Yi
 * CSE264 Homework 2
 * 02/20/2017
 */


var stack = [];
var display = '0';
var sign = false;
var temp = "";
var dotSign = false;


document.getElementById("calculator").onclick = function(evt) {
    
    if (evt.target.id === "one") {
        temp = temp + '1';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "two") {
        temp = temp + '2';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "three") {
        temp = temp + '3';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "four") {
        temp = temp + '4';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "five") {
        temp = temp + '5';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "six") {
        temp = temp + '6';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "seven") {
        temp = temp + '7';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "eight") {
        temp = temp + '8';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "nine") {
        temp = temp + '9';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "zero") {
        temp = temp + '0';
        document.getElementById("display").innerHTML = temp;
    }
    else if (evt.target.id === "add") {
        var addNum1 = stack.pop();
        var addNum2 = stack.pop();
        var add = addNum1 + addNum2;
        stack.push(add);
        document.getElementById("display").innerHTML = add;
    }
    else if (evt.target.id === "substract") {
        var substractNum1 = stack.pop();
        var substractNum2 = stack.pop();
        var substract = substractNum1 - substractNum2;
        stack.push(substract);
        document.getElementById("display").innerHTML = substract;
    }
    else if (evt.target.id === "multiply") {
        var multiplyNum1 = stack.pop();
        var multiplyNum2 = stack.pop();
        var multiply = multiplyNum1 * multiplyNum2;
        stack.push(multiply);
        document.getElementById("display").innerHTML = multiply;
    }
    else if (evt.target.id === "divide") {
        var divideNum1 = stack.pop();
        var divideNum2 = stack.pop();
        var divide = divideNum1 / divideNum2;
        stack.push(divide);
        document.getElementById("display").innerHTML = divide;    
    }
    else if (evt.target.id === "push") {
        stack.push(parseFloat(temp));
        temp = "";
        dotSign = false;
        sign = false;
        document.getElementById("display").innerHTML = 0;
    }
    else if (evt.target.id === "changeSign") {
        if (sign === true) {
            temp = temp.substring(1);
            sign = false;
            document.getElementById("display").innerHTML = temp;

        }
        else {
            temp = '-' + temp;
            sign = true;
            document.getElementById("display").innerHTML = temp;
        }
    }
    else if (evt.target.id === "dot") {
        if (dotSign === false) {
            temp = temp + '.';
            dotSign = true;
            document.getElementById("display").innerHTML = temp;
        }
    }
    else if (evt.target.id === "clear") {
        stack = new Array;
        temp = "";
        dotSign = false;
        sign = false;
        document.getElementById("display").innerHTML = 0;
    }
};