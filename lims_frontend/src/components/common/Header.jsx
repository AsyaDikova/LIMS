import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';

export default class Header extends Component {
    render() {
        const {loggedIn, isAdmin, logout, user, isRegistrar, isEmployee, isPatient} = this.props;

        return (
                <nav class="navbar navbar-light bg-light">
                    <a class="navbar-brand" href="/">
                        <img src="/images.png" width="30" height="30" class="d-inline-block align-top" alt="" />
                            DNA Analysis
                    </a>
                <NavLink exact to="/" activeClassName="active">Home</NavLink>
                {(isAdmin || isRegistrar || isEmployee) && <NavLink to="/analysis/add" activeClassName="active">Add Analyses</NavLink>}
                {isAdmin && <NavLink to="/employee/register" activeClassName="active">Employee Register</NavLink>}
                {(isAdmin || isRegistrar || isEmployee) && <NavLink to="/employee/schedule" activeClassName="active">Day Schedule</NavLink>}
                {isPatient && <NavLink to="/patient/consultation" activeClassName="active">My Consultation</NavLink>}
                {(isAdmin || isRegistrar) && <NavLink to="/patient/register" activeClassName="active">Patient Register</NavLink>}
                {loggedIn && <span>Welcome, {user} !</span>}
                {loggedIn && <a href="javascript:void(0)" onClick={logout}>Logout</a>}
                {!loggedIn && <NavLink to="/login" activeClassName="active">Login</NavLink>}
            </nav>
        );
    }
}