/* 
 * Final Project
 * Yang Yi
 * yay218
 */



var canvas = document.getElementById("canvas_chessboard");
var context_length=0;
var context;
var margin_space;
var intersection_number; // the number of lines for each side.
var intersection_space; //the length between to lines.
//var my_interval;
var all_stone=[{},{}];
var now_step;



$( () => {

    canvas = document.getElementById("canvas_chessboard");
    context_length=0;
    context = canvas.getContext("2d");
    margin_space = parseInt(canvas.style.marginLeft);
    intersection_number = 15; // the number of lines for each side.
    intersection_space = 0; //the length between to lines.
    //var my_interval;
    all_stone=[{},{}];
    now_step=0;
    
    //init();
    $("#button").click(init);
    attachEventHandlers();

});



function attachEventHandlers() {
    $("body").on("tap",function(event){
        var shift_length = 2*margin_space;
        var x = event.pageX - shift_length;
        var y = event.pageY - shift_length;

        var index_x = Math.round(x/intersection_space);
        var index_y = Math.round(y/intersection_space);
        //alert(index_x+";"+index_y);
        x = index_x * intersection_space;
        y = index_y * intersection_space;

        point = [index_x,index_y];
        if(!((point in all_stone[0])||(point in all_stone[1])))
        {
                var player_ID = (now_step++)%2;
                console.log(x);
                console.log(y);
                Draw_Stone(x, y, player_ID);

                all_stone[player_ID][point]=''; //add new stone 
                //***Judge if a player becomes winner***//
                if(judge_winner(point, all_stone[player_ID])){
                        var player_name = 'White';
                        if (player_ID===0){
                                player_name = 'Black';
                        }
                        // console.log(player_ID + ' Player Wins!!');
                        setTimeout(function(){
                            window.confirm(player_name + ' Player Wins!!');
                            location.reload();

                        },5);
                }
                //*********//
        }
        // else
        // {
        // 	alert('there is already one: ' + Object.keys(all_stone[0]).length + ";" + Object.keys(all_stone[1]).length);
        // }
        //$(this).hide();
    });
}



function init() 
{
    $("#btn").html("");
    Draw_Screen();
}



// Draw the chessboard with 15*15 lines.
function Draw_Screen()
{
        var screen_width = document.body.clientWidth;
        var screen_height = document.body.clientHeight;
        context_length = Math.min(screen_width,screen_height)-4*margin_space;
        canvas.width=context_length;
        canvas.height=context_length;	
        //context.clearRect(0, 0, screen_width, screen_height);
        context.fillStyle = 'Bisque';
        context.fillRect(0, 0, context_length, context_length);
        context.strokeStyle ='black';
        context.lineWidth = 1;
        context.beginPath();
        intersection_space = context_length/(intersection_number-1);
        
        
        for(i=0;i<intersection_number;i++)
        {
                context.moveTo(i*intersection_space, 0);
                context.lineTo(i*intersection_space, context_length);
        }
        for(i=0;i<intersection_number;i++)
        {
                context.moveTo(0, i*intersection_space);
                context.lineTo(context_length, i*intersection_space);
        }
        context.stroke();
}



// Draw a stone on chessboard.
function Draw_Stone(x, y, player_ID)
{
        context.beginPath();
        var circle_r = intersection_space*2/5;
        context.arc(x, y, circle_r, 0, 2 * Math.PI); 

        var color = 'white';//'Ivory';
        if(player_ID === 0)
        {
                color = 'black';
        }

        context.fillStyle = color;
        context.shadowBlur = circle_r;
        context.shadowOffsetX = circle_r/5;
        context.shadowOffsetY = circle_r/5;
        context.shadowColor = 'gray';
        //context.shadowColor = 'black';
        //context.stroke();
        context.fill();

}



function judge_winner(pra_now_point, points_player)
{
    // judege left and right
    var count_left_and_right = 0;
    //left;
    var now_point = pra_now_point.slice(0); 
    while(now_point in points_player){
        count_left_and_right+=1;
        now_point[0]-=1;
    } 
    
    
    //right
    var now_point = pra_now_point.slice(0);
    while(now_point in points_player){
        count_left_and_right+=1;
        now_point[0]+=1;
    }
    if(count_left_and_right>5){
        return true;
    }



    // judege up and down
    var count_up_and_down = 0;
    //up;
    var now_point = pra_now_point.slice(0);
    while(now_point in points_player){
        count_up_and_down+=1;
        now_point[1]-=1;
    }
    
    
    //down
    var now_point = pra_now_point.slice(0);
    while(now_point in points_player){
        count_up_and_down+=1;
        now_point[1]+=1;
    }
    if(count_up_and_down>5){
        return true;
    }



    // judge the diagonal
    var count_diagonal = 0;
    //left up;
    var now_point = pra_now_point.slice(0);
    while(now_point in points_player){
        count_diagonal+=1;
        now_point[0]-=1;
        now_point[1]-=1;
    }
    
    
    //right down
    var now_point = pra_now_point.slice(0);
    while(now_point in points_player){
                count_diagonal+=1;
        now_point[0]+=1;
        now_point[1]+=1;
    }
    if(count_diagonal>5){
        return true;
    }


    // judge the diagonal
    count_diagonal = 0;
    //left up;
    now_point = pra_now_point.slice(0);
    while(now_point in points_player){
        count_diagonal+=1;
        now_point[0]+=1;
        now_point[1]-=1;
    }
    
    
    //right down
    now_point = pra_now_point.slice(0);
    while(now_point in points_player){
        count_diagonal+=1;
        now_point[0]-=1;
        now_point[1]+=1;
    }
    if(count_diagonal>5)
    {
        return true;
    }

    return false;
}

