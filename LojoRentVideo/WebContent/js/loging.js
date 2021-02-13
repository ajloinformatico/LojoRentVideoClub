//I have used a jquer library



//when the document is loaded
$(document).ready(function (){
    //check form and send by ajax to LoginServlet
    $.fn.login();
    console.log("im ready");
});


//^ indica que el patrón debe iniciar con los caracteres dentro de los corchetes
// [A-Z] indica que los caracteres admitidos son letras del alfabeto
// + indica que los caracteres dentro de los corchetes se pueden repetir
// $ indica que el patrón finaliza con los caracteres que están dentro de los corchetes.
// i indica que validaremos letras mayúsculas y minúsculas (case-insensitive)
/**
 * Check form using RegExp
 */
function check_nick(){
    nick = $('#nick').val();
    //if nick name dont pass pattern show a message
    if(nick.length < 2 || nick.length > 30){
        $('#nick-error').html("Please enter only letters minimum 2, maximum 30")
        $('#nick').val("")
    }else{
        $('#nick-error').html("");
    }
}

/**
 * Check user password
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
 * Go to login
 */
function goSignin(){
    window.location.replace("signin.html");
}



/**
 * ServletLoging
 */
$.fn.login = function (){
    $('#login').click(function(){
       nick = $('#nick').val();
       pass = $('#password').val();
       let flag = true;
       if(nick === ""){
           $('#nick-error').html("this field is required");
           flag = false;
       }
       if(pass === ""){
           $('#password-error').html("this field is required");
           flag = false;
       }
       if(flag){
           $.ajax({
               type: "POST", //Tipo de envío
               dataType: "html", //Tipo de archivo
               url: "./ServletLoging", //Servlet de unión
               data: $.param({ //parametros de envio OBVIAMENTE ESTO HABRÁ QUE HACERLO COGIENDO EL VALOR DE UN FORM
                   nick,
                   pass
               }),
               success: function(result){
                   if(result === "error\n"){
                       $('#error-back').html("User or password not valid");
                   }else{
                       document.cookie = "nick="+result;
                       window.location.replace("index.html");
                   }
               },
               error: function(error){
                   alert("Error:\nSomethig vas wront");
               }
           });
       }

    });
}



