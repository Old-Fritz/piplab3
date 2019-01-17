var pointsArray = [];

ice.ace.instance('mainForm:paramR').valueChangeListener = function (event) {
    draw();
}

redraw();
document.querySelector("canvas").addEventListener('click', clickCanvas, false);

function redraw() {
    draw();
    setTimeout(redraw, 100);
}

function addPoint(x, y, color)
{
    pointsArray.push({xPoint:x,yPoint:y, color:color});
}

function draw()
{
    let canvas = document.querySelector("canvas");
    canvas.width = 600;
    canvas.height = 600;
    drawBackground(canvas);
    drawFigure(canvas, getR());
    drawPoints(canvas);
    drawAxises(canvas);
}

function drawPoints(canvas)
{
    let rows = document.querySelector("#resultTable>tbody").querySelectorAll("tr");
    for(let i = 0;i<rows.length;i++)
    {
        let columns = rows[i].querySelectorAll("td");
        let x = Number(columns[0].innerText);
        let y = Number(columns[1].innerText);
        let result = columns[3].innerText;
        let color = (result == "Попадание") ? "green" : "red";
        drawPoint(canvas,x,y, color);
    }
}

function drawBackground(canvas) {
    let context = canvas.getContext("2d");
    context.fillStyle = "white";
    context.fillRect(0, 0, canvas.width, canvas.height);
}

function drawPoint(canvas,x,y,color)
{
    let context = canvas.getContext("2d");
    context.fillStyle = color;

    x+=6;
    y=6-y;
    let rPixels = canvas.width/12;

    context.closePath();
    context.fillRect(rPixels*x-3,rPixels*y-3, 6, 6);
}

function drawFigure(canvas, r) {
    let context = canvas.getContext("2d");
    context.fillStyle = "blue";

    let rPixels = canvas.width/12;

    context.fillRect(canvas.width/2, (6-r)*rPixels,rPixels*r,rPixels*r);
    context.arc(canvas.width/2,canvas.height/2,r*rPixels/2,0,Math.PI/2);
    context.lineTo(canvas.width/2,canvas.height/2);
    context.fill();
    context.moveTo(canvas.width/2,canvas.height/2);
    context.lineTo(canvas.width/2-rPixels*r, canvas.height/2);
    context.lineTo(canvas.width/2, canvas.height/2+rPixels*r);
    context.fill();
}

function drawAxises(canvas)
{
    let context = canvas.getContext("2d");
    let fontSize = canvas.width/30;

    context.fillStyle = "black";
    context.fillRect(canvas.width/2-1,0,2,canvas.height);
    context.fillRect(0,canvas.height/2-1,canvas.width,2);

    // X axis
    for(let i = -5;i<6;i++)
    {
        context.font = fontSize+"px serif";
        context.fillRect((i+6)*(canvas.width/12)-1,canvas.height/2-3,2,6);
        context.fillText(i,(i+6)*(canvas.width/12),canvas.height/2+fontSize);
    }
    context.fillText("X",canvas.width-fontSize,canvas.height/2-fontSize);

    // Y axis
    for(let i = -5;i<6;i++)
    {
        context.font = fontSize+"px serif";
        context.fillRect(canvas.width/2-3, (i+6)*(canvas.height/12)-1,6,2);
        if(i!=0)
            context.fillText(-i,canvas.width/2-fontSize,(i+6)*(canvas.height/12));
    }
    context.fillText("Y",canvas.width/2+fontSize,fontSize);
}

function clickCanvas(event) {
    let canvas = document.querySelector("canvas");
    var rect = canvas.getBoundingClientRect();

    let x;
    let y;

    x = event.clientX;
    y = event.clientY;

    x -= rect.left;
    y -= rect.top;

    x = x*12/canvas.width-6;
    y= 6-y*12/canvas.height;

    document.getElementById('mainForm:paramX').value = x;
    document.getElementById('mainForm:hiddenY').value = y;
    document.getElementById('mainForm:checkButton').click();
}

function getR() {
    return ice.ace.instance('mainForm:paramR').getValue();
}

function slideR()
{
    let r = getR();
    document.getElementById('mainForm:sliderR').innerHTML = r;
    document.getElementById('mainForm:hiddenR').value = r;
    draw();
}

function slideY()
{
    let y = ice.ace.instance('mainForm:paramY').getValue();
    document.getElementById('mainForm:sliderY').innerHTML = y;
    document.getElementById('mainForm:hiddenY').value = y;
}

