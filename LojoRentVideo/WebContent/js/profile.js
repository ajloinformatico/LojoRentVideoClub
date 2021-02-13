$(document).ready(function (){
    /*
    * Get user profile cookie
    */
    $.fn.getCookie();


    //Calls main methods to load movie table and user form
    //When they load, they call the methods to return movie,
    //update user, make user premium and delete user
    $.fn.listUserFilms();
    $.fn.loadFormUser();

})

/**
 *
 *TODO: REFACTOR THIS FUNCTION TO UNIT JS*/
$.fn.getCookie = function (){
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
 * Log out by deleting cookie and loading login
 * TODO: REFACTOR THIS FUNCTION TO UNIT JS
 */
const logout = () => {
    document.cookie = "nick=''";
    window.location.replace("login.html");
}


/**
 * Get and return user name located in h3 target by id attribute
 * @returns {string}: User name
 */
const getUserName = () => {
    let name = document.cookie.split(";")[0]; //Get first possition
    return (name.split("=")[1]); //get only name after =
}


/**
 * laod index page
 */
const backHome = () => {
    window.location.replace("index.html");
}


/**
 * Function to generate user table
 * @param result
 */
const createTableFilms = result => {
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
            + "<td><span id='"+final[i].cod_film+"'  class='fas fa-undo-alt fa-2x'></span></td><tr>"
    }
    return result;
}


/**
 * Load user form
 */
$.fn.loadFormUser = () => {
    $.ajax({
        type: "POST",
        dataType: "html",
        url: "./UserLoadFormUpdate",
        data: $.param({
            nick : getUserName()
        }),
        success: function (result) {
            //Set User Data as JSON format
            let userJson = JSON.parse(result);
            console.log(userJson);
            userJson = userJson[0];
            console.log(userJson);
            //User saldo
            $("#user-monoey").html(userJson.saldo + " €");



            //depending on whether it is a premium client or not,
            // it shows the style of the button
            $('#premium-info').removeAttr('class').attr('class', '');
            if(userJson.cliente_premium === true){
                $('#premium-info').html("Premium");
                $('#premium-info').addClass("premium");
            }else{
                $('#premium-info').html("No premium");
                $('#premium-info').addClass("nopremium");

            }
            //$("#user-premium").html(userJson.premium);

            //user data form
            $('#nick').val(userJson.nick);
            $('#password').html(userJson.password);
            $('#name').val(userJson.name);
            $('#surname').val(userJson.surname);
            $('#saldo').val(userJson.saldo);
            $('#mail').val(userJson.mail);

            //
            $.fn.updateUser();
            $.fn.changePremium();
            $.fn.delete_user();

        }
    })
}


/**
 * Delete an user
 */
$.fn.delete_user = () =>{
    $('#delete_user').click(function(){
       $.ajax({
           type: "POST",
           dataType: "html",
           url: "./DeleteUser",
           data: $.param({
               nick : getUserName()
           }),
           success: function (result){
               if(result === "error\n"){
                   alert("Error:\nSomething was wrong");
               }else{
                   //delete cookie and load login
                   logout();
               }
           },
           error: function (){
               alert("Error:\nSomething was wrong");
           }
       });
    });
}



/**
 * Update premium
 */
$.fn.changePremium = () => {
    $('#premium-info').click(function () {
        //First ask User if he want to delete his user
        if (confirm("Are you shure you want delete your acount?")){
            $.ajax({
                type: "POST",
                dataType: "html",
                url: "./ServletChangePremium",
                data: $.param({
                    nick: getUserName()
                }),
                success: function (result) {
                    //Reload loadFromForm
                    if (result === "error\n") {
                        alert("Error:\nSomething was wrong");
                    } else {
                        $.fn.loadFormUser();
                    }
                },
                error: function () {
                    alert("Error:\nSomething was wrong");
                }
            });
        }
    });
}




/* *
 * Check user nick
 */
function check_nick(){
    nick = $('#nick').val();
    //if nick name dont pass pattern show a message
    if(nick.length < 2 || nick.length > 30){
        $('#nick-error').html("Please enter inputs with minimum 2, maximum 30")
        $('#nick').val("")
    }else{
        $('#nick-error').html("");
    }
}


/**
 * Check user pass
 */
