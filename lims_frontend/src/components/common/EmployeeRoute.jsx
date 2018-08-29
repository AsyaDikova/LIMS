import React, { Component } from 'react';
import { Redirect, Route } from 'react-router-dom';


export default class EmployeeRoute extends Component {
    render() {
        if (localStorage.getItem('authToken') === null ||
            (!localStorage.getItem('isRegistrar') &&
                !localStorage.getItem('isAdmin') &&
                !localStorage.getItem('isEmployee'))) {
            return <Redirect to="/login" />;
        }

        return (
            <Route {...this.props}>
                this.props.children
            </Route>
        );
    }
}