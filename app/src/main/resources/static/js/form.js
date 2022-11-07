function formCheck() {

  let result = document.getElementById("email-error");
  let email = document.getElementById("email").value;

  result.innerHTML = "";

  let re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  if (re.test(email)) {
    result.innerHTML = "입력하신 '" + email + "'은 적합한 이메일 형식입니다.";
    result.style.color = "#0089eb";
  } else {
    result.innerHTML = "입력하신 '" + email + "'은 적합하지 못한 이메일 형식입니다.";
    result.style.color = "#ff4c47";
  }
  return false;
}


document.getElementById('email-error').addEventListener('blur', function () {
  formCheck();
});

// $( "#email3" ).change(function(){
//   $("#email2").val( $("#email3").val() );
// });