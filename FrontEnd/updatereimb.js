const url = "http://localhost:3001/"
//import {data2} from './krustyKrab.js'
document.getElementById("update").addEventListener("click", updatefunction);
async function updatefunction() {

    //gather the user inputs from the login inputs
    let rid = document.getElementById("reimbid").value;
    let resl = document.getElementById("yourid").value;
    let stat = document.getElementById("status").value;
    let statnum=2;
    if(stat==="Denied"){
        statnum=3;
    }
    else if(stat ==="Approved"){
        statnum=1;
    }
    console.log(resl);
    //we want to send the user/pass as JSON, so we need a JS object to send
    let reimb = {
        rid:rid,
        r_status_fk : statnum,
        resl_id : resl,
    }
    //This object will reflect our DTO in Java... This is the data we want to transfer!

    console.log(reimb)

    //fetch request to the server
    //remember the second parameter fetch can take? It's essentially for configuring our fetch request
    //fetch sends a GET by default, but this seconds parameter can change that and more!
    let responseupdate = await fetch (url + "reimbupdate", {

        method: "POST", //send a POST request (would be a GET if we didn't do this...)
        body: JSON.stringify(reimb), //turn our user object into JSON
        credentials: "include"
        //this last line will ensure that the cookie is captured (so that we can have a user session)
        //future fetches will also require this "include" value to send the cookie back
        
    });
    
    console.log(responseupdate);
    //control flow based on successful/unsuccessful login
    if(responseupdate.status === 200) {
        //wipe our login row and welcome the user 
        
        
        document.getElementById("updatediv").innerText="update successful!";
        
        
    } else {
        document.getElementById("updatediv").innerText="update failed! Refresh the page";
    }


}