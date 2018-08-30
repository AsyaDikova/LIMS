import React, { Component } from 'react';
import { Redirect, Route } from 'react-router-dom';


export default class RegistrarRoute extends Component {
    render() {
        if (localStorage.getItem('authToken') === null ||
            (localStorage.getItem('isRegistrar') == 'false' &&
            localStorage.getItem('isAdmin') ==='false')) {
            localStorage.clear();
            return <Redirect to="/login" />;
        }

        return (
            <Route {...this.props}>
                this.props.children
            </Route>
        );
    }
}