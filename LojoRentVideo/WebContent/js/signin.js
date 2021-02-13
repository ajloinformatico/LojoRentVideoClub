//when the document is loaded
$(document).ready(function (){
    //check form and send by ajax to LoginServlet
    $.fn.Signin();
    console.log("im ready");
});


/**
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
 * Go login
 */
function goLogin(){
    window.location.replace("login.html");
}



$.fn.Signin = function (){
    $('#Signin').click(function (){
        debugger;
        $('#error-back').html("");

        nick = $('#nick').val();
        pass = $('#password').val();
        name = $('#name').val();
        surname = $('#surname').val();
        mail = $('#mail').val();

        if(nick !== "" && pass !== "" && name !== "" && surname !== ""
            && mail !== "") {

            //Ask to sign in
            if (confirm("Are you shure you want to sign in as " + name + " ?")) {
                $.ajax({
                    type: "POST", //Tipo de envío
                    dataType: "html", //Tipo de archivo
                    url: "./ServletSign", //Servlet de unión
                    data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
                        nick,
                        pass,
                        name,
                        surname,
                        mail
                    }),
                    success: function (result) {
                        if (result === "error\n") {
                            alert("nick or mail is actually in use");
                        } else {
                            document.cookie = "nick="+result;
                            window.location.replace("index.html");
                        }
                    },
                    error: function (error) {
                        alert("Error:\nSomething was wrong")
                    }
                });
            }

        }else{
            $('#error-back').html("Please check the fields");
        }
    });
}
