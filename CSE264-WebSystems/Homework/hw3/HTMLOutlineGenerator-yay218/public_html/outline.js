/* 
 * CSE264 Homework 3a
 * Yang Yi
 * yay218
 * 03/01/2017
 */


var s = "<ul><li>HTML<ul>";
$(document).ready(function()
{
    var childJ = $('HTML').children();
    var childD1 = childJ.get(0);
    var childD2 = childJ.get(1);

    traverse(childD1);
    traverse(childD2);
    
    s = s + "</ul></li></ul>";
    $('body').append(s);
    
});

function traverse(c) {
    s = s + "<li>" + c.tagName;
    
    if (c.type !== undefined) {
        s = s + " type(" + c.type + ")";
    }
    if (c.src !== undefined) {
        s = s + " src(" + c.src + ")";
    }
    if (c.innerText !== undefined && c.innerText !== "") {
        s = s + " text(" + c.innerText + ")";
    }
    
    if (jQuery(c).children().length === null || jQuery(c).children().length === 0) {
        s = s + "</li>";
    }
    else {
        s = s + "<ul>";
        var child = jQuery(c).children();
        for (var i = 0; i < child.length; i++) {
            traverse(child[i]);
        }
        s = s + "</ul></li>";
    }
}