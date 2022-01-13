const url = "http://localhost:3001/"

document.getElementById("signup").addEventListener("click", signupfunction);
async function signupfunction() {

    //gather the user inputs from the login inputs
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;
    let firstn = document.getElementById("fname").value;
    let lastn = document.getElementById("lname").value;
    let email = document.getElementById("email").value;
    let role = document.getElementById("role").value;
    let id = document.getElementById("id").value;
    let idfk =1;
    if(role==="EMPLOYEE"){
        idfk=1;
    }
    else if(role==="FINANCE_MANAGER"){
        idfk=2;
    }
    //we want to send the user/pass as JSON, so we need a JS object to send
    let user = {
        username:usern,
        password:userp,
        user_lname:lastn,
        user_fname:firstn,
        user_email:email,
        role_id_fk:idfk,
        id:id
        
    }
    //This object will reflect our DTO in Java... This is the data we want to transfer!

    console.log(user)

    //fetch request to the server
    //remember the second parameter fetch can take? It's essentially for configuring our fetch request
    //fetch sends a GET by default, but this seconds parameter can change that and more!
    let response = await fetch (url + "user", {

        method: "POST", //send a POST request (would be a GET if we didn't do this...)
        body: JSON.stringify(user), //turn our user object into JSON
        credentials: "include"
        //this last line will ensure that the cookie is captured (so that we can have a user session)
        //future fetches will also require this "include" value to send the cookie back
        
    });
    
    console.log(response);
    //control flow based on successful/unsuccessful login
    if(response.status === 200) {
        //wipe our login row and welcome the user 
        
        
        document.getElementById("signupRow").innerText="sign up successful!";
        
        
    } else {
        document.getElementById("signupRow").innerText="Sign up failed! Refresh the page";
    }


}