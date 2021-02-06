//I have used a jquer library



//when the document is loaded
$(document).ready(function (){
    //to check form on frony
    $.fn.initVal();
    console.log("im ready");

    //to load ajax
    document.addEventListener("submit", function(event){
        $.fn.loadLogin();
    })


})

//assign function to $ .fn.initval ()
$.fn.initVal = function (){
    console.log("beging to valid")

    //JQUERY VALIDATION PLUGING
    //https://jqueryvalidation.org/
    $("#logingform").validate({
        //rules for inputs
        rules:{
            nick: {
                required: true,
                minlength: 3,
                maxlength: 30,
            },
            //The password type didnt work
            password:{
                required: true,
                minlength: 8,
                maxlength: 20,
            }
        },
        //message of errors
        messages:{
            nick:{
                required:  " This field is required",
                minlength: " The field must be composed of at least 3 characters",
                maxlength: " The field must be composed of less than 30 characters",
                text:    " this field does not accept numeric characters"
            },
            password:{
                required:  " This field is required",
                minlength: " Password must be composed of at least 8 characters",
                maxlength: " Password must be composed of less than 30 characters",
            }
        }

    })

}

//Function to load ajax
$.fn.loadLogin = function (){
    console.log("Sending info to JAVA by ayax");
    nick = $('#nick').val();
    password = $("#password").val();
    $.ajax({
        type: "POST",
        dataType: "html",
        url: "./ServletLoging",
        data: $.param({
           nick,
           password
        }),
        success: function (result){
            alert(nick + " " + password +" seding");
        },
        error: function (error){
            alert("Something was wrong");
        }
    });

    /*
    $("#textarea").val("");
    dni =  $("#dni").val();
    nombre = $("#nombre").val();
    edad = $("#edad").val();
    //si hay datos
    if(dni != "" && nombre != "" && edad != ""){
        //Ajax --> enviar a servler insertar
        $.ajax({
            type: "POST", //Tipo de envío
            dataType: "html", //lo que voy a recibir
            url: "./ServletInsertar", //Servlet de unión
            data: $.param({ //parametros de envio los cojo a paritr de la id
                dni,
                nombre,
                edad
            }),//si tiene exito
            success: function(result){
                //Deja los campos vacíos
                $("#textarea").val(result);

            },//si hay un error
            error: function(error){
                alert("Error:\nAlgo ha ido mal durante la insercción");
                $("#textarea").val("");
            }
        });
    }else{
        //si faltan datos muestra error
        alert("Error:\nDebes rellenar todos los campos")
    }
    //siempre deja los campos vacíos
    $("#dni").val("");
    $("#nombre").val("");
    $("#edad").val("");

     */


}




