$(document).ready(function (){

    //onrun check cookie to load user or return to loging
    $.fn.getCookie();

    //Muestra la lista de peliculas
    $.fn.listFilms();




})


/**
 * Check if name cookie exist to load index or return to login
 */
$.fn.getCookie = function (){
    debugger

    let name = document.cookie.split(";")[0];
    name = name.split("=")[1];
    const complete = document.cookie;
    if(name !== "" && name !=="''" && complete !== "" && complete !== "''"){
        $('#user-name').html(name);
    }else{
        //TODO CHANGE IT LATER
        //document.cookie = "nick=Juanito_1998";
        //$('#user-name').html("Juanito_1998");
        window.location.replace("login.html");
    }
}

/**
 * delete a cookie
 */
const logout = () => {
    document.cookie = "nick=''";
    window.location.replace("login.html");
};



/*
* Get user name after it was loaded by a cookie
* TODO: CHECK IF IT RUN
*/
const getUserName = () => {
    let name = document.cookie.split(";")[0]; //Get first possition
    return (name.split("=")[1]); //get only name after =
}


/**
 * load user profile page
 * Modern using od js
 */
const userProfile = () => {
    window.location.replace("profile.html")
};


/**
 * Parse String to JSON
 * @param result
 * @returns {string}: table of films with button to ad rent with id film
 */
function createTableFilms(result) {
    const final = JSON.parse(result);
    result = "";
    //loop as dict
    for (let i = 0; i < final.length; i++) {
        result += "<tr>"
            + "<td>" + final[i].cod_film + "</td>"
            + "<td>" + final[i].name_film + "</td>"
            + "<td>" + final[i].price + "€</td>"
            + "<td>" + final[i].estreno + "</td>"
            + "<td>" + final[i].n_copias + "</td>"
            + "<td>" + final[i].genero + "</td>"
            + "<td><span id='"+final[i].cod_film+"'  class='fas fa-shopping-cart fa-2x'></span></td><tr>"
    }
    return result;
}



/**
 * Servlet IndexFilmsList
 */
$.fn.listFilms = function (){
    $.ajax({
        type: "POST",
        dataType: "html",
        url: './IndexFilmsList',
        success: function (result) {
            if(result === "error\n"){
                $('#error-back').html("<p>No movies available</p>");
            }else {
                console.log(result);
                $('#tbody').html(createTableFilms(result));
                //Click on button to rent
                $.fn.rentClick();
            }
        },
        error: function (){
            $('#error-back').html("<p>No movies available</p>");
        }
    })
}
/**
 * Function for checked button to rent video
 * selector td > span capture the id and load
 * ServletrentFilm to rent a video. After that
 * loas filmList function
 */
$.fn.rentClick = function (){
    $('td > span').click(function(){
        const id = $(this).attr('id');
        const name = getUserName(); //Catch cookie name
        $.ajax({
            type: "POST",
            dataType: "html",
            url: "./ServletrentFilm",
            data: $.param({
                nick: name,
                cod_film: id
            }),
            success: function (result){
                    //if there is an error
                if(result === "haveit\n") {
                    alert('Error\nYou have this film actually rent\n' +
                        'First you must return film on your user area');
                }else if(result === "units\n") {
                    alert('Error\nSorry, there are no units left of this movie');
                }else if(result === "error\n"){
                    alert("Error\nSomething was wrong");
                }else{
                    //if rent succes load data
                    $.fn.listFilms();
                }
            },
            error: function (error) {
                alert("Error\nSomething was wrong");
            }
        })
    });
}