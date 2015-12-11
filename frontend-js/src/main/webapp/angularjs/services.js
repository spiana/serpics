function loadjscssfile(filename, filetype){
    if (filetype=="js"){ //if filename is a external JavaScript file
        var fileref=document.createElement('script');
//        fileref.setAttribute("type","text/javascript")
        fileref.setAttribute("src", filename);
    }
    else if (filetype=="css"){ //if filename is an external CSS file
        var fileref=document.createElement("link")
        fileref.setAttribute("rel", "stylesheet")
        fileref.setAttribute("type", "text/css")
        fileref.setAttribute("href", filename)
    }
    if (typeof fileref!="undefined")
        document.getElementsByTagName("head")[0].appendChild(fileref)
};
 
loadjscssfile("angularjs/service.js", "js"); //dynamically load and add this .js file
//loadjscssfile("javascript.php", "js") //dynamically load "javascript.php" as a JavaScript file
//loadjscssfile("mystyle.css", "css") ////dynamically load and add this .css file
loadjscssfile("angularjs/controller.js", "js"); //dynamically load and add this .js file
loadjscssfile("angularjs/serpicsApp.js", "js"); //dynamically load and add this .js file
loadjscssfile("angularjs/directive.js", "js"); //dynamically load and add this .js file
loadjscssfile("angularjs/config.js", "js"); //dynamically load and add this .js file

//<script src=""></script>
//<script src="angularjs/serpicsApp.js"></script>
//<script src="angularjs/directive.js"></script>
//<script src="angularjs/config.js"></script>