import React, { Component } from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';

import Header from './components/common/Header';
import RegisterPage from './components/Auth/RegisterPage';
import LoginPage from './components/Auth/LoginPage';
import HomePage from './components/HomePage/HomePage';
import AnalysesDetailPage from './components/AnalyzesPage/AnalysesDetailsPage';
import AdminRoute from "./components/common/AdminRoute";
import PrivateRoute from "./components/common/PrivateRoute";
import AnalysesAddPage from "./components/AnalyzesPage/AnalysesAddPage";
import EmployeeDetailsPage from "./components/EmployeePage/EmployeeDetailsPage";
import RegistrarRoute from "./components/common/RegistrarRoute";
import PatientRegisterPage from "./components/PatientPage/PatientRegisterPage"


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
                      user={localStorage.getItem('user')}
                      logout={this.onLogout} />
              <Switch>
                  <Route exact path="/" component={HomePage} />
                  <Route path="/analyzes/:id" component={AnalysesDetailPage} />
                  <Route path="/login" component={LoginPage} />
                  <AdminRoute path="/employee/register" component={RegisterPage} />
                  <RegistrarRoute path="/patient/register" component={PatientRegisterPage}/>
                  <PrivateRoute path="/analysis/add" component={AnalysesAddPage} />
                  <PrivateRoute path="/employee/profile" component={EmployeeDetailsPage} />
              </Switch>
          </div>
      );
  }
}

export default withRouter(App);
