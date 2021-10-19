const regExpEmail = /^([a-z\d\.\-_]{6,50})(@)([a-z-]{2,8})(\.)([a-z\-]{2,8})((\.)([a-z]{2,8}))?$/;
const regExpPassword = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{10,20}$/;


const form = document.querySelector('form');
const user_password = document.getElementById('user_password');
const user_email = document.getElementById('user_email');

const error_message = "incorrect login or password";






document.addEventListener("DOMContentLoaded", ()=>{
  form.addEventListener('submit', (event)=>{
    event.preventDefault();
    if(isPasswordEmailValid()){
      form.submit();
    } else {
      addErrorMessage();
    }
  });
});

const isPasswordEmailValid = () => {
  return regExpEmail.test(user_email.value) && regExpPassword.test(user_password.value);
};


const addErrorMessage = () => {
    user_email.nextElementSibling.textContent = error_message;
    user_password.nextElementSibling.textContent = error_message;
}


