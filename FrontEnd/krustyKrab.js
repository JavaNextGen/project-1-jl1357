const url = "http://localhost:3001/" //putting our base URL in a variable for cleaner code below
//eventually, we'll use this in our fetch requests and make calls to our server by appending endpoints

//add eventListeners to our buttons to give them functionality

document.getElementById("loginButton").addEventListener("click", loginFunction);


//remember, async returns a promise (which fetch request return)
async function getAllReimb() {

    //we will send a fetch request to get our employee data
    //by default, fetch requests send GET requests
    //let response = await fetch(url + "employee");
    let response = await fetch(url + "reimball");
    //logging the response in the console just to see the response object
    console.log(response);

    //control flow for is the request is successful
    if(response.status === 200){

        let data = await response.json(); //parse the JSON data from the response into a JS object

        //logging the actual employee data that has been parsed from JSON -> JS
        console.log(data);

        //For every Employee object we got back (stored in the data variable), put it in the table
        for(let Reimbursement of data){

            //create a table row
            let row = document.createElement("tr");

            //create a data cell for each employee field
            let cell = document.createElement("td");
            //fill the cell with the appropriate employee data
            cell.innerHTML = Reimbursement.id;
            //add the td element (data cell) to the tr element (table row)
            row.appendChild(cell);

            //we'll do this^ for every column in employees

            let cell2 = document.createElement("td");
            cell2.innerHTML = Reimbursement.amount;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = Reimbursement.author.user_lname;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = Reimbursement.author.user_fname;
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            if(Reimbursement.ers_reimb_type_fk==1){
                cell5.innerHTML="lodging";
            }
            else if(Reimbursement.ers_reimb_type_fk==2){
                cell5.innerHTML="travel";
            }
            else if(Reimbursement.ers_reimb_type_fk==3){
                cell5.innerHTML="food";
            }
            else if(Reimbursement.ers_reimb_type_fk==4){
                cell5.innerHTML="other";
            }
           
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            cell6.innerHTML = Reimbursement.status;
            row.appendChild(cell6);

            //append the tr called row that we created in the for loop to the table body
            //a new row will be appended for every employee object that comes in
            document.getElementById("employeeBody").appendChild(row);
        }

    }


}

//this function will send the user-inputted login credentials to our server
async function loginFunction() {

    //gather the user inputs from the login inputs
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;
    
    //we want to send the user/pass as JSON, so we need a JS object to send
    let user = {
        username:usern,
        password:userp
    }
    //This object will reflect our DTO in Java... This is the data we want to transfer!

    console.log(user)

    //fetch request to the server
    //remember the second parameter fetch can take? It's essentially for configuring our fetch request
    //fetch sends a GET by default, but this seconds parameter can change that and more!
    let response = await fetch (url + "login", {

        method: "POST", //send a POST request (would be a GET if we didn't do this...)
        body: JSON.stringify(user), //turn our user object into JSON
        credentials: "include"
        //this last line will ensure that the cookie is captured (so that we can have a user session)
        //future fetches will also require this "include" value to send the cookie back
        
    });
    

    //control flow based on successful/unsuccessful login
    if(response.status === 202) {
        //wipe our login row and welcome the user 
        
        let response2 = await fetch(url + "username/"+ usern);
        let data2 = await response2.json();
        console.log(data2);
        document.getElementById("loginRow").innerText="Welcome!";
        console.log(data2.role);
            if(data2.role==="FINANCE_MANAGER"){
                document.getElementById("getEmployeeButton").addEventListener("click", getAllReimb);
            }
            else{
                document.getElementById("getEmployeeButton").addEventListener("click", getuserReimb);
            }
        
        
        
    } else {
        document.getElementById("loginRow").innerText="Login failed! Refresh the page";
    }


}

