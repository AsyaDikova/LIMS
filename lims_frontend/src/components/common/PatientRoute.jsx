import React, { Component } from 'react';
import { Redirect, Route } from 'react-router-dom';


export default class PatientRoute extends Component {
    render() {
        if (localStorage.getItem('authToken') === null ||
            !localStorage.getItem('isPatient')) {
            return <Redirect to="/login" />;
        }

        return (
            <Route {...this.props}>
                this.props.children
            </Route>
        );
    }
}