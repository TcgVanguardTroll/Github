let buttons = {
    "w": false,
    "a": false,
    "s": false,
    "d": false,
};

/*
Adding Event Handling for the Users Inputs
*/


function buttonPressed(event, buttonCondition) {
    if (event.keyCode === 65 || event.code === "ArrowLeft") {
        newButtonPressed("a", buttonCondition)
    } else if (event.keyCode === 87 || event.code === "ArrowUp") {
        newButtonPressed("w", buttonCondition)
    } else if (event.keyCode === 83 || event.code === "ArrowDown") {
        newButtonPressed("s", buttonCondition)
    } else if (event.keyCode === 68 || event.code === "ArrowRight") {
        newButtonPressed("d", buttonCondition)
    }
}

document.addEventListener("keydown", function (event) {
    buttonPressed(event, true);

});

document.addEventListener("keyup", function (event) {
    buttonPressed(event, false);

});

function newButtonPressed(button, buttonCondition) {
    if (buttons[button] !== buttonCondition) {
        buttons[button] = buttonCondition
        //    Need to Add Socket
    }
}




