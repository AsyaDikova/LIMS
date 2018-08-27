import React, { Component } from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';

import Header from './components/common/Header';
import RegisterPage from './components/auth/RegisterPage';
import LoginPage from './components/auth/LoginPage';
import HomePage from './components/homePage/HomePage';
import AnalysesDetailPage from './components/analyzesPage/AnalysesDetailsPage';
import AdminRoute from "./components/common/AdminRoute";
import PrivateRoute from "./components/common/PrivateRoute";
import AnalysesAddPage from "./components/analyzesPage/AnalysesAddPage";
import EmployeeDetailsPage from "./components/employeePage/EmployeeDetailsPage";
import RegistrarRoute from "./components/common/RegistrarRoute";
import PatientRegisterPage from "./components/patientPage/PatientRegisterPage"
import ConsultationAddPage from "./components/consultationPage/ConsutlationAddPage";
import AnalysisResultAddPage from "./components/analysesResultPage/AnalysisResultAddPage";


class App extends Component {
    constructor(props) {
        super(props);

        this.onLogout = this.onLogout.bind(this);
    }

    onLogout() {
        localStorage.clear();
        this.props.history.push('/');
    }

  render() {
      return (
          <div className="App">
              <Header loggedIn={localStorage.getItem('authToken') != null}
                      isAdmin={localStorage.getItem('isAdmin') != null && localStorage.getItem('isAdmin') === 'true'}
                      isRegistrar={localStorage.getItem('isRegistrar') != null && localStorage.getItem('isRegistrar') === 'true'}
                      user={localStorage.getItem('user')}
                      logout={this.onLogout} />
              <Switch>
                  <Route exact path="/" component={HomePage} />
                  <Route path="/analyzes/:id" component={AnalysesDetailPage} />
                  <Route path="/login" component={LoginPage} />
                  <AdminRoute path="/employee/register" component={RegisterPage} />
                  <RegistrarRoute path="/patient/register" component={PatientRegisterPage}/>
                  <RegistrarRoute path="/consultation/create" component={ConsultationAddPage}/>
                  <RegistrarRoute path="/analysisResult/create" component={AnalysisResultAddPage}/>
                  <PrivateRoute path="/analysis/add" component={AnalysesAddPage} />
                  <PrivateRoute path="/employee/profile" component={EmployeeDetailsPage} />
              </Switch>
          </div>
      );
  }
}

export default withRouter(App);
