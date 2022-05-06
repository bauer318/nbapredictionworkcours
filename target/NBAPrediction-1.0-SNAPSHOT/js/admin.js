function selectUserRole(){
    let bettorInfo = document.getElementsByClassName("clientInfos");
    let userRole = document.getElementById("selectRole");
    let selectedRole = userRole.options[userRole.selectedIndex].value;
    let firstname = document.getElementsByClassName("userFirstName");
    let lastname = document.getElementsByClassName("userLastName");
    let email = document.getElementsByClassName("userEmail");
    if(selectedRole=='bettor'){
        bettorInfo[0].style.display = "initial";
        firstname[0].required = true;
        lastname[0].required = true;
        email[0].required = true;

    }else{
        bettorInfo[0].style.display = "none";
        firstname[0].required = false;
        lastname[0].required = false;
        email[0].required = false;
    }
}

function updateUserData(){
    let userFirstName = document.getElementsByClassName("userfirstname");
    let userLastName = document.getElementsByClassName("userlastname");
    let userEmail = document.getElementsByClassName("useremail");
    let login = document.getElementsByClassName("userlogin");
    let password = document.getElementsByClassName("userpassword");
    userFirstName[0].readOnly = false;
    userLastName[0].readOnly = false;
    userEmail[0].readOnly = false;
    login[0].readOnly = false;
    password[0].readOnly = false;
    userFirstName[0].required = true;
    userLastName[0].required = true;
    userEmail[0].required = true;
    login[0].required = true;
    password[0].required = true;
}