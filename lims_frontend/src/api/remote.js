const host = 'http://localhost:8000/';

async function register(email, password, firstName, lastName, phoneNumber, repeatPassword) {
    const res = await fetch(host + 'employee/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken')
        },
        body: JSON.stringify({
            email,
            password,
            firstName,
            lastName,
            phoneNumber,
            repeatPassword
        })
    });
    return await res.json();
}


async function registerPatient(email, firstName, lastName, phoneNumber, password) {
    const res = await fetch(host + 'patient/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken')
        },
        body: JSON.stringify({
            email,
            firstName,
            lastName,
            phoneNumber,
            password
        })
    });

    return await res.json();
}


async function login(email, password) {
    const res = await fetch(host + 'login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email,
            password
        })
    });
    return await res.json();
}

function parseJwt (token) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
}

async function getEmployeeForAnalyses() {
    const res = await fetch(host + 'analyses/add', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken')
        }
    });
    return await res.json();
}

async function createAnalyses(name, price, description, periodOfProduct, type) {
    const res = await fetch(host + 'analysis/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken')
        },
        body: JSON.stringify({
            name,
            price,
            description,
            periodOfProduct,
            type
        })
    });
    return await res.json();
}


async function getAnalyzes() {
    const res = await fetch(host + 'analyzes');
    return await res.json();
}

async function getAnalyzesName() {
    const res = await fetch(host + 'analyzes/names');
    return await res.json();
}

async function getAnalysesDetails(id) {
    const res = await fetch(host + 'analyzes/' + id, {
        method: 'GET'
    });
    return await res.json();
}

async function getEmployeeDetails() {
    const res = await fetch(host + 'employee/profile', {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken'),
            'Content-Type': 'application/json'
        }
    });
    return await res.json();
}



export { register, login, getAnalyzes, getAnalysesDetails, createAnalyses, getEmployeeForAnalyses,getEmployeeDetails,
    registerPatient, getAnalyzesName};