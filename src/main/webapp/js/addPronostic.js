function outHiddenPronosticResult(btn,id) {

    const pronosticResultDiv = document.getElementById(id).getElementsByClassName("pronosticResult")[0];
    if(btn.textContent==="Hide"){
        btn.textContent="Display";
        pronosticResultDiv.style.display = 'none';

    }else{
        btn.textContent="Hide";
        pronosticResultDiv.style.display = 'initial';
    }
}

function outHiddenOtherMatch(btn, id){
    const pronosticResultDiv = document.getElementById(id).getElementsByClassName("othersMatch")[0];
    if(btn.textContent==="Hide"){
        btn.textContent="Display";
        pronosticResultDiv.style.display = 'none';

    }else{
        btn.textContent="Hide";
        pronosticResultDiv.style.display = 'initial';
    }
}