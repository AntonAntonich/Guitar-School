const form = document.querySelector('form');
const emptyInputMessage = "This field must be filled";
const emptyPlug = "";

const regExpName = /^[a-z0-9_-]{3,16}$/;
const regExpEmail = /^([a-z\d\.\-_]{6,50})(@)([a-z-]{2,8})(\.)([a-z\-]{2,8})((\.)([a-z]{2,8}))?$/;
const regExpPassword = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{10,20}$/;


const password0 = document.getElementById('password0');
const password1 = document.getElementById('password1');
let isEmailValid = false;
let isPasswordalid= false;
let isRepeatedPasswordValid = false;
let isName = false;





document.addEventListener('DOMContentLoaded', () => {
    "user strict";
    validateAll();
    form.addEventListener('submit', (event) => {
        event.preventDefault();
        ;
        if(checkFieldsNull() && isValid() && isMatch()){
            form.submit();
        }
    });
});


const isValid = () => {
    return isEmailValid && isPasswordalid && isRepeatedPasswordValid && isName;
}


const validateAll = () => {

    for (let element of form.elements) {

        if (element.classList.contains('txt-input')) {
            element.addEventListener('change', () => {
                validateElement(element);

            });

        }
    }
}

const validateElement = (element) => {

    switch (element.name) {
        case 'Email':
            if (validateEmail(element)) {
                isEmailValid = true;
            } else {
                isEmailValid = false;
            }
            break;
        case 'Password':
            if (validatePassword(element)) {
                isPasswordalid = true;
            } else {
                isPasswordalid = false;
            }
            break;
        case 'Password-Repeated':
            if (validatePassword(element)) {
                isRepeatedPasswordValid = true;
            } else {
                isRepeatedPasswordValid = false;
            }
            break;
        case 'Nickname':
            if (validateName(element)) {
                isName = true;
            } else {
                isName = false;
            }
            break;
        default :
            console.log('default');
            break;
    }
};

const isMatch = () => {
    let result = false;
    if (password0.value != password1.value && password1.value != "") {
        elemInvalid(password0);
        elemInvalid(password1);
        password0.nextElementSibling.textContent = "password mismatch";
        password1.nextElementSibling.textContent = "password mismatch";

    } else {
        password0.nextElementSibling.textContent = "";
        password1.nextElementSibling.textContent = "";
        result = true;
    }
    return result;
};


const validateEmail = (element) => {
    let result = false;
    if(regExpEmail.test(element.value)){
        elemValid(element);
        element.nextElementSibling.textContent = "";
        result = true;
    } else if(!regExpEmail.test(element.value) || element.value !=""){
        elemInvalid(element);
        element.nextElementSibling.textContent = 'email should conform to the formate: ' + '\n' +
            '[name](length 6-16: lowercase latin letters  symbols, numbers, ".", "-" or "_")\n [@] [domain name] [.] [suffix]';
    }
    return result;
};


const validatePassword = (element) => {
    let result = false;
    if(regExpPassword.test(element.value)){
        result = true;
        elemValid(element);
        element.nextElementSibling.textContent = "";
    } else {
        elemInvalid(element);
        element.nextElementSibling.textContent = 'password should contain at less one latin uppercase letter, one latin lowercase letter, one of symbols !@#$%^&* one number';
    }

    return result;
};


const validateName = (element) => {
    let result = false;
    if(regExpName.test(element.value)){
        elemValid(element);
        element.nextElementSibling.textContent = "";
        result = true;
    } else {
        elemInvalid(element);
        element.nextElementSibling.textContent = "name length should be from 3 to 16 symbols";

    }
    return result;
};

const checkFieldsNull = () => {
    let result = false;
    for (let element of form.elements) {
        if (!element.classList.contains('txt-input')) {

        } else {
            if (element.value === "") {
                element.nextElementSibling.textContent = emptyInputMessage;
                elemInvalid(element);
                result = false;
            } else {
                element.nextElementSibling.textContent = emptyPlug;
                result = true;
            }
        }
    }
    return result;
}


const elemValid = (element) => {
    removeClasses(element);
    element.classList.add('valid');
}

const elemInvalid = (element) => {
    removeClasses(element);
    element.classList.add('invalid');
}

const removeClasses = (element) => {
    element.classList.remove('invalid');
    element.classList.remove('valid');
}