function check_pass(){
    pass = $('#password').val();
    if(pass.length < 8 || pass.length > 20){
        $('#password-error').html("Please enter password with minimum 8 and maximum 20");
        $('#password').val("")
    }else{
        $('#password-error').html('');
    }
}

/**
 * Check user Name
 */
function check_name(){
    name = $('#name').val();
    const pattern = new RegExp('^[A-ZÁÉÍÓÚÑ ]+$', 'i');
    if(name.length < 2 || name.length > 60 || (!pattern.test(name))){
        $('#name-error').html('Please enter only letters minimum 2, maximum 60')
        $('#name').val('');
    }else{
        $('#name-error').html('');
    }
}

/**
 * Check userName input
 */
function check_surname(){
    name = $('#surname').val();
    const pattern = new RegExp('^[A-ZÁÉÍÓÚÑ ]+$', 'i');
    if(name.length < 2 || name.length > 60 || (!pattern.test(name))){
        $('#surname-error').html('Please enter only letters minimum 2, maximum 60')
        $('#surname').val('');
    }else{
        $('#surname-error').html('');
    }
}


/**
 * check mail
 */
function check_mail(){
    email = $('#mail').val();
    const pattern = new RegExp(/^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i);
    if(email.length < 2 || email.length > 60 || (!pattern.test(email))){
        $('#mail-error').html("Please enter a real mail with maximum length of 60")
        $('#mail').val('');
    }else{
        $('#mail-error').html('')
    }
}

/**
 * Check saldo
 */
const check_saldo = () => {
    saldo = $('#saldo').val();
    const pattern = new RegExp(/^[0-9]+$/);
    if(!pattern.test(saldo)){
        $('#error-saldo').html('Saldo must be a number');
        $('#saldo').val('');
    }else if(saldo.length < 2 || saldo.length > 4){
        $('#error-saldo').html('Enter a minimum balance of 10 and a maximum of 9999');
        $('#saldo').val('');
    }else{
        //pass all conditions
        $('#error-saldo').html('');
    }
}


/*Update user info*/
$.fn.updateUser = () => {
    $('#update').click(function (){
        nick = $('#nick').val();
        pass = $('#password').val();
        name = $('#name').val();
        surname = $('#surname').val();
        mail = $('#mail').val();
        saldo = $('#saldo').val();

        if(nick !== "" && pass !== "" && name !== "" && surname !== ""
            && mail !== "" && saldo !== "") {
            if(confirm("Are you shure you want to update "+nick+" ?")){
                $.ajax({
                    type: "POST",
                    dataType: "html",
                    url: "./ServletUserUpdateForm",
                    data: $.param({
                        oldNick : getUserName(),
                        nick,
                        pass,
                        name,
                        surname,
                        mail,
                        saldo
                    }),
                    success: function (result) {
                        if (result === "error\n"){
                            $('#error-user-form').html('Please check the fields\n' +
                                'There is already a user with this nick in the system');
                        }else{
                            //update cookie with the new user

                            alert('user update correctly');
                            document.cookie = "nick="+result;
                            $('#user-name').html(result);

                            $.fn.loadFormUser();
                        }

                    }
                })
            }
        }else{
            $('#error-user-form').html('Please check the fields');
        }
    });
}





/*List all user films*/
$.fn.listUserFilms = function (){
    $.ajax({
        type: "POST",
        dataType: "html",
        url: "./ListUserFilms",
        data: $.param({
            nick: getUserName()
        }),
        success: function (result) {
            //User have the film
            if(result === "error\n") {
                $('#error-back').html("<p>you haven't rented any movie yet</p>");
            }else{
                $('#tbody').html(createTableFilms(result));
                $.fn.returnClick(); //function to return a film
            }
            //$.fn.returnFilm
        },
        error: function (){
            $("#error-back").html("<p>No movies availables</p>");
        }
    });
}


/*Function that trigger before load films user
* to load servlet returnFilm*/
$.fn.returnClick = () => {
    $('td>span').click(function (){
        const cod_film = $(this).attr('id');
        const nick = getUserName();
        $.ajax({
            type: "POST",
            dataType: "html",
            url: "./ServletreturnFilm",
            data: $.param({
                nick : nick,
                cod_film : cod_film
            }),
            success: function (result){
                if(result === "error\n"){
                    alert("Error:\nSomething was wrong")
                }else{
                    $.fn.listUserFilms();
                }
            }
        })
    });
}









