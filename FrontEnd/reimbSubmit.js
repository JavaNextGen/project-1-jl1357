const url = "http://localhost:3001/"
//import {data2} from './krustyKrab.js'
document.getElementById("submit").addEventListener("click", submitfunction);
async function submitfunction() {

    //gather the user inputs from the login inputs
    let rid = document.getElementById("id").value;
    let amount = document.getElementById("amount").value;
    let type = document.getElementById("type").value;
    let auth = document.getElementById("auth").value;
    let desc = document.getElementById("desc").value;
    let typefk;
    if(type==="lodging"){
        typefk=1;
    }
    else if(type==="travel"){
        typefk=2;
    }
    else if(type==="food"){
        typefk=3;
    }
    else if(type==="other"){
        typefk=4;
    }
    
    //we want to send the user/pass as JSON, so we need a JS object to send
    let reimb = {
        id:rid,
        amount:amount,
        ers_reimb_type_fk:typefk,
        ers_reimb_status_fk : 2,
        ers_users_fk_auth : auth,
        reimb_description:desc
    }
    //This object will reflect our DTO in Java... This is the data we want to transfer!

    console.log(reimb)

    //fetch request to the server
    //remember the second parameter fetch can take? It's essentially for configuring our fetch request
    //fetch sends a GET by default, but this seconds parameter can change that and more!
    let response = await fetch (url + "reimbcreate", {

        method: "POST", //send a POST request (would be a GET if we didn't do this...)
        body: JSON.stringify(reimb), //turn our user object into JSON
        credentials: "include"
        //this last line will ensure that the cookie is captured (so that we can have a user session)
        //future fetches will also require this "include" value to send the cookie back
        
    });
    
    console.log(response);
    //control flow based on successful/unsuccessful login
    if(response.status === 200) {
        //wipe our login row and welcome the user 
        
        
        document.getElementById("submitrow").innerText="submit successful!";
        
        
    } else {
        document.getElementById("submitrow").innerText="submitp failed! Refresh the page";
    }


}