const host = 'http://localhost:8000/';

async function register(email, password, firstName, lastName, phoneNumber) {
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
            phoneNumber
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
    const res = await fetch(host + 'analysis/namesList', {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken'),
            'Content-Type': 'application/json'
        }
    });
    return await res.json();
}

async function getAnalysesDetails(id) {
    const res = await fetch(host + 'analyzes/' + id, {
        method: 'GET'
    });
    return await res.json();
}

async function createConsultation(patientId, analysisId, hourOfConsultation, dateOfConsultation) {
    const res = await fetch(host + 'consultation/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken')
        },
        body: JSON.stringify({
            patientId,
            analysisId,
            hourOfConsultation,
            dateOfConsultation
        })
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

async function createAnalysisResult(patientId, analysisId) {
    const res = await fetch(host + 'analysisResult/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken')
        },
        body: JSON.stringify({
            patientId,
            analysisId
        })
    });
    return await res.json();
}

async function getDaySchedulesHours(analysisId) {
    const res = await fetch(host + 'daySchedule/freeHours?analysisId=' + analysisId, {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken'),
            'Content-Type': 'application/json'
        }
    });
    return await res.json();
}

async function getEmployeeDaySchedulesHours() {
    const res = await fetch(host + 'daySchedule/employee', {
        method: 'GET',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken'),
            'Content-Type': 'application/json'
        }
    });
    return await res.json();
}





export { register, login, getAnalyzes, getAnalysesDetails, createAnalyses, getEmployeeDetails,
    registerPatient, getAnalyzesName, createConsultation, createAnalysisResult, getDaySchedulesHours};