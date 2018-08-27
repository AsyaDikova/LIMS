import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';

export default class Header extends Component {
    render() {
        const {loggedIn, isAdmin, logout, user, isRegistrar} = this.props;

        return (
            <header>
                <NavLink exact to="/" activeClassName="active">Home</NavLink>
                {loggedIn && <NavLink to="/analysis/add" activeClassName="active">Add Analyses</NavLink>}
                {loggedIn && <NavLink to="/employee/profile" activeClassName="active">Profile</NavLink>}
                {isAdmin && <NavLink to="/employee/register" activeClassName="active">Register</NavLink>}
                {(isAdmin || isRegistrar) && <NavLink to="/patient/register" activeClassName="active">Patient Register</NavLink>}
                {loggedIn && <span>Welcome, {user} !</span>}
                {loggedIn && <a href="javascript:void(0)" onClick={logout}>Logout</a>}
                {!loggedIn && <NavLink to="/login" activeClassName="active">Login</NavLink>}
            </header>
        );
    }
}